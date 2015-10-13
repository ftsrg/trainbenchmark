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
package hu.bme.mit.trainbenchmark.benchmark.emfincquery.checker;

import org.eclipse.incquery.runtime.api.IncQueryMatcher;
import org.eclipse.incquery.runtime.exception.IncQueryException;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.ConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.ConnectedSegmentsMatcher;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.config.EMFIncQueryBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.driver.EMFIncQueryBaseDriver;

public class EMFIncQueryConnectedSegmentsChecker extends EMFIncQueryChecker<ConnectedSegmentsMatch> {

	public EMFIncQueryConnectedSegmentsChecker(final EMFIncQueryBenchmarkConfig eiqbc,
			final EMFIncQueryBaseDriver<ConnectedSegmentsMatch, EMFIncQueryBenchmarkConfig> eiqDriver) {
		super(eiqbc, eiqDriver);
	}

	@Override
	public IncQueryMatcher<ConnectedSegmentsMatch> getMatcher() throws IncQueryException {
		return ConnectedSegmentsMatcher.on(eiqDriver.getEngine());
	}

}
