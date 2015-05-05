/*******************************************************************************
 * Copyright (c) 2010-2014, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/

package hu.bme.mit.trainbenchmark.benchmark.mysql.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.mysql.MySQLProcess;
import hu.bme.mit.trainbenchmark.benchmark.mysql.driver.MySQLDriver;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;
import hu.bme.mit.trainbenchmark.constants.Scenario;
import hu.bme.mit.trainbenchmark.sql.benchmarkcases.SQLBenchmarkCase;
import hu.bme.mit.trainbenchmark.sql.benchmarkcases.SQLChecker;

import java.io.IOException;

public class MySQLBenchmarkCase extends SQLBenchmarkCase {

	public MySQLBenchmarkCase() {
		super();
		driver = sqlDriver = new MySQLDriver();
	}

	@Override
	public void init() throws IOException {
		super.init();
		MySQLProcess.startSQLProcess();

		checker = new SQLChecker(sqlDriver, bc);

		if (bc.getScenario() != Scenario.BATCH) {
			// transformation = SQLTransformation.newInstance(sqlDriver, bc.getQuery(), bc.getScenario());
		}
	}

	@Override
	protected long getMemoryUsage() throws IOException {
		Util.runGC();
		return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() + MySQLProcess.getMemoryUsage(bc) * 1024;
	}

	@Override
	protected void destroy() throws IOException {
		driver.destroy();
	}

}
