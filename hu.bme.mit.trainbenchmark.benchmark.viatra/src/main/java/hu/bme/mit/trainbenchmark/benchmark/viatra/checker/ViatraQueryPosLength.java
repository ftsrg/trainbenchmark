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
package hu.bme.mit.trainbenchmark.benchmark.viatra.checker;

import org.eclipse.viatra.query.runtime.api.ViatraQueryMatcher;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;

import hu.bme.mit.trainbenchmark.benchmark.viatra.PosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.viatra.PosLengthMatcher;
import hu.bme.mit.trainbenchmark.benchmark.viatra.config.ViatraBackend;
import hu.bme.mit.trainbenchmark.benchmark.viatra.driver.ViatraBaseDriver;
import hu.bme.mit.trainbenchmark.benchmark.viatra.util.PosLengthQuerySpecification;

public class ViatraQueryPosLength extends ViatraQuery<PosLengthMatch> {

	public ViatraQueryPosLength(final ViatraBackend backend,
			final ViatraBaseDriver<PosLengthMatch> driver) {
		super(backend, driver);
	}

	@Override
	public ViatraQueryMatcher<PosLengthMatch> getMatcher() throws ViatraQueryException {
		switch (backend) {
		case INCREMENTAL:
			return engine.getMatcher(PosLengthQuerySpecification.instance());
		case LOCALSEARCH:
			return (PosLengthMatcher) getLSMatcher(PosLengthQuerySpecification.instance());
		default:
			throw new UnsupportedOperationException("Backend: " + backend + " not supported");
		}
	}

}
