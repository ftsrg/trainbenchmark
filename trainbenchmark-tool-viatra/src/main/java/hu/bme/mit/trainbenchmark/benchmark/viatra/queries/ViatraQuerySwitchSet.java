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

import hu.bme.mit.trainbenchmark.benchmark.viatra.SwitchSetMatch;
import hu.bme.mit.trainbenchmark.benchmark.viatra.SwitchSetMatcher;
import hu.bme.mit.trainbenchmark.benchmark.viatra.config.ViatraBackend;
import hu.bme.mit.trainbenchmark.benchmark.viatra.driver.ViatraBaseDriver;
import hu.bme.mit.trainbenchmark.benchmark.viatra.util.SwitchSetQuerySpecification;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class ViatraQuerySwitchSet extends ViatraQuery<SwitchSetMatch> {

	public ViatraQuerySwitchSet(final ViatraBackend backend, final ViatraBaseDriver<SwitchSetMatch> driver) {
		super(RailwayQuery.SWITCHSET, backend, driver);
	}

	@Override
	public ViatraQueryMatcher<SwitchSetMatch> getMatcher() throws ViatraQueryException {
		switch (backend) {
		case INCREMENTAL:
            return engine.getMatcher(SwitchSetQuerySpecification.instance());
		case LOCALSEARCH:
            return (SwitchSetMatcher) getLSMatcher(SwitchSetQuerySpecification.instance());
		default:
			throw new UnsupportedOperationException("Backend: " + backend + " not supported");
		}
	}

}
