/*******************************************************************************
 * Copyright (c) 2010-2015, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.benchmark.fourstore.driver;

import static hu.bme.mit.trainbenchmark.rdf.RDFConstants.BASE_PREFIX;
import static hu.bme.mit.trainbenchmark.rdf.RDFConstants.ID_PREFIX;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.AttributeOperation;
import hu.bme.mit.trainbenchmark.rdf.RDFDatabaseDriver;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import eu.mondo.driver.fourstore.FourStoreGraphDriverReadWrite;

public class FourStoreDriver extends RDFDatabaseDriver<Long> {

	protected Long newVertexId = null;

	protected static final String CLUSTERNAME = "trainbenchmark_cluster";

	protected FourStoreGraphDriverReadWrite driver;
	protected final String query;

	public FourStoreDriver(final String queryPath) throws IOException {
		// start with a clean slate: delete old directory
		final String dbPath = "/var/lib/4store/" + CLUSTERNAME;
		if (new File(dbPath).exists()) {
			FileUtils.deleteDirectory(new File(dbPath));
		}

		this.query = FileUtils.readFileToString(new File(queryPath));
		this.driver = new FourStoreGraphDriverReadWrite(CLUSTERNAME);
		driver.start();
	}

	@Override
	public void read(final String modelPath) throws IOException {
		try {
			driver.load(modelPath);
		} catch (final InterruptedException e) {
			throw new IOException(e);
		}
	}

	@Override
	public List<Long> runQuery() throws IOException {
		final List<Long> results = driver.queryIds(query);
		return results;
	}

	@Override
	public Comparator<Long> getComparator() {
		// a null comparator provides natural ordering
		return null;
	}

	@Override
	public void destroy() throws IOException {
		driver.stop();
	}

	// create

	@Override
	public void insertVertexWithEdge(final List<Long> vertices, final String sourceVertexType, final String targetVertexType,
			final String edgeType) throws IOException {
		if (newVertexId == null) {
			newVertexId = determineNewVertexId();
		}

		final Multimap<String, String> edges = ArrayListMultimap.create();

		for (final Long vertex : vertices) {
			final String source = BASE_PREFIX + ID_PREFIX + vertex;
			final String target = BASE_PREFIX + ID_PREFIX + newVertexId;
			edges.put(source, target);
			newVertexId++;
		}

		System.out.println(edges);

		final String fullEdgeType = BASE_PREFIX + edgeType;
		final String fullTargetVertexType = BASE_PREFIX + targetVertexType;

		driver.insertEdgesWithVertex(edges, fullEdgeType, fullTargetVertexType);
	}

	@Override
	public Long insertVertexWithEdge(final Long sourceVertex, final String sourceVertexType, final String targetVertexType,
			final String edgeType) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertEdge(final Long sourceVertex, final String sourceVertexType, final Long targetVertex, 
			final String edgeType) throws IOException{
		// TODO Auto-generated method stub

	}

	// read

	@Override
	public List<Long> collectVertices(final String type) throws IOException {
		return driver.collectVertices(BASE_PREFIX + type);
	}

	@Override
	public List<Long> collectOutgoingConnectedVertices(Long sourceVertex, final String sourceVertexType,
			String targetVertexType, String edgeType) {
		// TODO Auto-generated method stub
		return null;
	}

	// update

	@Override
	public void updateProperties(final List<Long> vertices, final String vertexType, final String propertyName,
			final AttributeOperation attributeOperation) throws IOException {
		final String fullPropertyName = BASE_PREFIX + propertyName;
		final Map<String, Object> properties = new HashMap<>();

		final Multimap<Long, Object> originalValues = driver.collectProperties(fullPropertyName);
		for (final Long vertex : vertices) {
			final String key = BASE_PREFIX + ID_PREFIX + vertex;
			final int originalValue = (int) originalValues.get(vertex).iterator().next();
			final int newValue = attributeOperation.op(originalValue);
			properties.put(key, newValue);
		}

		driver.updateProperties(properties, fullPropertyName);
	}

	// delete

	@Override
	public void deleteAllOutgoingEdges(final List<Long> vertices, final String vertexType, final String edgeType) throws IOException {
		// 4s-query trainbenchmark_cluster -s -1 -f text
		// "SELECT ?x WHERE {?x <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.semanticweb.org/ontologies/2011/1/TrainRequirementOntology.owl#Route>}"

		// 4s-update trainbenchmark_cluster
		// "
		// DELETE {?x <http://www.w3.org/1999/02/22-rdf-syntax-ns#type>
		// <http://www.semanticweb.org/ontologies/2011/1/TrainRequirementOntology.owl#Route>}
		// WHERE {?x <http://www.w3.org/1999/02/22-rdf-syntax-ns#type>
		// <http://www.semanticweb.org/ontologies/2011/1/TrainRequirementOntology.owl#Route>}
		// "
		final String predicateString = String.format("<%s%s>", BASE_PREFIX, edgeType);

		for (final Long vertex : vertices) {
			final String subjectString = String.format("<%s%s%d>", BASE_PREFIX, ID_PREFIX, vertex);

			final String delete = String.format( //
					"DELETE {%s %s ?z} WHERE {%s %s ?z}", //
					subjectString, predicateString, //
					subjectString, predicateString);
			driver.runUpdate(delete);
		}
	}

	@Override
	public void deleteAllIncomingEdges(final List<Long> vertices, final String sourceVertexType, final String edgeType) throws IOException {
		for (final Long vertex : vertices) {
			final String query = String.format( //
					"DELETE {?x <%s> <%s%s%d>} WHERE {?x <%s> <%s%s%d>}", //
					BASE_PREFIX + edgeType, BASE_PREFIX, ID_PREFIX, vertex, //
					BASE_PREFIX + edgeType, BASE_PREFIX, ID_PREFIX, vertex);
			driver.runUpdate(query);
		}
	}

	@Override
	public void deleteOneOutgoingEdge(final List<Long> vertices, final String vertexType, final String edgeType) throws IOException {
		final Multimap<Long, Long> edges = driver.collectEdges(BASE_PREFIX + edgeType);

		for (final Long vertex : vertices) {
			final Collection<Long> outgoingEdges = edges.get(vertex);
			if (outgoingEdges.isEmpty()) {
				continue;
			}
			final Long targetVertex = outgoingEdges.iterator().next();

			final String subjectString = String.format("<%s%s%d>", BASE_PREFIX, ID_PREFIX, vertex);
			final String predicateString = String.format("<%s%s>", BASE_PREFIX, edgeType);
			final String objectString = String.format("<%s%s%d>", BASE_PREFIX, ID_PREFIX, targetVertex);

			final String delete = String.format( //
					"DELETE {%s %s %s} WHERE {%s %s %s}", //
					subjectString, predicateString, objectString, subjectString, predicateString, objectString);
			driver.runUpdate(delete);
			break;
		}
	}

	@Override
	public void deleteSingleOutgoingEdge(final List<Long> vertices, final String vertexType, final String edgeType) throws IOException {
		deleteAllOutgoingEdges(vertices, vertexType, edgeType);
	}

	// utility

	protected boolean ask(final String askQuery) throws IOException {
		return driver.ask(askQuery);
	}

}
