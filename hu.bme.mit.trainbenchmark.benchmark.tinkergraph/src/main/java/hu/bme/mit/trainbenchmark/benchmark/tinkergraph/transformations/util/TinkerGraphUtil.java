package hu.bme.mit.trainbenchmark.benchmark.tinkergraph.transformations.util;

import org.apache.tinkerpop.gremlin.structure.Direction;
import org.apache.tinkerpop.gremlin.structure.Vertex;

public class TinkerGraphUtil {

	public static boolean isConnected(final Vertex source, final Vertex target, final String type) {
		final Iterable<Vertex> vertices = () -> source.vertices(Direction.OUT, type);
		for (final Vertex vertex : vertices) {
			if (vertex.equals(target)) {
				return true;
			}
		}
		return false;
	}
	
}
