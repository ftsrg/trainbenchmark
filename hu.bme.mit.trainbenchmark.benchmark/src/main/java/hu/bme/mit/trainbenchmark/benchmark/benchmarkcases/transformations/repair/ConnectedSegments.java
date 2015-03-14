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
		
		for(final T vertex : elementsToModify){
			List<T> oneHopConnected = new ArrayList<T>();
			List<T> vertices = new ArrayList<T>();
			// get the first and only one vertex
			System.out.println("first collect");
			T oneHopVertex = driver.collectOutgoingConnectedVertices(vertex, ModelConstants.SEGMENT, ModelConstants.SEGMENT, ModelConstants.TRACKELEMENT_CONNECTSTO).get(0);
			System.out.println("second collect");
			T twoHopVertex = driver.collectOutgoingConnectedVertices(oneHopVertex, ModelConstants.SEGMENT, ModelConstants.SEGMENT, ModelConstants.TRACKELEMENT_CONNECTSTO).get(0);
		
			System.out.println("Insert edge:");
			
			driver.insertEdge(vertex, ModelConstants.SEGMENT, twoHopVertex, ModelConstants.TRACKELEMENT_CONNECTSTO);
			
			
			
			vertices.add(vertex);
			driver.deleteAllOutgoingEdges(vertices, ModelConstants.SEGMENT, ModelConstants.TRACKELEMENT_CONNECTSTO);
			
			oneHopConnected.add(oneHopVertex);
			
			System.out.println("First delete output:");
			
			driver.deleteAllOutgoingEdges(oneHopConnected, ModelConstants.SEGMENT, ModelConstants.TRACKELEMENT_CONNECTSTO);
			
			System.out.println("Second delete output:");
			
			driver.deleteAllOutgoingEdges(oneHopConnected, ModelConstants.SEGMENT, ModelConstants.TRACKELEMENT_SENSOR);
			
			System.out.println("First delete input:");
			
			//driver.deleteAllIncomingEdges(oneHopConnected, ModelConstants.SEGMENT, ModelConstants.TRACKELEMENT_CONNECTSTO);
		}
//		driver.deleteAllOutgoingEdges(oneHopConnected, ModelConstants.SEGMENT, ModelConstants.TRACKELEMENT_CONNECTSTO);
//		driver.deleteAllOutgoingEdges(oneHopConnected, ModelConstants.SEGMENT, ModelConstants.TRACKELEMENT_SENSOR);
//		driver.deleteAllIncomingEdges(oneHopConnected, ModelConstants.SEGMENT, ModelConstants.TRACKELEMENT_CONNECTSTO);
	}


	
}
