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
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfSwitchSetMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.EmfTransformation;

import java.util.Collection;

public class EmfTransformationRepairSwitchSet<TDriver extends EmfDriver, TSwitchSetMatch extends EmfSwitchSetMatch>
		extends EmfTransformation<TSwitchSetMatch, TDriver> {

	public EmfTransformationRepairSwitchSet(final TDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<TSwitchSetMatch> matches) {
		for (final EmfSwitchSetMatch match : matches) {
			match.getSw().setCurrentPosition(match.getSwP().getPosition());
		}
	}

}
