package hu.bme.mit.trainbenchmark.emf.transformation;

import hu.bme.mit.trainbenchmark.railway.Position;
import hu.bme.mit.trainbenchmark.railway.RailwayElement;
import hu.bme.mit.trainbenchmark.railway.Switch;

import java.util.Collection;

public class EMFTransformationSwitchSet extends EMFTransformationAction {

	@Override
	public void transform(final Collection<RailwayElement> switches) {
		for (final RailwayElement railwayElement : switches) {
			final Switch sw = (Switch) railwayElement;
			final Position currentPosition = sw.getCurrentPosition();
			final Position newCurrentPosition = Position.get((currentPosition.ordinal() + 1) % Position.VALUES.size());
			sw.setCurrentPosition(newCurrentPosition);
		}
	}

}
