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

package hu.bme.mit.trainbenchmark.generator.graph;

import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.generator.Generator;
import hu.bme.mit.trainbenchmark.generator.RailwayGenerator;
import hu.bme.mit.trainbenchmark.generator.graph.config.GraphGeneratorConfig;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.stream.XMLStreamException;

import org.apache.commons.cli.ParseException;
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

public class GraphRailwayGenerator extends RailwayGenerator {

	public GraphRailwayGenerator(GraphGeneratorConfig generatorConfig){
		super();
		this.generatorConfig = generatorConfig;
	}

	@Override
	protected String syntax() {
		return "graph";
	}

	protected GraphGeneratorConfig graphGeneratorConfig;
	protected GraphDatabaseService graphDb;
	protected Transaction tx;

	@Override
	protected void initModel() throws IOException {
		final String databaseDirectoriesPath = generatorConfig.getModelPath() + "/neo4j-gen/";
		final String databasePath = databaseDirectoriesPath + "/" + generatorConfig.getModelFileNameWithoutExtension() + ".neo4j";

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
	protected Object createVertex(final int id, final String type, final Map<String, Object> attributes,
			final Map<String, Object> outgoingEdges, final Map<String, Object> incomingEdges) {
		final Node node;
		try (Transaction tx = graphDb.beginTx()) {
			node = graphDb.createNode(DynamicLabel.label(type));

			// this only works for inheritance hierarchies with
			if (ModelConstants.ancestors.containsKey(type)) {
				final String ancestor = ModelConstants.ancestors.get(type);
				node.addLabel(DynamicLabel.label(ancestor));
			}

			for (final Entry<String, Object> attribute : attributes.entrySet()) {
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
			tx.success();
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
	protected void createEdge(final String label, final Object from, final Object to) {
		if (from == null || to == null) {
			return;
		}

		final Node source = (Node) from;
		final Node target = (Node) to;

		final RelationshipType relationshipType = relationship(label);
		source.createRelationshipTo(target, relationshipType);
	}

	@Override
	protected void setAttribute(final String type, final Object node, final String key, final Object value) {
		final Node n = (Node) node;

		final Object attributeValue = enumsToString(value);
		n.setProperty(key, attributeValue);
	}

	protected DynamicRelationshipType relationship(final String label) {
		return DynamicRelationshipType.withName(label);
	}

	@Override
	protected void persistModel() throws IOException, XMLStreamException {
		try (Transaction tx = graphDb.beginTx()) {
			final ProgressReporter reporter = new ProgressReporter(null, null);

			final StringWriter writer = new StringWriter();
			final XmlGraphMLWriter xmlGraphMLWriter = new XmlGraphMLWriter();
			final Config config = Config.config();
			xmlGraphMLWriter.write(new DatabaseSubGraph(graphDb), writer, reporter, config.withTypes());
			tx.success();

			final String fileName = generatorConfig.getModelPathNameWithoutExtension() + ".graphml";

			String graphmlContent = writer.toString();
			// this is required to be compatibile with OrientDB
			graphmlContent = graphmlContent
					.replaceAll(
							//
							"<graph id=\"G\" edgedefault=\"directed\">",
							"<graph id=\"G\" edgedefault=\"directed\">\n<key id=\"labels\" for=\"node\" attr.name=\"labels\" attr.type=\"string\"/>");

			FileUtils.writeStringToFile(new File(fileName), graphmlContent.trim());
		} finally {
			graphDb.shutdown();
		}
	}

	@Override
	protected void beginRoute() throws IOException {
		tx = graphDb.beginTx();
	}

	@Override
	protected void endRoute() {
		tx.success();
		tx.close();
	}

}
