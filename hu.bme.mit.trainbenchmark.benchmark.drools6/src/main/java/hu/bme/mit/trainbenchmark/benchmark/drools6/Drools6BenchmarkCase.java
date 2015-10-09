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

import java.io.IOException;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.Transformation;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.drools6.checkers.Drools6Checker;
import hu.bme.mit.trainbenchmark.benchmark.drools6.driver.Drools6Driver;
import hu.bme.mit.trainbenchmark.emf.benchmarkcases.EMFBenchmarkCase;
import hu.bme.mit.trainbenchmark.emf.transformation.EMFTransformation;

public class Drools6BenchmarkCase extends EMFBenchmarkCase<Drools6Driver, BenchmarkConfig, Drools6Checker> {

	@Override
	public Drools6Driver createDriver(final BenchmarkConfig benchmarkConfig) throws Exception {
		return new Drools6Driver(benchmarkConfig);
	}

	@Override
	public Drools6Checker createChecker(final BenchmarkConfig benchmarkConfig, final Drools6Driver driver) throws Exception {
		return new Drools6Checker(driver, benchmarkConfig.getQuery());
	}

	@Override
	public Transformation<?, ?> createTransformation(final BenchmarkConfig benchmarkConfig, final Drools6Driver driver) throws IOException {
		return EMFTransformation.newInstance(driver, benchmarkConfig.getQuery(), benchmarkConfig.getScenario());
	}

}
