/*******************************************************************************
 * Copyright (c) 2010-2015, Gabor Szarnyas, Benedek Izso, Istvan Rath and Daniel Varro
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

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.PosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.PosLengthMatcher;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.driver.EMFIncQueryDriver;

import org.eclipse.incquery.runtime.api.IncQueryMatcher;
import org.eclipse.incquery.runtime.exception.IncQueryException;

public class EMFIncQueryPosLengthChecker extends EMFIncQueryChecker<PosLengthMatch> {

	public EMFIncQueryPosLengthChecker(final EMFIncQueryDriver<PosLengthMatch> eiqDriver) {
		super(eiqDriver);
	}

	@Override
	public IncQueryMatcher<PosLengthMatch> getMatcher() throws IncQueryException {
		return PosLengthMatcher.on(eiqDriver.getEngine());
	}

}
