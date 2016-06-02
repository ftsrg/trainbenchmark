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

import java.io.IOException;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.Transformation;
import hu.bme.mit.trainbenchmark.benchmark.checker.Checker;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.emf.benchmarkcases.EMFBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EMFMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.EMFTransformation;
import hu.bme.mit.trainbenchmark.benchmark.emfapi.benchmarkcases.EMFAPIChecker;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.emf.EMFDriver;

public class EMFAPIBenchmarkCase<TBenchmarkConfig extends BenchmarkConfig>
		extends EMFBenchmarkCase<EMFDriver, TBenchmarkConfig, Checker<EMFMatch>> {

	@Override
	public EMFDriver createDriver(final BenchmarkConfig benchmarkConfig) throws Exception {
		return new EMFDriver();
	}

	@Override
	public Checker<EMFMatch> createChecker(final BenchmarkConfig benchmarkConfig, final EMFDriver driver, final RailwayQuery query)
			throws Exception {
		return (Checker<EMFMatch>) EMFAPIChecker.newInstance(driver, query);
	}

	@Override
	public Transformation<?, ?> createTransformation(final BenchmarkConfig benchmarkConfig, final EMFDriver driver, final RailwayQuery query)
			throws IOException {
		return EMFTransformation.newInstance(driver, query, benchmarkConfig.getScenario());
	}

}
