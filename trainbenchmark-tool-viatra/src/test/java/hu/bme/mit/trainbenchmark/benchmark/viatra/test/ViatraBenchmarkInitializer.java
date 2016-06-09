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
package hu.bme.mit.trainbenchmark.benchmark.viatra.test;

import java.util.Arrays;
import java.util.List;

import hu.bme.mit.trainbenchmark.benchmark.test.TestBenchmarkInitializer;
import hu.bme.mit.trainbenchmark.benchmark.viatra.config.ViatraBackend;

public class ViatraBenchmarkInitializer extends TestBenchmarkInitializer {

	protected ViatraBackend backend;

	public ViatraBenchmarkInitializer(final ViatraBackend backend) {
		this.backend = backend;
	}
	
	public static List<Object[]> getTestParameters() {
		return Arrays.asList(new Object[][] { { ViatraBackend.INCREMENTAL }, { ViatraBackend.LOCALSEARCH} });
	}

//	@Override
//	protected BenchmarkRunner initializeBenchmark(final RailwayQuery query, final ScenarioEnum scenario) {
//		final ViatraBenchmarkConfigWrapper benchmarkConfig = new ViatraBenchmarkConfigWrapper(scenario, size, 1, query,
//				iterationCount, transformationStrategy, transformationConstant, backend);
//		return new BenchmarkRunner(benchmarkConfig, new ViatraBenchmarkCase());
//	}

}
