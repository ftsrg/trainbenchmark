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

package hu.bme.mit.trainbenchmark.benchmark.drools5;

import java.io.IOException;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.drools5.checkers.Drools5ModelQuery;
import hu.bme.mit.trainbenchmark.benchmark.drools5.driver.Drools5Driver;
import hu.bme.mit.trainbenchmark.benchmark.emf.benchmarkcases.EmfBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.EmfTransformation;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelTransformation;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class Drools5BenchmarkCase extends EmfBenchmarkCase<Drools5Driver, BenchmarkConfig, Drools5ModelQuery> {

	@Override
	public Drools5Driver createDriver(final BenchmarkConfig benchmarkConfig) throws Exception {
		return new Drools5Driver();
	}

	@Override
	public Drools5ModelQuery createModelQuery(final BenchmarkConfig benchmarkConfig, final Drools5Driver driver, final RailwayQuery query) throws IOException {
		return new Drools5ModelQuery(benchmarkConfig, driver, query);
	}

	@Override
	public ModelTransformation<?, ?> createTransformation(final BenchmarkConfig benchmarkConfig, final Drools5Driver driver, final RailwayQuery query) throws IOException {
		return EmfTransformation.newInstance(driver, query, benchmarkConfig.getScenario());
	}

}
