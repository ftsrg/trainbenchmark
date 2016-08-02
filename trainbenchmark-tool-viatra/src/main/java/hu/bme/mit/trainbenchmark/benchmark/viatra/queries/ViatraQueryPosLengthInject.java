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

import hu.bme.mit.trainbenchmark.benchmark.viatra.PosLengthInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.viatra.driver.ViatraDriver;
import hu.bme.mit.trainbenchmark.benchmark.viatra.util.PosLengthInjectQuerySpecification;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class ViatraQueryPosLengthInject extends ViatraQuery<PosLengthInjectMatch> {

	public ViatraQueryPosLengthInject(final ViatraDriver driver) {
		super(RailwayQuery.POSLENGTH_INJECT, driver);
	}

	@Override
	public ViatraQueryMatcher<PosLengthInjectMatch> getMatcher() throws ViatraQueryException {
		return engine.getMatcher(PosLengthInjectQuerySpecification.instance());
	}

}
