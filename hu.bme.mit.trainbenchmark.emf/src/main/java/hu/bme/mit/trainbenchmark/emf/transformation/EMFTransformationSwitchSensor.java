package hu.bme.mit.trainbenchmark.emf.transformation;

import hu.bme.mit.trainbenchmark.railway.RailwayElement;
import hu.bme.mit.trainbenchmark.railway.Switch;

import java.util.Collection;

public class EMFTransformationSwitchSensor extends EMFTransformationAction {

	@Override
	public void transform(final Collection<RailwayElement> switches) {
		for (final RailwayElement railwayElement : switches) {
			final Switch sw = (Switch) railwayElement;
			sw.setSensor(null);
			driver.getContainer().getInvalids().add(sw);
		}
	}

}
