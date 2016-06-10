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

package hu.bme.mit.trainbenchmark.benchmark.sesame;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.phases.BenchmarkScenario;
import hu.bme.mit.trainbenchmark.benchmark.rdf.RdfBenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.sesame.comparators.SesameMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameMatch;
import hu.bme.mit.trainbenchmark.benchmark.sesame.operations.SesameModelOperationFactory;

public class SesameBenchmarkMain {

	public static void main(final String[] args) throws Exception {
		final RdfBenchmarkConfigWrapper benchmarkConfigWrapper = BenchmarkConfigWrapper.fromFile(args[0], RdfBenchmarkConfigWrapper.class);
		
		final SesameDriver driver = SesameDriver.create(benchmarkConfigWrapper.isInferencing());
		final SesameModelOperationFactory factory = SesameModelOperationFactory.create();
		final SesameMatchComparator comparator = SesameMatchComparator.create();
		
		final BenchmarkScenario<SesameMatch, SesameDriver, BenchmarkConfigWrapper> scenario = new BenchmarkScenario<>(driver, factory, comparator, benchmarkConfigWrapper);
		scenario.runBenchmark();
	}

}
