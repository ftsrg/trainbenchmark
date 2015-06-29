package hu.bme.mit.trainbenchmark.benchmark.orientdb.transformations.user;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.CURRENTPOSITION;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.driver.OrientDbDriver;
import hu.bme.mit.trainbenchmark.constants.Position;

import java.util.Collection;

import com.tinkerpop.blueprints.Vertex;

public class OrientDbTransformationUserSwitchSet extends OrientDbTransformationUser {

	public OrientDbTransformationUserSwitchSet(final OrientDbDriver orientDriver) {
		super(orientDriver);
	}

	@Override
	public void rhs(final Collection<Vertex> switches) {
		for (final Vertex sw : switches) {
			final String currentPositionString = sw.getProperty(CURRENTPOSITION);
			final Position currentPosition = Position.valueOf(currentPositionString);
			final Position newCurrentPosition = Position.values()[(currentPosition.ordinal() + 1) % Position.values().length];			
			sw.setProperty(CURRENTPOSITION, newCurrentPosition.toString());
		}
	}
	
}
