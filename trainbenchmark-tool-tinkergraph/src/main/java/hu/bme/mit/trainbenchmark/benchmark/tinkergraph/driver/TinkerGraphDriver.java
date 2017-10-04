package hu.bme.mit.trainbenchmark.benchmark.tinkergraph.driver;

import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;

import java.io.IOException;

public class TinkerGraphDriver extends GraphDriver<TinkerGraph> {

	protected TinkerGraphDriver() throws IOException {
		graph = TinkerGraph.open();
		graph.createIndex(LABEL, Vertex.class); // TODO does this make any difference?
	}

}
