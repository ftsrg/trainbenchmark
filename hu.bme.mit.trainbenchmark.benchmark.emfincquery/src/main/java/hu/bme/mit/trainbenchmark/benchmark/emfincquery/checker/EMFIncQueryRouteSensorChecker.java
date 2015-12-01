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

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.ConnectedSegmentsMatcher;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.RouteSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.RouteSensorMatcher;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.config.EMFIncQueryBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.driver.EMFIncQueryBaseDriver;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.ConnectedSegmentsQuerySpecification;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.RouteSensorProcessor;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.RouteSensorQuerySpecification;

public class EMFIncQueryRouteSensorChecker extends EMFIncQueryChecker<RouteSensorMatch> {

	public EMFIncQueryRouteSensorChecker(final EMFIncQueryBenchmarkConfig benchmarkConfig,
			final EMFIncQueryBaseDriver<RouteSensorMatch, EMFIncQueryBenchmarkConfig> driver) {
		super(benchmarkConfig, driver);
	}

	@Override
	public IncQueryMatcher<RouteSensorMatch> getMatcher() throws IncQueryException {
        if (benchmarkConfig.isLocalSearch()) {
            return (RouteSensorMatcher) getLSMatcher(RouteSensorQuerySpecification.instance());
        } else {
            return engine.getMatcher(RouteSensorQuerySpecification.instance());
        }
	}

}
