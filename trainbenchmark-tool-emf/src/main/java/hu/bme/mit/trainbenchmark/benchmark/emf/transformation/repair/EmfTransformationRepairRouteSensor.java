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

import hu.bme.mit.trainbenchmark.benchmark.emf.driver.EmfDriver;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfRouteSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.EmfTransformation;

import java.util.Collection;

public class EmfTransformationRepairRouteSensor<TDriver extends EmfDriver, TRouteSensorMatch extends EmfRouteSensorMatch>
		extends EmfTransformation<TRouteSensorMatch, TDriver> {

	public EmfTransformationRepairRouteSensor(final TDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<TRouteSensorMatch> matches) {
		for (final EmfRouteSensorMatch match : matches) {
			match.getRoute().getRequires().add(match.getSensor());
		}
	}

}
