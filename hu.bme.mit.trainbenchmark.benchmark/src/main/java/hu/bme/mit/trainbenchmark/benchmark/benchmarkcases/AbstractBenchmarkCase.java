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

package hu.bme.mit.trainbenchmark.benchmark.benchmarkcases;

import java.io.IOException;
import java.util.Comparator;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.Transformation;
import hu.bme.mit.trainbenchmark.benchmark.checker.Checker;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.constants.Query;

public abstract class AbstractBenchmarkCase<TMatch, TElement, TDriver extends Driver<TElement, TBenchmarkConfig>, TBenchmarkConfig extends BenchmarkConfig, TChecker extends Checker<TMatch>> {

	public abstract TDriver createDriver(TBenchmarkConfig benchmarkConfig) throws Exception;

	public abstract TChecker createChecker(TBenchmarkConfig benchmarkConfig, TDriver driver, Query query) throws Exception;

	public abstract Transformation<?, ?> createTransformation(TBenchmarkConfig benchmarkConfig, TDriver driver, Query query) throws IOException;

	public abstract Comparator<?> createMatchComparator();

}
