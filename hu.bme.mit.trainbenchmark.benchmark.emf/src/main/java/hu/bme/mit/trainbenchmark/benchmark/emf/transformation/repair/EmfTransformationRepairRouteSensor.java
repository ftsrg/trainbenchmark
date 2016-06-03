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
package hu.bme.mit.trainbenchmark.benchmark.emf.transformation.repair;

import java.util.Collection;

import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfRouteSensorMatch;
import hu.bme.mit.trainbenchmark.emf.EmfDriver;

public class EmfTransformationRepairRouteSensor<TDriver extends EmfDriver> extends EmfTransformationRepair<EmfRouteSensorMatch, TDriver> {

	public EmfTransformationRepairRouteSensor(TDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<EmfRouteSensorMatch> matches) {
		for (final EmfRouteSensorMatch rsm : matches) {
			rsm.getRoute().getGathers().add(rsm.getSensor());
		}
	}

}
