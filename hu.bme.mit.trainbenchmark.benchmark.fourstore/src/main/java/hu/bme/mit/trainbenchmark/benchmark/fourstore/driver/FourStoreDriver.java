/*******************************************************************************
 * Copyright (c) 2010-2014, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.benchmark.fourstore.driver;

import static hu.bme.mit.trainbenchmark.benchmark.fourstore.driver.RDFUtil.brackets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.openrdf.model.URI;
import org.openrdf.query.BindingSet;
import org.openrdf.query.resultio.text.tsv.SPARQLResultsTSVParser;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

import hu.bme.mit.trainbenchmark.benchmark.rdf.RDFBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.rdf.RDFDriver;
import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.URIComparator;
import hu.bme.mit.trainbenchmark.constants.Query;

public class FourStoreDriver extends RDFDriver<URI> {

	protected static final String RDF_PREFIX = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
	protected static final String SPARQL_RDF_PREFIX = "PREFIX rdf: <" + RDF_PREFIX + "> ";
	protected static final String CLUSTERNAME = "trainbenchmark_cluster";

	protected boolean showCommands = false;
	protected boolean showCommandOutput = false;
	protected final Map<String, String> environment = ImmutableMap.of("FOURSTORE_CLUSTER_NAME", CLUSTERNAME);

	public FourStoreDriver(final RDFBenchmarkConfig benchmarkConfig) throws IOException {
		super(benchmarkConfig);
		final String dbPath = "/var/lib/4store/" + CLUSTERNAME;
		if (new File(dbPath).exists()) {
			FileUtils.deleteDirectory(new File(dbPath));
		}
	}

	@Override
	public URIComparator getElementComparator() {
		return new URIComparator();
	}

	public void init() throws FileNotFoundException, IOException {
		UnixUtils.execResourceScript("4s-start.sh", environment, showCommandOutput);
	}

	@Override
	public void destroy() throws FileNotFoundException, IOException {
		UnixUtils.execResourceScript("4s-stop.sh", environment, showCommandOutput);
	}

	@Override
	public void read(final String modelPathWithoutExtension) throws IOException {
		final String modelPath = modelPathWithoutExtension + getExtension();
		final File modelFile = new File(modelPath);
		if (!modelFile.exists()) {
			throw new FileNotFoundException(modelPath);
		}

		UnixUtils.execResourceScript("4s-import.sh", modelFile.getAbsolutePath(), environment, showCommandOutput);
	}

	public void setShowCommandOutput(final boolean showCommandOutput) {
		this.showCommandOutput = showCommandOutput;
	}

	protected final String ID_PREFIX = "_";

	protected final SPARQLResultsTSVParser parser = new SPARQLResultsTSVParser();

	@Override
	public Collection<BindingSet> runQuery(final Query query, final String queryDefinition) throws IOException {
		return runQuery(queryDefinition);
	}

	protected Collection<BindingSet> runQuery(final String queryDefinition) throws Exception {
		final String command = String.format("4s-query $FOURSTORE_CLUSTER_NAME -f text -s -1 '%s'", queryDefinition);
		if (showCommands) {
			System.out.println(command);
		}

		final BindingSetCollector bindingSetCollector = new BindingSetCollector();
		parser.setQueryResultHandler(bindingSetCollector);

		final InputStream is = UnixUtils.execToStream(command, environment);
		parser.parse(is);
		final Collection<BindingSet> bindingSets = bindingSetCollector.getBindingSets();

		return bindingSets;
	}

	public List<Long> queryIds(final String query) throws IOException {
		final Collection<BindingSet> bindingSets = runQuery(query);

		System.out.println(bindingSets);
		// // example: <http://www.semanticweb.org/ontologies/2011/1/TrainRequirementOntology.owl#_87947>
		// final String regex = "<.*#" + ID_PREFIX + "(\\d+)>";
		// final Pattern pattern = Pattern.compile(regex);
		//
		// final List<Long> results = new ArrayList<>();
		//
		// String line;
		// while ((line = reader.readLine()) != null) {
		// final Matcher matcher = pattern.matcher(line);
		// if (matcher.matches()) {
		// final Long id = new Long(matcher.group(1));
		// results.add(id);
		// }
		// }
		return null;
	}

	@Override
	public List<URI> collectVertices(final String type) throws IOException {
		final String queryDefinition = String.format(SPARQL_RDF_PREFIX + "SELECT ?a WHERE { ?a rdf:type %s }", RDFUtil.brackets(type));
		final Collection<BindingSet> ids = runQuery(queryDefinition);

		final List<URI> vertices = new LinkedList<>();
		for (final BindingSet bindingSet : ids) {
			// ...
		}
		return vertices;
	}

	@Override
	public boolean ask(final String query) throws IOException {
		final Collection<BindingSet> bindingSet = runQuery(query);
		// final String line = reader.readLine();
		//
		// switch (line) {
		// case "true":
		// return true;
		// case "false":
		// return false;
		// default:
		// throw new IOException("Result for ASK query should be 'true' or 'false'. Received '" + line + "' instead.");
		// }
		System.out.println(bindingSet);
		throw new RuntimeException();
		// return false;
	}

	// note the following limitation of arguments in Bash and set the PARTITION_SIZE accordingly.
	// "one argument must not be longer than MAX_ARG_STRLEN (131072)" (http://www.in-ulm.de/~mascheck/various/argmax/)
	private static final int PARTITION_SIZE = 500;

	// create

	// insert vertices

	public void insertVertex(final String uri, final String type) throws IOException {
		final List<String> uris = new ArrayList<>();
		uris.add(uri);
		insertVertices(uris, type);
	}

	public void insertVertices(final List<String> uris, final String type) throws IOException {
		if (uris.isEmpty()) {
			return;
		}

		final List<List<String>> partitions = Lists.partition(uris, PARTITION_SIZE);
		for (final List<String> partition : partitions) {
			insertVerticesPartition(partition, type);
		}
	}

	private void insertVerticesPartition(final Collection<String> uris, final String type) throws IOException {
		final StringBuilder insertQueryBuilder = new StringBuilder(SPARQL_RDF_PREFIX);
		insertQueryBuilder.append("INSERT DATA {");
		for (final String uri : uris) {
			insertQueryBuilder.append(String.format(". %s rdf:type %s", brackets(uri), brackets(type)));
		}
		insertQueryBuilder.append("}");

		// run the update
		runUpdate(insertQueryBuilder.toString());
	}

	// insert edges

	public void insertEdge(final String sourceVertexURI, final String targetVertexURI, final String type) throws IOException {
		final Multimap<String, String> edges = HashMultimap.create();
		edges.put(sourceVertexURI, targetVertexURI);
		insertEdges(edges, type);
	}

	public void insertEdges(final Multimap<String, String> edges, final String type) throws IOException {
		if (edges.isEmpty()) {
			return;
		}

		final ArrayList<String> sourceVertices = new ArrayList<>(edges.keySet());
		final List<List<String>> sourceVerticesPartitions = Lists.partition(sourceVertices, PARTITION_SIZE);
		for (final List<String> sourceVerticesPartition : sourceVerticesPartitions) {

			final Multimap<String, String> edgePartition = ArrayListMultimap.create();
			for (final String sourceVertexURI : sourceVerticesPartition) {
				final Collection<String> targetVertexURIs = edges.get(sourceVertexURI);
				edgePartition.putAll(sourceVertexURI, targetVertexURIs);
			}

			insertEdgesPartition(edgePartition, type);
		}
	}

	private void insertEdgesPartition(final Multimap<String, String> edges, final String type) throws IOException {
		final StringBuilder insertQueryBuilder = new StringBuilder("INSERT DATA {");
		edgesToTriples(edges, type, insertQueryBuilder);
		insertQueryBuilder.append("}");

		// run the update
		runUpdate(insertQueryBuilder.toString());
	}

	// insert edges with verties

	public void insertEdgeWithVertex(final String sourceURI, final String targetURI, final String edgeType, final String targetVertexType)
			throws IOException {
		final Multimap<String, String> edges = HashMultimap.create();
		edges.put(sourceURI, targetURI);
		insertEdgesWithVertex(edges, edgeType, targetVertexType);
	}

	public void insertEdgesWithVertex(final Multimap<String, String> edges, final String edgeType, final String targetVertexType)
			throws IOException {
		if (edges.isEmpty()) {
			return;
		}

		final ArrayList<String> sourceVertices = new ArrayList<>(edges.keySet());
		final List<List<String>> sourceVerticesPartitions = Lists.partition(sourceVertices, PARTITION_SIZE);
		for (final List<String> sourceVerticesPartition : sourceVerticesPartitions) {

			final Multimap<String, String> edgePartition = ArrayListMultimap.create();
			for (final String sourceVertexURI : sourceVerticesPartition) {
				final Collection<String> targetVertexURIs = edges.get(sourceVertexURI);
				edgePartition.putAll(sourceVertexURI, targetVertexURIs);
			}

			insertEdgesWithVertexPartition(edgePartition, edgeType, targetVertexType);
		}

	}

	private void insertEdgesWithVertexPartition(final Multimap<String, String> edges, final String edgeType, final String targetVertexType)
			throws IOException {
		final StringBuilder insertQueryBuilder = new StringBuilder(SPARQL_RDF_PREFIX);
		insertQueryBuilder.append("INSERT DATA {");
		edgesToTriples(edges, edgeType, insertQueryBuilder);
		for (final String targetVertex : edges.values()) {
			insertQueryBuilder.append(String.format(". %s rdf:type %s", brackets(targetVertex), brackets(targetVertexType)));
		}
		insertQueryBuilder.append("}");

		// run the update
		runUpdate(insertQueryBuilder.toString());
	}

	// update properties

	public void updateProperty(final String vertex, final String type, final Object value) throws IOException {
		final Map<String, Object> properties = new HashMap<>();
		properties.put(vertex, value);
		updateProperties(properties, type);
	}

	public void updateProperties(final Map<String, Object> properties, final String type) throws IOException {
		if (properties.isEmpty()) {
			return;
		}

		final List<String> vertexURIs = new ArrayList<>(properties.keySet());

		final List<List<String>> vertexURIpartitions = Lists.partition(vertexURIs, PARTITION_SIZE);
		for (final List<String> vertexURIpartition : vertexURIpartitions) {

			final Map<String, Object> propertyPartition = new HashMap<>();
			for (final String vertexURI : vertexURIpartition) {
				final Object value = properties.get(vertexURI);
				propertyPartition.put(vertexURI, value);
			}

			updatePropertiesPartition(propertyPartition, type);
		}
	}

	private void updatePropertiesPartition(final Map<String, Object> properties, final String type) throws IOException {
		final StringBuilder updateQueryBuilder = new StringBuilder(SPARQL_RDF_PREFIX);
		int i = 0;

		// delete
		for (final Entry<String, Object> property : properties.entrySet()) {
			final String vertex = property.getKey();

			i++;
			updateQueryBuilder.append(String.format("DELETE { %s %s ?a%d } WHERE { %s %s ?a%d }; ", brackets(vertex), brackets(type), i,
					brackets(vertex), brackets(type), i));
		}

		// insert
		boolean first = true;
		updateQueryBuilder.append("INSERT DATA {");
		for (final Entry<String, Object> property : properties.entrySet()) {
			if (first) {
				first = false;
			} else {
				updateQueryBuilder.append(".");
			}
			final String vertex = property.getKey();
			final String value = RDFUtil.toLiteral(property.getValue());
			updateQueryBuilder.append(String.format(" %s %s %s ", brackets(vertex), brackets(type), value));
		}
		updateQueryBuilder.append("}");

		// run the update
		runUpdate(updateQueryBuilder.toString());
	}

	// delete vertices

	public void deleteVertex(final String uri) throws IOException {
		final List<String> vertices = new ArrayList<>();
		vertices.add(uri);
		deleteVertices(vertices);
	}

	public void deleteVertices(final List<String> uris) throws IOException {
		if (uris.isEmpty()) {
			return;
		}

		final List<List<String>> partitions = Lists.partition(uris, PARTITION_SIZE);
		for (final List<String> partition : partitions) {
			deleteVertexPartition(partition);
		}
	}

	private void deleteVertexPartition(final List<String> uris) throws IOException {
		final StringBuilder deleteQueryBuilder = new StringBuilder();

		// add a number to each variable number in the SPARQL query in order to make it unique
		long i = 0;
		for (final String vertex : uris) {
			// if we try to use DELETE DATA (as in the deleteEdge() method), 4store throws an error:
			// DELETE WHERE { x } not yet supported, use DELETE { x } WHERE { x }
			i++;
			// delete "incoming edges"
			deleteQueryBuilder.append(
					String.format("DELETE { ?a%d ?b%d %s } WHERE { ?a%d ?b%d %s }; ", i, i, brackets(vertex), i, i, brackets(vertex)));
			i++;
			// delete "outgoing edges" and "properties"
			deleteQueryBuilder.append(
					String.format("DELETE { %s ?a%d ?b%d } WHERE { %s ?a%d ?b%d }; ", brackets(vertex), i, i, brackets(vertex), i, i));
		}

		runUpdate(deleteQueryBuilder.toString());
	}

	// delete edges

	public void deleteEdge(final String sourceVertexURI, final String targetVertexURI, final String type) throws IOException {
		final Multimap<String, String> edges = HashMultimap.create();
		edges.put(sourceVertexURI, targetVertexURI);
		deleteEdges(edges, type);
	}

	public void deleteEdges(final Multimap<String, String> edges, final String type) throws IOException {
		if (edges.isEmpty()) {
			return;
		}

		deleteEdgesPartition(edges, type);
	}

	private void deleteEdgesPartition(final Multimap<String, String> edges, final String type) throws IOException {
		final StringBuilder deleteQueryBuilder = new StringBuilder("DELETE DATA {");
		edgesToTriples(edges, type, deleteQueryBuilder);
		deleteQueryBuilder.append("}");
		runUpdate(deleteQueryBuilder.toString());
	}

	// helper methods

	public void runUpdate(final String query) throws IOException {
		final String command = String.format("4s-update $FOURSTORE_CLUSTER_NAME '%s'", query);

		if (showCommands) {
			System.out.println(command);
		}
		UnixUtils.exec(command, environment);
	}

	protected StringBuilder initBuilder() {
		StringBuilder insertQueryBuilder;
		insertQueryBuilder = new StringBuilder(SPARQL_RDF_PREFIX);
		insertQueryBuilder.append("INSERT DATA {");
		return insertQueryBuilder;
	}

	protected void edgesToTriples(final Multimap<String, String> edges, final String edgeLabel, final StringBuilder insertQueryBuilder) {
		boolean first = true;
		for (final Entry<String, String> edge : edges.entries()) {
			if (first) {
				first = false;
			} else {
				insertQueryBuilder.append(".");
			}
			final String sourceVertex = edge.getKey();
			final String targetVertex = edge.getValue();

			insertQueryBuilder.append(String.format(" %s %s %s ", brackets(sourceVertex), brackets(edgeLabel), brackets(targetVertex)));
		}
	}

}
