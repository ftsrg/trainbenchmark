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
package hu.bme.mit.trainbenchmark.benchmark.viatra.queries;

import org.eclipse.viatra.query.runtime.api.ViatraQueryMatcher;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;

import hu.bme.mit.trainbenchmark.benchmark.viatra.SemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.viatra.SemaphoreNeighborMatcher;
import hu.bme.mit.trainbenchmark.benchmark.viatra.config.ViatraBackend;
import hu.bme.mit.trainbenchmark.benchmark.viatra.driver.ViatraBaseDriver;
import hu.bme.mit.trainbenchmark.benchmark.viatra.util.SemaphoreNeighborQuerySpecification;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class ViatraQuerySemaphoreNeighbor extends ViatraQuery<SemaphoreNeighborMatch> {

	public ViatraQuerySemaphoreNeighbor(final ViatraBackend backend,
			final ViatraBaseDriver<SemaphoreNeighborMatch> driver) {
		super(RailwayQuery.SEMAPHORENEIGHBOR, backend, driver);
	}

	@Override
	public ViatraQueryMatcher<SemaphoreNeighborMatch> getMatcher() throws ViatraQueryException {
		switch (backend) {
		case INCREMENTAL:
			return engine.getMatcher(SemaphoreNeighborQuerySpecification.instance());
		case LOCALSEARCH:
			return (SemaphoreNeighborMatcher) getLSMatcher(SemaphoreNeighborQuerySpecification.instance());
		default:
			throw new UnsupportedOperationException("Backend: " + backend + " not supported");
		}
	}

}
