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

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.emf.benchmarkcases.EmfBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfMatch;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelQuery;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelTransformation;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.emf.EmfDriver;

public class EmfApiBenchmarkCase<TBenchmarkConfig extends BenchmarkConfig>
		extends EmfBenchmarkCase<EmfDriver, TBenchmarkConfig, ModelQuery<EmfMatch, EmfDriver>> {

	@Override
	public EmfDriver createDriver(final BenchmarkConfig benchmarkConfig) throws Exception {
		return new EmfDriver();
	}

	@Override
	public ModelQuery<EmfMatch, EmfDriver> createModelQuery(final BenchmarkConfig benchmarkConfig, final EmfDriver driver, final RailwayQuery query)
			throws Exception {
//		return (ModelQuery<EMFMatch, EMFDriver>) EMFAPIModelQuery.newInstance(driver, query);
		return null;
	}

	@Override
	public ModelTransformation<?, ?> createTransformation(final BenchmarkConfig benchmarkConfig, final EmfDriver driver, final RailwayQuery query)
			throws IOException {
//		return EMFTransformation.newInstance(driver, query, benchmarkConfig.getScenario());
		return null;
	}

}
