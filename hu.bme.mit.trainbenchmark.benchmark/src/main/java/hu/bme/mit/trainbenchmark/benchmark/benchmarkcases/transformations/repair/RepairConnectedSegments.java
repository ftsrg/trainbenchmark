package hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.repair;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.RepairTransformation;

import java.io.IOException;

public class RepairConnectedSegments<M, T> extends RepairTransformation<M, T> {

	@Override
	protected void rhs() throws IOException {
		// for (final T vertex : elementsToModify) {
		// final List<T> oneHopConnected = new ArrayList<T>();
		// final List<T> vertices = new ArrayList<T>();
		// // get the first and only one vertex
		// final T oneHopVertex = driver.collectOutgoingConnectedVertices(vertex, ModelConstants.SEGMENT, ModelConstants.SEGMENT,
		// ModelConstants.CONNECTSTO).get(0);
		// final T twoHopVertex = driver.collectOutgoingConnectedVertices(oneHopVertex, ModelConstants.SEGMENT, ModelConstants.SEGMENT,
		// ModelConstants.CONNECTSTO).get(0);
		//
		// driver.insertEdge(vertex, ModelConstants.SEGMENT, twoHopVertex, ModelConstants.CONNECTSTO);
		//
		// vertices.add(vertex);
		// driver.deleteAllOutgoingEdges(vertices, ModelConstants.SEGMENT, ModelConstants.CONNECTSTO);
		//
		// oneHopConnected.add(oneHopVertex);
		//
		// driver.deleteAllOutgoingEdges(oneHopConnected, ModelConstants.SEGMENT, ModelConstants.CONNECTSTO);
		//
		// driver.deleteAllOutgoingEdges(oneHopConnected, ModelConstants.SEGMENT, ModelConstants.SENSOR_EDGE);
		//
		// driver.deleteVertex(oneHopVertex, ModelConstants.SEGMENT);
		// }
	}

}
