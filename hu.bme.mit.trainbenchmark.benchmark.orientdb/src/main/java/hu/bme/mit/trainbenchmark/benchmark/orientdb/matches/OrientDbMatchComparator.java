package hu.bme.mit.trainbenchmark.benchmark.orientdb.matches;

import com.tinkerpop.blueprints.Vertex;

import hu.bme.mit.trainbenchmark.benchmark.matches.MatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.driver.VertexComparator;

public class OrientDbMatchComparator extends MatchComparator<OrientDbMatch, Vertex> {

	protected VertexComparator vc = new VertexComparator();
	
	@Override
	public int compare(final OrientDbMatch o1, final OrientDbMatch o2) {
		final Vertex[] m1 = o1.toArray();
		final Vertex[] m2 = o2.toArray();
		return compareArrays(m1, m2, vc);
	}
}
