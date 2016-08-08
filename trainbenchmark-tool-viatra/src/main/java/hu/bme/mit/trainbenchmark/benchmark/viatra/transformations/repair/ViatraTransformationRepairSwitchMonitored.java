/*******************************************************************************
 * Copyright (c) 2010-2015, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.benchmark.viatra.transformations.repair;

import java.io.IOException;
import java.util.Collection;

import hu.bme.mit.trainbenchmark.benchmark.viatra.SwitchMonitoredMatch;
import hu.bme.mit.trainbenchmark.benchmark.viatra.driver.ViatraDriver;
import hu.bme.mit.trainbenchmark.benchmark.viatra.transformations.ViatraTransformation;
import hu.bme.mit.trainbenchmark.railway.RailwayFactory;
import hu.bme.mit.trainbenchmark.railway.Region;
import hu.bme.mit.trainbenchmark.railway.Sensor;

public class ViatraTransformationRepairSwitchMonitored extends ViatraTransformation<SwitchMonitoredMatch> {

	private static final String Region = null;

	public ViatraTransformationRepairSwitchMonitored(final ViatraDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<SwitchMonitoredMatch> matches) throws IOException {
		for (final SwitchMonitoredMatch match : matches) {
			final Sensor sensor = RailwayFactory.eINSTANCE.createSensor();
			match.getSw().getMonitoredBy().add(sensor);
			final Region region = (Region) match.getSw().eContainer();
			region.getSensors().add(sensor);
		}
	}

}
