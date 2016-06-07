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
package hu.bme.mit.trainbenchmark.generator.graph.neo4j;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.stream.XMLStreamException;

import org.apache.commons.io.FileUtils;
import org.neo4j.cypher.export.DatabaseSubGraph;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.DynamicRelationshipType;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.shell.tools.imp.format.graphml.XmlGraphMLWriter;
import org.neo4j.shell.tools.imp.util.Config;
import org.neo4j.shell.tools.imp.util.ProgressReporter;

import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.generator.ModelSerializer;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;
import hu.bme.mit.trainbenchmark.generator.graph.neo4j.config.GraphGeneratorConfig;

public class Neo4jGraphSerializer extends ModelSerializer<GeneratorConfig> {

	protected String databasePath;

	protected GraphGeneratorConfig graphGeneratorConfig;
	protected GraphDatabaseService graphDb;
	protected Transaction tx;

	public Neo4jGraphSerializer(final GeneratorConfig generatorConfig) {
		super(generatorConfig);
	}

	@Override
	public String syntax() {
		return "graph";
	}

	@Override
	public void initModel() throws IOException {
		final String databaseDirectoriesPath = "/neo4j-gen/";
		databasePath = databaseDirectoriesPath + "/" + generatorConfig.getModelFileNameWithoutExtension() + ".neo4j";

		// on the first run delete the previous database directories
		if (new File(databasePath).exists()) {
			FileUtils.deleteDirectory(new File(databasePath));
		}

		graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(databasePath);

		// bump the initial id from 0 to 1
		try (Transaction tx = graphDb.beginTx()) {
			graphDb.createNode().delete();
			tx.success();
		}
	}

	@Override
	public Object createVertex(final int id, final String type, final Map<String, ? extends Object> attributes,
			final Map<String, Object> outgoingEdges, final Map<String, Object> incomingEdges) {
		final Node node = graphDb.createNode(DynamicLabel.label(type));

		// this only works for inheritance hierarchies
		if (ModelConstants.SUPERTYPES.containsKey(type)) {
			final String ancestor = ModelConstants.SUPERTYPES.get(type);
			node.addLabel(DynamicLabel.label(ancestor));
		}

		for (final Entry<String, ? extends Object> attribute : attributes.entrySet()) {
			final String key = attribute.getKey();
			Object value = attribute.getValue();

			// convert the value to string if it's an enum
			value = enumsToString(value);
			node.setProperty(key, value);
		}

		for (final Entry<String, Object> outgoingEdge : outgoingEdges.entrySet()) {
			final String label = outgoingEdge.getKey();
			if (outgoingEdge.getValue() instanceof Node) {
				final Node targetNode = (Node) outgoingEdge.getValue();
				node.createRelationshipTo(targetNode, relationship(label));
			}
		}

		for (final Entry<String, Object> incomingEdge : incomingEdges.entrySet()) {
			final String label = incomingEdge.getKey();
			if (incomingEdge.getValue() instanceof Node) {
				final Node sourceNode = (Node) incomingEdge.getValue();
				sourceNode.createRelationshipTo(node, relationship(label));
			}
		}

		return node;
	}

	private Object enumsToString(Object value) {
		if (value instanceof Enum) {
			final Enum<?> e = (Enum<?>) value;
			value = e.toString();
		}
		return value;
	}

	@Override
	public void createEdge(final String label, final Object from, final Object to) {
		if (from == null || to == null) {
			return;
		}

		final Node source = (Node) from;
		final Node target = (Node) to;

		final RelationshipType relationshipType = relationship(label);
		source.createRelationshipTo(target, relationshipType);
	}

	@Override
	public void setAttribute(final String type, final Object node, final String key, final Object value) {
		final Node n = (Node) node;

		final Object attributeValue = enumsToString(value);
		n.setProperty(key, attributeValue);
	}

	public DynamicRelationshipType relationship(final String label) {
		return DynamicRelationshipType.withName(label);
	}

	@Override
	public void persistModel() throws IOException, XMLStreamException {
		try (Transaction tx = graphDb.beginTx()) {
			final ProgressReporter reporter = new ProgressReporter(null, null);

			final StringWriter writer = new StringWriter();
			final XmlGraphMLWriter xmlGraphMLWriter = new XmlGraphMLWriter();
			final Config config = Config.config();
			xmlGraphMLWriter.write(new DatabaseSubGraph(graphDb), writer, reporter, config.withTypes());
			tx.success();

			final String fileName = generatorConfig.getModelPathWithoutExtension() + ".graphml";

			String graphmlContent = writer.toString();
			// this is required to be compatibile with OrientDB
			graphmlContent = graphmlContent.replaceAll("<graph id=\"G\" edgedefault=\"directed\">",
					"<graph id=\"G\" edgedefault=\"directed\">\n<key id=\"labels\" for=\"node\" attr.name=\"labels\" attr.type=\"string\"/>");

			FileUtils.writeStringToFile(new File(fileName), graphmlContent.trim());
		} finally {
			graphDb.shutdown();

			// cleanup: delete the database directory
			if (new File(databasePath).exists()) {
				FileUtils.deleteDirectory(new File(databasePath));
			}
		}
	}

	@Override
	public void beginTransaction() throws IOException {
		tx = graphDb.beginTx();
	}

	@Override
	public void endTransaction() {
		tx.success();
		tx.close();
	}

}
