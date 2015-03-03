package hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.repair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.TransformationDefinition;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;

public class ConnectedSegments<T> extends TransformationDefinition<T>{

	@Override
	protected void lhs() throws IOException {
		elementCandidates = currentResults;
	}

	@Override
	protected void rhs() throws IOException {
		List<T> oneHopConnected = new ArrayList<T>();
		for(final T vertex : elementsToModify){
			// get the first and only one vertex
			T oneHopVertex = driver.collectOutgoingFilteredConnectedVertices(vertex, ModelConstants.SEGMENT, ModelConstants.TRACKELEMENT_CONNECTSTO).get(0);
			T twoHopVertex = driver.collectOutgoingFilteredConnectedVertices(oneHopVertex, ModelConstants.SEGMENT, ModelConstants.TRACKELEMENT_CONNECTSTO).get(0);
		
			driver.insertEdge(vertex, twoHopVertex, ModelConstants.TRACKELEMENT_CONNECTSTO);
			
			oneHopConnected.add(oneHopVertex);
		}
		driver.deleteAllOutgoingEdges(oneHopConnected, ModelConstants.SEGMENT, ModelConstants.TRACKELEMENT_CONNECTSTO);
		driver.deleteAllOutgoingEdges(oneHopConnected, ModelConstants.SEGMENT, ModelConstants.TRACKELEMENT_SENSOR);
		driver.deleteAllIncomingEdges(oneHopConnected, ModelConstants.SEGMENT, ModelConstants.TRACKELEMENT_CONNECTSTO);
	}


	
}
