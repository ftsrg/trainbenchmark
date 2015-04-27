package hu.bme.mit.trainbenchmark.emf.transformation.user;

import hu.bme.mit.trainbenchmark.emf.EMFDriver;
import hu.bme.mit.trainbenchmark.railway.RailwayElement;
import hu.bme.mit.trainbenchmark.railway.Switch;

import java.util.Collection;

public class EMFUserTransformationSwitchSensor extends EMFUserTransformation {

	public EMFUserTransformationSwitchSensor(final EMFDriver driver) {
		super(driver);
	}

	@Override
	public void rhs(final Collection<RailwayElement> switches) {
		for (final Object railwayElement : switches) {
			final Switch sw = (Switch) railwayElement;
			sw.setSensor(null);
			driver.getContainer().getInvalids().add(sw);
		}
	}

}
