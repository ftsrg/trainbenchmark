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
package hu.bme.mit.trainbenchmark.benchmark.neo4j.driver;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.CURRENTPOSITION;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.DEFINED_BY;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ENTRY;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.LENGTH;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.POSITION;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR_EDGE;
import hu.bme.mit.trainbenchmark.benchmark.driver.DatabaseDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jPosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jRouteSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jSemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jSwitchSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jSwitchSetMatch;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.constants.QueryConstants;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLStreamException;

import org.apache.commons.collections.IteratorUtils;
import org.apache.commons.io.FileUtils;
import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.cypher.javacompat.ExecutionResult;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.DynamicRelationshipType;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.ResourceIterable;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.shell.tools.imp.format.graphml.XmlGraphMLReader;
import org.neo4j.shell.tools.imp.util.MapNodeCache;
import org.neo4j.tooling.GlobalGraphOperations;

public class Neo4jDriver extends DatabaseDriver<Neo4jMatch, Node> {

	protected final RelationshipType definedByEdge = DynamicRelationshipType.withName(DEFINED_BY);
	protected final RelationshipType entryEdge = DynamicRelationshipType.withName(ENTRY);
	protected final RelationshipType sensorEdge = DynamicRelationshipType.withName(SENSOR_EDGE);

	protected final Label sensorLabel = DynamicLabel.label(SENSOR);

	protected Transaction tx;
	protected GraphDatabaseService graphDb;
	protected String dbPath;
	protected String query;
	protected Comparator<Node> nodeComparator = new NodeComparator();

	public Neo4jDriver(final String dbPath, final String query) throws IOException {
		// delete old directory
		if (new File(dbPath).exists()) {
			FileUtils.deleteDirectory(new File(dbPath));
		}

		// start the database
		this.dbPath = dbPath;
		this.query = query;
	}

	@Override
	public void beginTransaction() {
		tx = graphDb.beginTx();
	}

	@Override
	public void finishTransaction() {
		tx.success();
		tx.close();
	}

	@Override
	public void read(final String filePath) throws IOException {
		graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(dbPath);

		try (Transaction tx = graphDb.beginTx()) {
			final XmlGraphMLReader xmlGraphMLReader = new XmlGraphMLReader(graphDb);
			xmlGraphMLReader.nodeLabels(true);
			xmlGraphMLReader.parseXML(new BufferedReader(new FileReader(filePath)), MapNodeCache.usingHashMap());
			tx.success();
		} catch (final XMLStreamException e) {
			throw new IOException(e);
		}
	}

	public List<Neo4jMatch> runQuery(final String pattern) throws IOException {
		final List<Neo4jMatch> results = new ArrayList<>();

		try (Transaction tx = graphDb.beginTx()) {
			final ExecutionEngine engine = new ExecutionEngine(graphDb);
			final ExecutionResult result = engine.execute(query);
			for (final Map<String, Object> row : result) {
				results.add(createMatch(pattern, row));
			}
		}

		return results;
	}

	protected Neo4jMatch createMatch(final String pattern, final Map<String, Object> row) {
		switch (pattern) {
		case QueryConstants.POSLENGTH:
			return new Neo4jPosLengthMatch(row);
		case QueryConstants.ROUTESENSOR:
			return new Neo4jRouteSensorMatch(row);
		case QueryConstants.SEMAPHORENEIGHBOR:
			return new Neo4jSemaphoreNeighborMatch(row);
		case QueryConstants.SWITCHSENSOR:
			return new Neo4jSwitchSensorMatch(row);
		case QueryConstants.SWITCHSET:
			return new Neo4jSwitchSetMatch(row);
		default:
			throw new UnsupportedOperationException("Pattern not supported: " + pattern);
		}
	}

	@Override
	public Comparator<Neo4jMatch> getMatchComparator() {
		return null;
	}

	@Override
	public void destroy() {
		graphDb.shutdown();
	}

	// create

	// @Override
	// public void insertEdge(final Node sourceVertex, final String sourceVertexType, final Node targetVertex, final String edgeType)
	// throws IOException {
	// final RelationshipType relationship = DynamicRelationshipType.withName(edgeType);
	// sourceVertex.createRelationshipTo(targetVertex, relationship);
	// }
	//
	// @Override
	// public void insertVertexWithEdge(final List<Node> vertices, final String sourceVertexType, final String targetVertexType,
	// final String edgeType) throws IOException {
	// final Label label = DynamicLabel.label(targetVertexType);
	// for (final Node vertex : vertices) {
	// insertVertexWithEdge(vertex, edgeType, label);
	// }
	// }
	//
	// @Override
	// public Node insertVertexWithEdge(final Node sourceVertex, final String sourceVertexType, final String targetVertexType,
	// final String edgeType) throws IOException {
	// final Label label = DynamicLabel.label(targetVertexType);
	// return (insertVertexWithEdge(sourceVertex, edgeType, label));
	//
	// }
	//
	// protected Node insertVertexWithEdge(final Node sourceVertex, final String edgeType, final Label label) {
	// final Node targetNode = graphDb.createNode();
	// targetNode.addLabel(label);
	// sourceVertex.createRelationshipTo(targetNode, DynamicRelationshipType.withName(edgeType));
	// return (targetNode);
	// }

	// read

	@Override
	public List<Node> collectVertices(final String type) {
		final ResourceIterable<Node> nodes = GlobalGraphOperations.at(graphDb).getAllNodesWithLabel(DynamicLabel.label(type));

		final ResourceIterator<Node> iterator = nodes.iterator();
		@SuppressWarnings("unchecked")
		final List<Node> list = IteratorUtils.toList(iterator);
		return list;
	}

	// @Override
	// public List<Node> collectOutgoingConnectedVertices(final Node sourceVertex, final String sourceVertexType,
	// final String targetVertexType, final String edgeType) throws IOException {
	// final RelationshipType relationshipType = DynamicRelationshipType.withName(edgeType);
	// final List<Node> neighbors = new ArrayList<Node>();
	// final Iterable<Relationship> relationships = sourceVertex.getRelationships(relationshipType, Direction.OUTGOING);
	// for (final Relationship relationship : relationships) {
	// final Node endNode = relationship.getEndNode();
	// final Iterable<Label> labels = endNode.getLabels();
	// for (final Label label : labels) {
	// if (targetVertexType.equals(label.toString())) {
	// neighbors.add(endNode);
	// }
	// }
	// }
	// return neighbors;
	//
	// }
	//
	// // update
	//
	// @Override
	// public void updateProperties(final List<Node> vertices, final String vertexType, final String propertyName,
	// final PropertyOperation attributeOperation) {
	// for (final Node vertex : vertices) {
	// final Integer propertyValue = (Integer) vertex.getProperty(propertyName);
	// vertex.setProperty(propertyName, attributeOperation.op(propertyValue));
	// }
	// }
	//
	// // delete
	//
	// @Override
	// public void deleteIncomingEdge(final List<Node> vertices, final String sourceVertexType, final String edgeType) throws IOException {
	// deleteEdges(vertices, edgeType, false, true);
	// }
	//
	// @Override
	// public void deleteAllOutgoingEdges(final List<Node> vertices, final String vertexType, final String edgeType) throws IOException {
	// deleteEdges(vertices, edgeType, true, true);
	// }
	//
	// @Override
	// public void deleteOneOutgoingEdge(final List<Node> vertices, final String vertexType, final String edgeType) throws IOException {
	// deleteEdges(vertices, edgeType, true, false);
	// }
	//
	// @Override
	// public void deleteSingleOutgoingEdge(final List<Node> vertices, final String vertexType, final String edgeType) throws IOException {
	// // for Neo4j, this is the same as deleteOneOutgoingEdge
	// deleteEdges(vertices, edgeType, true, true);
	// }
	//
	// protected void deleteEdges(final List<Node> vertices, final String edgeType, final boolean outgoing, final boolean all) {
	// final RelationshipType relationshipType = DynamicRelationshipType.withName(edgeType);
	// final Direction direction = outgoing ? Direction.OUTGOING : Direction.INCOMING;
	//
	// for (final Node vertex : vertices) {
	// final Iterable<Relationship> relationships = vertex.getRelationships(direction, relationshipType);
	//
	// if (all) {
	// for (final Relationship relationship : relationships) {
	// relationship.delete();
	// }
	// } else {
	// // Finding the relationship with the smallest id. This only supports outgoing edges.
	// Relationship firstRelationship = null;
	// for (final Relationship relationship : relationships) {
	// if (firstRelationship == null || relationship.getEndNode().getId() < firstRelationship.getEndNode().getId()) {
	// firstRelationship = relationship;
	// }
	// }
	//
	// if (firstRelationship != null) {
	// firstRelationship.delete();
	// }
	// }
	// }
	// }

	// utility

	public GraphDatabaseService getGraphDb() {
		return graphDb;
	}

	// repair

	@Override
	public void posLengthRepair(final Collection<Neo4jMatch> matches) throws IOException {
		for (final Neo4jMatch match : matches) {
			final Neo4jPosLengthMatch plm = (Neo4jPosLengthMatch) match;
			final Node segment = plm.getSegment();
			final Integer length = (Integer) segment.getProperty(ModelConstants.LENGTH);
			segment.setProperty(LENGTH, -length + 1);
		}
	}

	@Override
	public void routeSensorRepair(final Collection<Neo4jMatch> matches) throws IOException {
		for (final Neo4jMatch match : matches) {
			final Neo4jRouteSensorMatch rsm = (Neo4jRouteSensorMatch) match;
			final Node route = rsm.getRoute();
			final Node sensor = rsm.getSensor();
			route.createRelationshipTo(sensor, definedByEdge);
		}
	}

	@Override
	public void semaphoreNeighborRepair(final Collection<Neo4jMatch> matches) throws IOException {
		for (final Neo4jMatch match : matches) {
			final Neo4jSemaphoreNeighborMatch snm = (Neo4jSemaphoreNeighborMatch) match;
			final Node semaphore = snm.getSemaphore();
			final Node route2 = snm.getRoute2();
			route2.createRelationshipTo(semaphore, entryEdge);
		}
	}

	@Override
	public void switchSensorRepair(final Collection<Neo4jMatch> matches) throws IOException {
		for (final Neo4jMatch match : matches) {
			final Neo4jSwitchSensorMatch ssrm = (Neo4jSwitchSensorMatch) match;
			final Node sw = ssrm.getSw();
			final Node sensor = graphDb.createNode();
			sensor.addLabel(sensorLabel);
			sw.createRelationshipTo(sensor, sensorEdge);
		}
	}

	@Override
	public void switchSetRepair(final Collection<Neo4jMatch> matches) throws IOException {
		for (final Neo4jMatch match : matches) {
			final Neo4jSwitchSetMatch sstm = (Neo4jSwitchSetMatch) match;
			final Node sw = sstm.getSw();
			final Node swP = sstm.getSwP();
			final Object position = swP.getProperty(POSITION);
			sw.setProperty(CURRENTPOSITION, position);
		}
	}

	// user

	@Override
	public void posLengthUser(final Collection<Node> segments) throws IOException {
		for (final Node segment : segments) {
			segment.setProperty(LENGTH, 0);
		}
	}

	@Override
	public void routeSensorUser(final Collection<Node> routes) throws IOException {
		for (final Node route : routes) {
			final Iterable<Relationship> definedBys = route.getRelationships(definedByEdge);
			for (final Relationship definedBy : definedBys) {
				definedBy.delete();
				break;
			}
		}
	}

	@Override
	public void semaphoreNeighborUser(final Collection<Node> routes) throws IOException {
		for (final Node route : routes) {
			final Iterable<Relationship> entries = route.getRelationships(entryEdge);
			for (final Relationship entry : entries) {
				entry.delete();
			}
		}
	}

	@Override
	public void switchSensorUser(final Collection<Node> switches) throws IOException {
		for (final Node sw : switches) {
			final Iterable<Relationship> sensors = sw.getRelationships(sensorEdge);
			for (final Relationship sensor : sensors) {
				sensor.delete();
			}
		}
	}

	@Override
	public void switchSetUser(final Collection<Node> switches) throws IOException {
		for (final Node sw : switches) {

		}
	}

	@Override
	public Comparator<Node> getElementComparator() {
		return nodeComparator;
	}

}
