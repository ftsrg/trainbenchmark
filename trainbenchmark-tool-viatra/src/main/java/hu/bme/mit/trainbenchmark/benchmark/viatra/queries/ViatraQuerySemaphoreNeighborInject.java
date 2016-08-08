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

import hu.bme.mit.trainbenchmark.benchmark.viatra.SemaphoreNeighborInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.viatra.driver.ViatraDriver;
import hu.bme.mit.trainbenchmark.benchmark.viatra.util.SemaphoreNeighborInjectQuerySpecification;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class ViatraQuerySemaphoreNeighborInject extends ViatraQuery<SemaphoreNeighborInjectMatch> {

	public ViatraQuerySemaphoreNeighborInject(final ViatraDriver driver) {
		super(RailwayQuery.SEMAPHORENEIGHBOR_INJECT, driver);
	}

	@Override
	public ViatraQueryMatcher<SemaphoreNeighborInjectMatch> getMatcher() throws ViatraQueryException {
		return engine.getMatcher(SemaphoreNeighborInjectQuerySpecification.instance());
	}

}
