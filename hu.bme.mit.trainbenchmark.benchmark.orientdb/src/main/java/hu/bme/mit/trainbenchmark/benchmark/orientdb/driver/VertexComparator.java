package hu.bme.mit.trainbenchmark.benchmark.orientdb.driver;

import java.util.Comparator;

import com.tinkerpop.blueprints.Vertex;

public class VertexComparator implements Comparator<Vertex> {

	public int compare(Vertex vertex1, Vertex vertex2) {
		final long id1 = Long.parseLong(vertex1.getId().toString().split(":")[1]);
		final long id2 = Long.parseLong(vertex2.getId().toString().split(":")[1]);
		return Long.compare(id1, id2);
	}
}
