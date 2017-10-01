package hu.bme.mit.trainbenchmark.benchmark.tinkergraph.driver;

import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.io.IoCore;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;

import java.io.IOException;

public class TinkerGraphDriver extends GraphDriver<TinkerGraph> {

	protected TinkerGraphDriver() throws IOException {
	}

	@Override
	public void read(String modelPath) throws Exception {
		graph = TinkerGraph.open();
		graph.createIndex(LABEL, Vertex.class);
		graph.io(IoCore.graphml()).readGraph(modelPath);
	}

}
