package hu.bme.mit.trainbenchmark.benchmark.orientdb.driver;

import java.util.Comparator;

import com.tinkerpop.blueprints.Vertex;

public class VertexComparator implements Comparator<Vertex> {

	public int compare(Vertex vertex1, Vertex vertex2) {
		final String id1 = vertex1.getId().toString();
		final String id2 = vertex2.getId().toString();
		return id1.compareTo(id2);
	}
}
