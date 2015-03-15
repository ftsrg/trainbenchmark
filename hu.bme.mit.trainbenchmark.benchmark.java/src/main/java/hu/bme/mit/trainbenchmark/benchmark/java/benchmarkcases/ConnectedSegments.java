package hu.bme.mit.trainbenchmark.benchmark.java.benchmarkcases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import hu.bme.mit.trainbenchmark.railway.Segment;
import hu.bme.mit.trainbenchmark.railway.Sensor;
import hu.bme.mit.trainbenchmark.railway.TrackElement;

public class ConnectedSegments extends JavaBenchmarkCase<Segment> {

	private int connectedLimit = 5;
	
	@Override
	protected Collection<Segment> check() throws IOException {
		results = new ArrayList<>();
		
		for (final Object eObject : container.getContains()) {
			if (eObject instanceof Sensor){
				final Sensor sensor = (Sensor) eObject;
				
				EList<TrackElement> trackElements = sensor.getSensor_trackElement();
				for (TrackElement element : trackElements){
					if (element instanceof Segment){
						final Segment firstSegment = (Segment) element;
						final Segment lastSegment = getConnected(firstSegment, 0);
						
						if (lastSegment == null){
							continue;
						}
						
						final Sensor nextSensor = lastSegment.getTrackElement_sensor().get(0);
						
						if (sensor == nextSensor){
							results.add(firstSegment);
						}
					}
				}
			}
		}
		
		return results;
	}
	
	
	protected Segment getConnected(final Segment source, int index){
		if (index == connectedLimit){
			return source;
		}

		EList<TrackElement> neighbors = source.getTrackElement_connectsTo();
		for (TrackElement element : neighbors){
			if (element instanceof Segment){
				return getConnected((Segment)element, index+1);
			}
		}
		return null;
	}

}
