package hu.bme.mit.trainbenchmark.benchmark.neo4j.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.DynamicRelationshipType;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;

public class Neo4jUtil {
	
	public static boolean isConnected(final Node source, final Node target, final DynamicRelationshipType relationshipType) {
		final Iterator<Relationship> edges = source.getRelationships(Direction.OUTGOING, relationshipType).iterator();

		while (edges.hasNext()) {
			final Node endNode = edges.next().getEndNode();
			if (target.equals(endNode)) {
				return true;
			}
		}

		return false;
	}

	public static Iterable<Node> getAdjacentNodes(final Node sourceNode, final RelationshipType relationshipType, final Direction direction, final Label targetNodeLabel) {
		final Collection<Node> nodes = new ArrayList<>();
		
		final Iterable<Relationship> relationships = sourceNode.getRelationships(relationshipType, direction);
		for (final Relationship relationship : relationships) {
			final Node candidate;
			switch (direction) {
			case INCOMING:
				candidate = relationship.getStartNode();
				break;
			case OUTGOING:
				candidate = relationship.getEndNode();			
				break;
			default:
				throw new UnsupportedOperationException("Direction: " + direction + " not supported.");
			}
			if (!candidate.hasLabel(targetNodeLabel)) {
				continue;
			}
			nodes.add(candidate);
		}
		return nodes;
	}
	
}
