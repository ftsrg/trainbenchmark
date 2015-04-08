package hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.user;

import hu.bme.mit.trainbenchmark.constants.ModelConstants;

import java.io.IOException;

public class UserConnectedSegments<M, T> extends UserTransformation<M, T> {

	@Override
	protected void lhs() throws IOException {
		candidatesToModify = driver.collectVertices(ModelConstants.SEGMENT);
	}

	@Override
	protected void rhs() throws IOException {
		// for (final T vertex : elementsToModify) {
		// final List<T> segments = driver.collectOutgoingConnectedVertices(vertex, ModelConstants.SEGMENT, ModelConstants.SEGMENT,
		// ModelConstants.CONNECTSTO);
		// final List<T> switchElements = driver.collectOutgoingConnectedVertices(vertex, ModelConstants.SEGMENT, ModelConstants.SWITCH,
		// ModelConstants.CONNECTSTO);
		// final List<T> sensors = driver.collectOutgoingConnectedVertices(vertex, ModelConstants.SEGMENT, ModelConstants.SENSOR,
		// ModelConstants.SENSOR_EDGE);
		//
		// final List<T> source = new ArrayList<T>();
		// source.add(vertex);
		// driver.deleteAllOutgoingEdges(source, ModelConstants.SEGMENT, ModelConstants.CONNECTSTO);
		//
		// final T inserted = driver.insertVertexWithEdge(vertex, ModelConstants.SEGMENT, ModelConstants.SEGMENT,
		// ModelConstants.CONNECTSTO);
		//
		// if (segments.size() > 0) {
		// driver.insertEdge(inserted, ModelConstants.SEGMENT, segments.get(0), ModelConstants.CONNECTSTO);
		// }
		// if (switchElements.size() > 0) {
		// driver.insertEdge(inserted, ModelConstants.SEGMENT, switchElements.get(0), ModelConstants.CONNECTSTO);
		// }
		// driver.insertEdge(inserted, ModelConstants.SEGMENT, sensors.get(0), ModelConstants.SENSOR_EDGE);
		// }
	}

}
