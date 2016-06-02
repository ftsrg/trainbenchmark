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
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.config.EMFIncQueryBackend;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.driver.EMFIncQueryBaseDriver;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.ConnectedSegmentsQuerySpecification;

public class EMFIncQueryConnectedSegmentsChecker extends EMFIncQueryModelQuery<ConnectedSegmentsMatch> {

	public EMFIncQueryConnectedSegmentsChecker(final EMFIncQueryBackend backend,
			final EMFIncQueryBaseDriver<ConnectedSegmentsMatch> driver) {
		super(backend, driver);
	}

	@Override
	public IncQueryMatcher<ConnectedSegmentsMatch> getMatcher() throws IncQueryException {
		switch (backend) {
		case INCREMENTAL:
			return engine.getMatcher(ConnectedSegmentsQuerySpecification.instance());
		case LOCALSEARCH:
			return (ConnectedSegmentsMatcher) getLSMatcher(ConnectedSegmentsQuerySpecification.instance());
		default:
			throw new UnsupportedOperationException("Backend: " + backend + " not supported");
		}
	}

}
