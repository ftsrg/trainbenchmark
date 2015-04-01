package hu.bme.mit.trainbenchmark.benchmark.orientdb.driver;

import java.util.Comparator;

import com.tinkerpop.blueprints.Vertex;

public class VertexComparator implements Comparator<Vertex> {

	@Override
	public int compare(Vertex v1, Vertex v2) {
		final String s1 = v1.getId().toString();
		final String s2 = v2.getId().toString();
		return s1.compareTo(s2);
	}

}
