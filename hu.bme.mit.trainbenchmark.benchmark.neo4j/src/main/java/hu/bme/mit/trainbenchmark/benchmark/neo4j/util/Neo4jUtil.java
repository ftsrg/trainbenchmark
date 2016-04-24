package hu.bme.mit.trainbenchmark.benchmark.neo4j.util;

import java.util.Iterator;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.DynamicRelationshipType;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

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
	
}
