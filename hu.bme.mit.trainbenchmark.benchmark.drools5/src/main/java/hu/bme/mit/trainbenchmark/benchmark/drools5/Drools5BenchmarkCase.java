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

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.layers.AnalyzedBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.drools5.checkers.Drools5Checker;
import hu.bme.mit.trainbenchmark.benchmark.drools5.driver.Drools5Driver;
import hu.bme.mit.trainbenchmark.emf.analyzer.EMFModelAnalyzer;
import hu.bme.mit.trainbenchmark.emf.benchmarkcases.EMFBenchmarkCase;
import hu.bme.mit.trainbenchmark.emf.transformation.EMFTransformation;

public class Drools5BenchmarkCase extends EMFBenchmarkCase implements AnalyzedBenchmarkCase {

	protected Drools5Driver drools5driver;

	@Override
	public void init() throws Exception {
		driver = drools5driver = new Drools5Driver(benchmarkConfig);
		checker = new Drools5Checker(drools5driver, benchmarkConfig.getQuery());

		transformation = EMFTransformation.newInstance(drools5driver, benchmarkConfig.getQuery(),
				benchmarkConfig.getScenario());

	}

	@Override
	public void initAnalyzer() {
		modelAnalyzer = new EMFModelAnalyzer(drools5driver);
	}

}
