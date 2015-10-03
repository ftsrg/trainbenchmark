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
package hu.bme.mit.trainbenchmark.benchmark.hawk.transformation.inject;

import java.io.IOException;
import java.util.Collection;

import hu.bme.mit.trainbenchmark.benchmark.hawk.driver.HawkDriver;
import hu.bme.mit.trainbenchmark.emf.transformation.inject.EMFTransformationInjectConnectedSegments;
import hu.bme.mit.trainbenchmark.railway.Segment;

public class HawkTransformationInjectConnectedSegments extends EMFTransformationInjectConnectedSegments {

	public HawkTransformationInjectConnectedSegments(final HawkDriver driver) {
		super(driver);
	}

	@Override
	public void rhs(final Collection<Segment> segments) throws IOException {
		super.rhs(segments);
		((HawkDriver) driver).persist();
	}

}
