/*******************************************************************************
 * Copyright (c) 2010-2015, Gabor Szarnyas, Benedek Izso, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.benchmark.sesame.transformations.repair;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.CURRENTPOSITION;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.POSITION;
import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameSwitchSetMatch;

import java.util.Collection;

public class SesameTransformationRepairSwitchSet extends SesameTransformationRepair<SesameSwitchSetMatch> {

	public SesameTransformationRepairSwitchSet(final SesameDriver sesameDriver) {
		super(sesameDriver);
	}

	@Override
	public void rhs(final Collection<SesameSwitchSetMatch> matches) {
		for (final SesameSwitchSetMatch ssm : matches) {
			final Node sw = ssm.getSw();
			final Node swP = ssm.getSwP();
			final Object position = swP.getProperty(POSITION);
			sw.setProperty(CURRENTPOSITION, position);
		}
	}

}
