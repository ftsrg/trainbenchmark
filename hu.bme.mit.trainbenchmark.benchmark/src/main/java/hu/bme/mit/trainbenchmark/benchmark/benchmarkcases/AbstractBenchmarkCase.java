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

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelQuery;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelTransformation;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public abstract class AbstractBenchmarkCase<TMatch, //
TElement, //
TDriver extends Driver<TElement>, //
TBenchmarkConfig extends BenchmarkConfig, //
TChecker extends ModelQuery<TMatch, TDriver> //
> {

	public abstract TDriver createDriver(TBenchmarkConfig benchmarkConfig) throws Exception;

	public abstract TChecker createChecker(TBenchmarkConfig benchmarkConfig, TDriver driver, RailwayQuery query) throws Exception;

	public abstract ModelTransformation<?, ?> createTransformation(TBenchmarkConfig benchmarkConfig, TDriver driver, RailwayQuery query)
			throws IOException;

	public abstract Comparator<?> getMatchComparator();

}
