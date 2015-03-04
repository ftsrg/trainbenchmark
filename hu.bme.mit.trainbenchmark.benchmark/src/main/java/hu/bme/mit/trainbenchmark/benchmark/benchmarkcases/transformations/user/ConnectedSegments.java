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
		for (final T vertex : elementsToModify){
			List<T> segments = driver.collectOutgoingConnectedVertices(vertex, ModelConstants.SEGMENT, 
																				ModelConstants.TRACKELEMENT_CONNECTSTO);
			List <T> switchElements = driver.collectOutgoingConnectedVertices(vertex, ModelConstants.SWITCH, 
																				ModelConstants.TRACKELEMENT_CONNECTSTO);
			List <T> sensors = driver.collectOutgoingConnectedVertices(vertex, ModelConstants.SENSOR, 
																				ModelConstants.TRACKELEMENT_SENSOR);
			
			List<T> source = new ArrayList<T>();
			source.add(vertex);
			driver.deleteAllOutgoingEdges(source, ModelConstants.SEGMENT, ModelConstants.TRACKELEMENT_CONNECTSTO);
			
			T inserted = driver.insertVertexWithEdge(vertex, ModelConstants.SEGMENT, 
													 ModelConstants.SEGMENT, ModelConstants.TRACKELEMENT_CONNECTSTO);
			
			if (segments.size() > 0){
				driver.insertEdge(inserted, segments.get(0), ModelConstants.TRACKELEMENT_CONNECTSTO);
			}
			if (switchElements.size() > 0){
				driver.insertEdge(inserted, switchElements.get(0), ModelConstants.TRACKELEMENT_CONNECTSTO);
			}
			driver.insertEdge(inserted, sensors.get(0), ModelConstants.TRACKELEMENT_SENSOR);
		}
	}


	
}
