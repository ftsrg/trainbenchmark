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

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.Transformation;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.drools5.checkers.Drools5Checker;
import hu.bme.mit.trainbenchmark.benchmark.drools5.driver.Drools5Driver;
import hu.bme.mit.trainbenchmark.benchmark.emf.benchmarkcases.EMFBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.EMFTransformation;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class Drools5BenchmarkCase extends EMFBenchmarkCase<Drools5Driver, BenchmarkConfig, Drools5Checker> {

	@Override
	public Drools5Driver createDriver(final BenchmarkConfig benchmarkConfig) throws Exception {
		return new Drools5Driver();
	}

	@Override
	public Drools5Checker createChecker(final BenchmarkConfig benchmarkConfig, final Drools5Driver driver, final RailwayQuery query) throws IOException {
		return new Drools5Checker(benchmarkConfig, driver, query);
	}

	@Override
	public Transformation<?, ?> createTransformation(final BenchmarkConfig benchmarkConfig, final Drools5Driver driver, final RailwayQuery query) throws IOException {
		return EMFTransformation.newInstance(driver, query, benchmarkConfig.getScenario());
	}

}
