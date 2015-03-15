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

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.AttributeOperation;
import hu.bme.mit.trainbenchmark.benchmark.driver.DatabaseDriver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLStreamException;

import org.apache.commons.collections.IteratorUtils;
import org.apache.commons.io.FileUtils;
import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.cypher.javacompat.ExecutionResult;
import org.neo4j.graphdb.Direction;
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

public class Neo4jDriver extends DatabaseDriver<Node> {

	protected Transaction tx;
	protected GraphDatabaseService graphDb;
	protected String dbPath;
	protected String query;

	public Neo4jDriver(final String dbPath, final String query) throws IOException {
		// start with a clean slate: delete old directory
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

	@Override
	public List<Node> runQuery() throws IOException {
		final List<Node> results = new ArrayList<>();

		try (Transaction tx = graphDb.beginTx()) {
			final ExecutionEngine engine = new ExecutionEngine(graphDb);
			final ExecutionResult result = engine.execute(query);
			for (final Map<String, Object> row : result) {
				final Node x = (Node) row.get(result.columns().get(0));
				results.add(x);
			}
		}

		return results;
	}

	@Override
	public Comparator<Node> getComparator() {
		return new NodeComparator();
	}

	@Override
	public void destroy() {
		graphDb.shutdown();
	}

	// create

	@Override
	public void insertEdge(final Node sourceVertex, final String sourceVertexType,
			Node targetVertex, String edgeType)  throws IOException{
		final RelationshipType relationship = DynamicRelationshipType.withName(edgeType);
		sourceVertex.createRelationshipTo(targetVertex, relationship);
	}
	
	@Override
	public void insertVertexWithEdge(final List<Node> vertices, final String sourceVertexType, final String targetVertexType,
			final String edgeType) throws IOException {
		final Label label = DynamicLabel.label(targetVertexType);
		for (final Node vertex : vertices) {
			insertVertexWithEdge(vertex, edgeType, label);
		}
	}
	
	@Override
	public Node insertVertexWithEdge(Node sourceVertex,String sourceVertexType, String targetVertexType, String edgeType)throws IOException {
		final Label label = DynamicLabel.label(targetVertexType);
		return (insertVertexWithEdge(sourceVertex, edgeType, label));
		
	}

	protected Node insertVertexWithEdge(final Node sourceVertex,final String edgeType, final Label label) {
		final Node targetNode = graphDb.createNode();
		targetNode.addLabel(label);
		sourceVertex.createRelationshipTo(targetNode, DynamicRelationshipType.withName(edgeType));
		return (targetNode);
	}
	
	// read

	@Override
	public List<Node> collectVertices(final String type) {
		final ResourceIterable<Node> nodes = GlobalGraphOperations.at(graphDb).getAllNodesWithLabel(DynamicLabel.label(type));

		final ResourceIterator<Node> iterator = nodes.iterator();
		@SuppressWarnings("unchecked")
		final List<Node> list = IteratorUtils.toList(iterator);
		return list;
	}
	
	
	@Override
	public List<Node> collectOutgoingConnectedVertices(final Node sourceVertex, final String sourceVertexType,
			final String targetVertexType, final String edgeType) throws IOException {
		final RelationshipType relationshipType = DynamicRelationshipType.withName(edgeType);
		List<Node> neighbors = new ArrayList<Node>();
		final Iterable<Relationship> relationships = sourceVertex.getRelationships(
																			relationshipType,
																			Direction.OUTGOING 
																			);
		for (final Relationship relationship : relationships) {
			final Node endNode = relationship.getEndNode();
			final Iterable<Label> labels = endNode.getLabels();
			for (Label label : labels){
				if (targetVertexType.equals(label.toString())){
					neighbors.add(endNode);
				}
			}
		}
		return neighbors;

	}
	
	// update

	@Override
	public void updateProperties(final List<Node> vertices, final String vertexType, final String propertyName,
			final AttributeOperation attributeOperation) {
		for (final Node vertex : vertices) {
			final Integer propertyValue = (Integer) vertex.getProperty(propertyName);
			vertex.setProperty(propertyName, attributeOperation.op(propertyValue));
		}
	}

	// delete

	@Override
	public void deleteAllIncomingEdges(final List<Node> vertices, final String sourceVertexType, final String edgeType) throws IOException {
		deleteEdges(vertices, edgeType, false, true);
	}

	@Override
	public void deleteAllOutgoingEdges(final List<Node> vertices, final String vertexType, final String edgeType) throws IOException {
		deleteEdges(vertices, edgeType, true, true);
	}

	@Override
	public void deleteOneOutgoingEdge(final List<Node> vertices, final String vertexType, final String edgeType) throws IOException {
		deleteEdges(vertices, edgeType, true, false);
	}

	@Override
	public void deleteSingleOutgoingEdge(final List<Node> vertices, final String vertexType, final String edgeType) throws IOException {
		// for Neo4j, this is the same as deleteOneOutgoingEdge
		deleteEdges(vertices, edgeType, true, true);
	}
	
	protected void deleteEdges(final List<Node> vertices, final String edgeType, final boolean outgoing, final boolean all) {
		final RelationshipType relationshipType = DynamicRelationshipType.withName(edgeType);
		final Direction direction = outgoing ? Direction.OUTGOING : Direction.INCOMING;

		for (final Node vertex : vertices) {
			final Iterable<Relationship> relationships = vertex.getRelationships(direction, relationshipType);

			for (final Relationship relationship : relationships) {
				relationship.delete();

				// break if we only want to delete one edge
				if (!all) {
					break;
				}
			}
		}
	}

	@Override
	public void deleteVertex(final Node vertex, final String vertexType) throws IOException {
		vertex.delete();
	}
	
	// utility

	public GraphDatabaseService getGraphDb() {
		return graphDb;
	}

}
