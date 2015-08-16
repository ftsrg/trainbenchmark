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
package hu.bme.mit.trainbenchmark.benchmark.memsql;

import hu.bme.mit.trainbenchmark.benchmark.memsql.driver.MemSQLDriver;
import hu.bme.mit.trainbenchmark.benchmark.sql.benchmarkcases.SQLBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.sql.benchmarkcases.SQLChecker;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.SQLTransformation;

public class MemSQLBenchmarkCase extends SQLBenchmarkCase {

	@Override
	protected void init() throws Exception {
		driver = sqlDriver = new MemSQLDriver();
		checker = new SQLChecker(sqlDriver, bc);

		transformation = SQLTransformation.newInstance(sqlDriver, bc);
	}

	@Override
	protected void initAnalyzer() {

	}

}
