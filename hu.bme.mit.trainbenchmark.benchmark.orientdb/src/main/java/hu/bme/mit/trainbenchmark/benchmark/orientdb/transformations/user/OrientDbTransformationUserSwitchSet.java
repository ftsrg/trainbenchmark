package hu.bme.mit.trainbenchmark.benchmark.orientdb.transformations.user;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.CURRENTPOSITION;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.driver.OrientDbDriver;

import java.util.Collection;

import com.tinkerpop.blueprints.Vertex;

public class OrientDbTransformationUserSwitchSet extends OrientDbTransformationUser {

	public OrientDbTransformationUserSwitchSet(final OrientDbDriver orientDriver) {
		super(orientDriver);
	}

	@Override
	public void rhs(final Collection<Vertex> switches) {
		for (final Vertex sw : switches) {
			final String currentPosition = sw.getProperty(CURRENTPOSITION);
			String nextPosition;
			switch(currentPosition) {
				case "FAILURE":
					nextPosition = "LEFT";
					break;
				case "LEFT":
					nextPosition = "RIGHT";
					break;
				case "RIGHT":
					nextPosition = "STRAIGHT";
					break;
				case "STRAIGHT":
					nextPosition = "FAILURE";
					break;
				default:
					nextPosition = "";
					break;
			}
			
			sw.setProperty(CURRENTPOSITION, nextPosition);
		}
	}
	
}
