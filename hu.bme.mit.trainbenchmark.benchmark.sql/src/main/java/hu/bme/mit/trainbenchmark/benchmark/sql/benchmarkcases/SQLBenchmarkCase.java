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

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.matches.LongMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.sql.driver.SQLDriver;
import hu.bme.mit.trainbenchmark.benchmark.sql.match.SQLMatch;

import java.util.Comparator;

public abstract class SQLBenchmarkCase extends
		AbstractBenchmarkCase<SQLMatch, Long, SQLDriver> {

	protected SQLDriver sqlDriver;

	@Override
	protected Comparator<?> getMatchComparator() {
		return new LongMatchComparator();
	}

}
