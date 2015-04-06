/*******************************************************************************
 * Copyright (c) 2010-2014, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/

package hu.bme.mit.trainbenchmark.benchmark.scenarios;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.util.BenchmarkResult;

import java.io.IOException;

public class BatchScenarioLogic implements ScenarioLogic<AbstractBenchmarkCase<?, ?>> {

	@Override
	public BenchmarkResult runBenchmark(final BenchmarkConfig bc, final AbstractBenchmarkCase<?, ?> testCase) throws IOException {
		testCase.benchmarkInit(bc);

		testCase.benchmarkRead();
		testCase.benchmarkCheck();
		testCase.benchmarkDestroy();

		final BenchmarkResult bmr = testCase.getBenchmarkResult();
		System.out.println(bmr);
		bmr.publish();
		return bmr;
	}

}
