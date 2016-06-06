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

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.emf.comparators.EmfMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.emf.driver.EmfDriver;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfapi.operations.EmfApiModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.phases.BenchmarkScenario;

public class EmfApiBenchmarkMain {

	public static void main(final String[] args) throws Exception {
		EmfDriver driver = EmfDriver.create();
		EmfApiModelOperationFactory<EmfDriver> factory = EmfApiModelOperationFactory.create();
		EmfMatchComparator comparator = EmfMatchComparator.create();
		BenchmarkConfig benchmarkConfig = new BenchmarkConfig();
		
		final BenchmarkScenario<EmfMatch, EmfDriver, BenchmarkConfig> scenario = new BenchmarkScenario<EmfMatch, EmfDriver, BenchmarkConfig>(driver, factory, comparator, benchmarkConfig);
		scenario.runBenchmark();
	}
}
