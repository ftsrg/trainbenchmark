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
package hu.bme.mit.trainbenchmark.benchmark.emfincquery.transformations.repair;

import java.io.IOException;
import java.util.Collection;

import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SwitchSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.driver.EMFIncQueryBaseDriver;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.transformations.EMFIncQueryTransformation;
import hu.bme.mit.trainbenchmark.railway.RailwayFactory;
import hu.bme.mit.trainbenchmark.railway.Sensor;

public class EMFIncQueryTransformationRepairSwitchSensor extends EMFIncQueryTransformation<SwitchSensorMatch> {

	public EMFIncQueryTransformationRepairSwitchSensor(final EMFIncQueryBaseDriver<? extends BasePatternMatch> driver) {
		super(driver);
	}

	@Override
	public void rhs(final Collection<SwitchSensorMatch> matches) throws IOException {
		for (final SwitchSensorMatch match : matches) {
			final Sensor sensor = RailwayFactory.eINSTANCE.createSensor();
			match.getSw().setSensor(sensor);
			driver.getContainer().getInvalids().add(sensor);

			// TODO add unique id
		}
	}

}
