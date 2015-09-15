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

package hu.bme.mit.trainbenchmark.benchmark.emfapi;

import hu.bme.mit.trainbenchmark.benchmark.checker.Checker;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.emfapi.benchmarkcases.EMFAPIChecker;
import hu.bme.mit.trainbenchmark.constants.Scenario;
import hu.bme.mit.trainbenchmark.emf.EMFDriver;
import hu.bme.mit.trainbenchmark.emf.benchmarkcases.EMFBenchmarkCase;
import hu.bme.mit.trainbenchmark.emf.matches.EMFMatch;
import hu.bme.mit.trainbenchmark.emf.transformation.EMFTransformation;

public class EMFAPIBenchmarkCase extends EMFBenchmarkCase {

	@Override
	public void benchmarkInit(final BenchmarkConfig bc) throws Exception {
		super.benchmarkInit(bc);

		final EMFDriver emfDriver = new EMFDriver();
		driver = emfDriver;
		checker = (Checker<EMFMatch>) EMFAPIChecker.newInstance(emfDriver, bc.getQuery());

		if (bc.getScenario().hasTranformation()) {
			transformation = EMFTransformation.newInstance(emfDriver, bc.getQuery(), bc.getScenario());
		}
	}

}
