package hu.bme.mit.trainbenchmark.benchmark.tinkergraph.transformations.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

import org.apache.tinkerpop.gremlin.structure.Direction;
import org.apache.tinkerpop.gremlin.structure.Vertex;

public class TinkerGraphUtil {

	/**
	 * The trick used in the
	 * hu.bme.mit.trainbenchmark.benchmark.neo4j.util.Neo4jUtil#isConnected()
	 * method will not work here as TinkerPop's Vertex class does not provide an
	 * interface for efficiently accessing the degree (w.r.t. a certain
	 * relationship type/direction) of the node.
	 * 
	 * @param source
	 * @param target
	 * @param edgeLabel
	 * @return
	 */
	public static boolean isConnected(final Vertex source, final Vertex target, final String edgeLabel) {
		final Iterable<Vertex> vertices = () -> source.vertices(Direction.OUT, edgeLabel);
		for (final Vertex vertex : vertices) {
			if (vertex.equals(target)) {
				return true;
			}
		}
		return false;
	}

	public static Iterable<Vertex> getAdjacentNodes(final Vertex source, final String edgeLabel,
			final Direction direction, final String[] targetVertexLabels) {
		final Collection<Vertex> vertices = new LinkedList<>();
		final Iterable<Vertex> candidateVertices = () -> source.vertices(direction, edgeLabel);
		for (final Vertex candidateVertex : candidateVertices) {
			if (!Arrays.asList(targetVertexLabels).contains(candidateVertex.label())) {
				continue;
			}
			vertices.add(candidateVertex);
		}
		return vertices;
	}

	public static Iterable<Vertex> getAdjacentNodes(final Vertex source, final String edgeLabel,
			final Direction direction, final String targetVertexLabel) {
		return getAdjacentNodes(source, edgeLabel, direction, new String[] { targetVertexLabel });
	}

}
