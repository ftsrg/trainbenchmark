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

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.TransformationBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.util.BenchmarkResult;
import hu.bme.mit.trainbenchmark.benchmark.util.JsonSerializer;

import java.io.IOException;

public class UserScenario implements Scenario<TransformationBenchmarkCase> {

	public void runBenchmark(BenchmarkConfig bc, TransformationBenchmarkCase testCase) throws IOException {
		testCase.init(bc);

		testCase.load();
		testCase.check();
		for (int i = 0; i < bc.getIterationCount(); i++) {
			testCase.modify();
			testCase.check();
		}
		testCase.measureMemory();
		testCase.destroy();

		BenchmarkResult bmr = testCase.getBenchmarkResult();
		JsonSerializer.serialize(bmr);
	}
}
