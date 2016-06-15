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

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.matches.comparators.MatchComparator;

public abstract class AbstractBenchmarkCase<//
TMatch, //
TElement, //
TDriver extends Driver<TElement>, //
TBenchmarkConfigWrapper extends BenchmarkConfigWrapper //
> {

	public abstract TDriver createDriver(TBenchmarkConfigWrapper benchmarkConfigWrapper) throws Exception;

	public abstract MatchComparator<TMatch, TElement> getMatchComparator();

}
