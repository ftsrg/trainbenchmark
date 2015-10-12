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

package hu.bme.mit.trainbenchmark.benchmark.drools6;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.scenarios.BenchmarkRunner;

public class Drools6BenchmarkMain {

	public static void main(final String[] args) throws Exception {
		final BenchmarkConfig benchmarkConfig = new BenchmarkConfig("Drools6", args);
		final BenchmarkRunner benchmarkRunner = new BenchmarkRunner(benchmarkConfig, new Drools6BenchmarkCase());
		benchmarkRunner.runBenchmark();
	}

}
