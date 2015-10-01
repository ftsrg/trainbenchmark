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

package hu.bme.mit.trainbenchmark.benchmark.mysql;

import java.io.IOException;
import java.util.Comparator;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.Transformation;
import hu.bme.mit.trainbenchmark.benchmark.matches.LongMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.mysql.driver.MySQLDriver;
import hu.bme.mit.trainbenchmark.benchmark.sql.benchmarkcases.SQLBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.sql.benchmarkcases.SQLChecker;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.SQLTransformation;

public class MySQLBenchmarkCase extends SQLBenchmarkCase<MySQLDriver> {

	public MySQLBenchmarkCase() {
		super();
	}

	@Override
	public void initialize() throws Exception {
		driver = new MySQLDriver();
		checker = new SQLChecker(driver, bc);
	}

	@Override
	protected Transformation<?> getTransformation() throws IOException {
		return SQLTransformation.newInstance(driver, bc);
	}

	@Override
	protected Comparator<?> getMatchComparator() {
		return new LongMatchComparator();
	}

}
