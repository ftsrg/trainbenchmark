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

import apoc.export.csv.ExportCSV;
import apoc.export.graphml.ExportGraphML;
import apoc.graph.Graphs;
import com.google.common.collect.ImmutableMap;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.generator.ModelSerializer;
import hu.bme.mit.trainbenchmark.generator.graph.neo4j.config.Neo4jGraphGeneratorConfig;
import hu.bme.mit.trainbenchmark.neo4j.Neo4jConstants;
import hu.bme.mit.trainbenchmark.neo4j.apoc.ApocHelper;
import hu.bme.mit.trainbenchmark.neo4j.Neo4jHelper;
import hu.bme.mit.trainbenchmark.neo4j.config.Neo4jDeployment;
import org.apache.commons.io.FileUtils;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.kernel.api.exceptions.KernelException;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Map;
import java.util.Map.Entry;

public class Neo4jGraphSerializer extends ModelSerializer<Neo4jGraphGeneratorConfig> {

	protected GraphDatabaseService graphDb;
	protected Transaction tx;
	protected final File databaseDirectory;

	public Neo4jGraphSerializer(final Neo4jGraphGeneratorConfig generatorConfig) {
		super(generatorConfig);
		databaseDirectory = new File(generatorConfig.getConfigBase().getModelDir() + "neo4j-gen/"
				+ generatorConfig.getConfigBase().getModelFileNameWithoutExtension() + ".neo4j");
	}

	@Override
	public String syntax() {
		return "Neo4j graph " + gc.getGraphFormat();
	}

	@Override
	public void initModel() throws IOException {
		cleanupDatabaseDirectory();
		graphDb = Neo4jHelper.startGraphDatabase(Neo4jDeployment.EMBEDDED, databaseDirectory);
	}

	@Override
	public Object createVertex(final int id, final String type, final Map<String, ? extends Object> attributes,
			final Map<String, Object> outgoingEdges, final Map<String, Object> incomingEdges) {
		final Node node = graphDb.createNode(Label.label(type));

		// this only works for inheritance hierarchies
		if (ModelConstants.SUPERTYPES.containsKey(type)) {
			final String ancestor = ModelConstants.SUPERTYPES.get(type);
			node.addLabel(Label.label(ancestor));
		}

		node.setProperty(ModelConstants.ID, id);
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

	public RelationshipType relationship(final String label) {
		return RelationshipType.withName(label);
	}

	@Override
	public void persistModel() throws IOException, XMLStreamException, KernelException {
		try {
			switch (gc.getGraphFormat()) {
			case CSV:
				saveToCsv();
				break;
			case GRAPHML:
				saveToGraphMl();
				break;
			default:
				throw new UnsupportedOperationException("Graph format " + gc.getGraphFormat() + " not supported.");
			}
		} finally {
			graphDb.shutdown();

			// cleanup: delete the database directory
			cleanupDatabaseDirectory();
		}
	}

	private void saveToCsv() throws RemoteException, KernelException {
		ApocHelper.registerProcedure(graphDb, ExportCSV.class, Graphs.class);

		final Map<String, String> exportCommands = ImmutableMap.<String, String>builder()
				// nodes
				.put(ModelConstants.REGION,         "MATCH (n:Region)              RETURN n.id AS `id:ID`") //
				.put(ModelConstants.ROUTE,          "MATCH (n:Route)               RETURN n.id AS `id:ID`, n.active AS `active:BOOLEAN`") //
				.put(ModelConstants.SEGMENT,        "MATCH (n:Segment)             RETURN n.id AS `id:ID`, n.length AS `length:INT`") //
				.put(ModelConstants.SEMAPHORE,      "MATCH (n:Semaphore)           RETURN n.id AS `id:ID`, n.signal AS signal") //
				.put(ModelConstants.SENSOR,         "MATCH (n:Sensor)              RETURN n.id AS `id:ID`") //
				.put(ModelConstants.SWITCH,         "MATCH (n:Switch)              RETURN n.id AS `id:ID`, n.currentPosition AS currentPosition") //
				.put(ModelConstants.SWITCHPOSITION, "MATCH (n:SwitchPosition)      RETURN n.id AS `id:ID`, n.position AS position") //
				// relationships
				.put(ModelConstants.CONNECTS_TO,    "MATCH (n)-[:connectsTo]->(m)  RETURN n.id AS `id:START_ID`, m.id AS `id:END_ID`") //
				.put(ModelConstants.ENTRY,          "MATCH (n)-[:entry]->(m)       RETURN n.id AS `id:START_ID`, m.id AS `id:END_ID`") //
				.put(ModelConstants.EXIT,           "MATCH (n)-[:exit]->(m)        RETURN n.id AS `id:START_ID`, m.id AS `id:END_ID`") //
				.put(ModelConstants.FOLLOWS,        "MATCH (n)-[:follows]->(m)     RETURN n.id AS `id:START_ID`, m.id AS `id:END_ID`") //
				.put(ModelConstants.MONITORED_BY,   "MATCH (n)-[:monitoredBy]->(m) RETURN n.id AS `id:START_ID`, m.id AS `id:END_ID`") //
				.put(ModelConstants.REQUIRES,       "MATCH (n)-[:requires]->(m)    RETURN n.id AS `id:START_ID`, m.id AS `id:END_ID`") //
				.put(ModelConstants.TARGET,         "MATCH (n)-[:target]->(m)      RETURN n.id AS `id:START_ID`, m.id AS `id:END_ID`") //
				.build();

		for (Entry<String, String> entry : exportCommands.entrySet()) {
			final String type = entry.getKey();
			final String query = entry.getValue();

			final String fileName = gc.getConfigBase().getModelPathWithoutExtension() + "-" + type + "."
					+ Neo4jConstants.CSV_EXTENSION;

			try (final Transaction t = graphDb.beginTx()) {
				graphDb.execute(String.format( //
					"CALL apoc.export.csv.query('%s', '%s', null)", //
					query,
					fileName //
				));
			}
		}
	}

	private void saveToGraphMl() throws KernelException {
		ApocHelper.registerProcedure(graphDb, ExportGraphML.class, Graphs.class);

		final String fileName = gc.getConfigBase().getModelPathWithoutExtension() + Neo4jConstants.GRAPHML_POSTFIX;

		try (final Transaction t = graphDb.beginTx()) {
			graphDb.execute(String.format( //
				"CALL apoc.export.graphml.all('%s', {useTypes: true})", //
				fileName //
			));
		}
	}

	private void cleanupDatabaseDirectory() throws IOException {
		if (databaseDirectory.exists()) {
			FileUtils.deleteDirectory(databaseDirectory);
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
