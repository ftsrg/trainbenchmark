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
package hu.bme.mit.trainbenchmark.benchmark.hawk.transformation.repair;

import java.io.IOException;
import java.util.Collection;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.ConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.transformations.repair.EMFIncQueryTransformationRepairConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.hawk.driver.HawkDriver;

public class HawkTransformationRepairConnectedSegments extends EMFIncQueryTransformationRepairConnectedSegments {

	public HawkTransformationRepairConnectedSegments(final HawkDriver<?> driver) {
		super(driver);
	}

	@Override
	public void rhs(final Collection<ConnectedSegmentsMatch> matches) throws IOException {
		super.rhs(matches);
		((HawkDriver<?>) driver).persist();
	}

}
