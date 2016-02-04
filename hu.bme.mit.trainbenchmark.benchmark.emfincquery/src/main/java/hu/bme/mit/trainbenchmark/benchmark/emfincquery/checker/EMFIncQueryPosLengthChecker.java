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

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.PosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.PosLengthMatcher;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.config.EMFIncQueryBackend;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.driver.EMFIncQueryBaseDriver;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.PosLengthQuerySpecification;

public class EMFIncQueryPosLengthChecker extends EMFIncQueryChecker<PosLengthMatch> {

	public EMFIncQueryPosLengthChecker(final EMFIncQueryBackend backend,
			final EMFIncQueryBaseDriver<PosLengthMatch> driver) {
		super(backend, driver);
	}

	@Override
	public IncQueryMatcher<PosLengthMatch> getMatcher() throws IncQueryException {
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
