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

import java.io.IOException;
import java.util.List;

import org.apache.commons.collections.IteratorUtils;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.DynamicRelationshipType;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.ResourceIterable;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;
import org.neo4j.tooling.GlobalGraphOperations;

public class Neo4jDriver extends DatabaseDriver {

	protected Transaction tx;
	protected GraphDatabaseService graphDb;

	public Neo4jDriver(final GraphDatabaseService graphDb) {
		this.graphDb = graphDb;
	}

	@Override
	public List<? extends Object> collectVertices(final String type) {
		final ResourceIterable<Node> nodes = GlobalGraphOperations.at(graphDb).getAllNodesWithLabel(DynamicLabel.label(type));

		final ResourceIterator<Node> iterator = nodes.iterator();
		final List<? extends Object> list = IteratorUtils.toList(iterator);

		return list;
	}

	@Override
	public void insertVertexWithEdge(final Object sourceVertex, final String sourceVertexType, final String targetVertexType,
			final String edgeType) throws IOException {
		final Node sourceNode = (Node) sourceVertex;
		final Node targetNode = graphDb.createNode();

		// automatic indexing ensures that the new node will be indexed by its type attribute
		targetNode.addLabel(DynamicLabel.label(targetVertexType));
		sourceNode.createRelationshipTo(targetNode, DynamicRelationshipType.withName(edgeType));
	}

	@Override
	public void deleteAllOutgoingEdges(final Object vertex, final String edgeType) throws IOException {
		deleteEdges(vertex, edgeType, true, true);
	}

	@Override
	public void deleteAllIncomingEdges(final Object vertex, final String edgeType, final String sourceVertexType) throws IOException {
		deleteEdges(vertex, edgeType, false, true);
	}

	@Override
	public void deleteOneOutgoingEdge(final Object vertex, final String edgeType) throws IOException {
		deleteEdges(vertex, edgeType, true, false);
	}

	@Override
	public void deleteOutgoingEdge(final Object vertex, final String vertexType, final String edgeType) throws IOException {
		// for Neo4j, this is the same as deleteOneOutgoingEdge
		deleteEdges(vertex, edgeType, true, true);		
	}
	
	protected void deleteEdges(final Object vertex, final String edgeType, final boolean outgoing, final boolean all) {
		final Node node = (Node) vertex;
		final RelationshipType relationshipType = DynamicRelationshipType.withName(edgeType);
		final Direction direction = outgoing ? Direction.OUTGOING : Direction.INCOMING;
		final Iterable<Relationship> relationships = node.getRelationships(direction, relationshipType);

		for (final Relationship relationship : relationships) {
			relationship.delete();
			
			// break if we only want to delete one edge
			if (!all) {
				break;
			}
		}
	}

	@Override
	public void updateProperty(final Object vertex, final String vertexType, final String propertyName,
			final AttributeOperation attributeOperation) {
		final Node node = (Node) vertex;
		final Integer propertyValue = (Integer) node.getProperty(propertyName);
		node.setProperty(propertyName, attributeOperation.op(propertyValue));
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
	public void destroy() {
		graphDb.shutdown();
	}


}
