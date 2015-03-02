package hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.TransformationDefinition;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;

public class ConnectedSegments<T> extends TransformationDefinition<T>{

	@Override
	protected void lhs() throws IOException {
		elementCandidates = driver.collectVertices(ModelConstants.SEGMENT);
	}

	@Override
	protected void rhs() throws IOException {
		// TODO Auto-generated method stub
//		for (final T vertex : elementsToModify){
//			List<T> neighbors = driver.collectOutgoingConnectedVertices(vertex, ModelConstants.SEGMENT, ModelConstants.TRACKELEMENT_CONNECTSTO);
//			T inserted = driver.insertVertexWithEdge(vertex, ModelConstants.SEGMENT, 
//													 ModelConstants.SEGMENT, ModelConstants.TRACKELEMENT_CONNECTSTO);
//			List<T> source = new ArrayList<T>();
//			source.add(vertex);
//			driver.deleteAllOutgoingEdges(source, ModelConstants.SEGMENT, ModelConstants.TRACKELEMENT_CONNECTSTO);
//			
		}
	}


	
}
