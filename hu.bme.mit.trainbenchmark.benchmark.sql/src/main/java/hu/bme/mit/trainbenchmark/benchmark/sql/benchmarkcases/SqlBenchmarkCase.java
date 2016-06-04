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
package hu.bme.mit.trainbenchmark.benchmark.sql.benchmarkcases;

import java.util.Comparator;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.matches.LongMatch;
import hu.bme.mit.trainbenchmark.benchmark.sql.comparators.LongMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.sql.driver.SqlDriver;

public abstract class SqlBenchmarkCase<TBenchmarkConfig extends BenchmarkConfig, TSqlDriver extends SqlDriver>
		extends AbstractBenchmarkCase<LongMatch, Long, TSqlDriver, TBenchmarkConfig> {

	@Override
	public Comparator<LongMatch> getMatchComparator() {
		return LongMatchComparator.create();
	}

}
