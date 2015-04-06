package hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.repair;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.TransformationDefinition;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RepairConnectedSegments<T> extends TransformationDefinition<T> {

    @Override
    protected void lhs() throws IOException {
	elementCandidates = currentResults;
    }

    @Override
    protected void rhs() throws IOException {

	for (final T vertex : elementsToModify) {
	    List<T> oneHopConnected = new ArrayList<T>();
	    List<T> vertices = new ArrayList<T>();
	    // get the first and only one vertex
	    T oneHopVertex = driver.collectOutgoingConnectedVertices(vertex,
		    ModelConstants.SEGMENT, ModelConstants.SEGMENT,
		    ModelConstants.CONNECTSTO).get(0);
	    T twoHopVertex = driver.collectOutgoingConnectedVertices(
		    oneHopVertex, ModelConstants.SEGMENT,
		    ModelConstants.SEGMENT, ModelConstants.CONNECTSTO).get(0);

	    driver.insertEdge(vertex, ModelConstants.SEGMENT, twoHopVertex,
		    ModelConstants.CONNECTSTO);

	    vertices.add(vertex);
	    driver.deleteAllOutgoingEdges(vertices, ModelConstants.SEGMENT,
		    ModelConstants.CONNECTSTO);

	    oneHopConnected.add(oneHopVertex);

	    driver.deleteAllOutgoingEdges(oneHopConnected,
		    ModelConstants.SEGMENT, ModelConstants.CONNECTSTO);

	    driver.deleteAllOutgoingEdges(oneHopConnected,
		    ModelConstants.SEGMENT, ModelConstants.SENSOR_EDGE);

	    driver.deleteVertex(oneHopVertex, ModelConstants.SEGMENT);
	}
    }

}
