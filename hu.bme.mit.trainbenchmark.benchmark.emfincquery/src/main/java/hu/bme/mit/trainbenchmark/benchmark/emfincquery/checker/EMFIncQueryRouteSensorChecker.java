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

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.RouteSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.RouteSensorMatcher;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.config.EMFIncQueryBackend;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.driver.EMFIncQueryBaseDriver;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.RouteSensorQuerySpecification;

public class EMFIncQueryRouteSensorChecker extends EMFIncQueryChecker<RouteSensorMatch> {

	public EMFIncQueryRouteSensorChecker(final EMFIncQueryBackend backend, final EMFIncQueryBaseDriver<RouteSensorMatch> driver) {
		super(backend, driver);
	}

	@Override
	public IncQueryMatcher<RouteSensorMatch> getMatcher() throws IncQueryException {
		switch (backend) {
		case INCREMENTAL:
            return engine.getMatcher(RouteSensorQuerySpecification.instance());
		case LOCALSEARCH:
            return (RouteSensorMatcher) getLSMatcher(RouteSensorQuerySpecification.instance());
		default:
			throw new UnsupportedOperationException("Backend: " + backend + " not supported");
		}
	}

}
