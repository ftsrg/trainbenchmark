package hu.bme.mit.trainbenchmark.benchmark.neo4j.driver;

import java.util.Comparator;

import org.neo4j.graphdb.Node;

public class NodeComparator implements Comparator<Node> {

	@Override
	public int compare(final Node node1, final Node node2) {
		final long id1 = node1.getId();
		final long id2 = node2.getId();
		return Long.compare(id1, id2);
	}

}
