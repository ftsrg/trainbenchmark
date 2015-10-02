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
import hu.bme.mit.trainbenchmark.emf.benchmarkcases.EMFBenchmarkCase;
import hu.bme.mit.trainbenchmark.emf.transformation.EMFTransformation;

public class Drools5BenchmarkCase extends EMFBenchmarkCase<Drools5Driver, BenchmarkConfig> {

	protected Drools5Driver drools5driver;

	@Override
	public void initialize() throws Exception {
		driver = drools5driver = new Drools5Driver(bc);
		checker = new Drools5Checker(drools5driver, bc.getQuery());
	}

	@Override
	protected Transformation<?> getTransformation() throws IOException {
		return EMFTransformation.newInstance(drools5driver, bc.getQuery(), bc.getScenario());
	}

}
