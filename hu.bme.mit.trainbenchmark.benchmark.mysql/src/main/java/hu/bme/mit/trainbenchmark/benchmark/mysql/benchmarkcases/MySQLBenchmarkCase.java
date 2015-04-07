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

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.mysql.MySQLProcess;
import hu.bme.mit.trainbenchmark.benchmark.mysql.driver.MySQLDriver;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;
import hu.bme.mit.trainbenchmark.sql.SQLDriver;
import hu.bme.mit.trainbenchmark.sql.match.SQLMatch;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

public class MySQLBenchmarkCase extends AbstractBenchmarkCase<SQLMatch, Long> {

	SQLDriver sqlDriver;

	@Override
	public void init() throws IOException {
		MySQLProcess.startSQLProcess();

		final String queryPath = bc.getWorkspacePath() + "/hu.bme.mit.trainbenchmark.sql/src/main/resources/queries/" + getName() + ".sql";
		driver = sqlDriver = new MySQLDriver(queryPath);
	}

	@Override
	public void read() throws FileNotFoundException, IOException {
		driver.read(bc.getModelPathNameWithoutExtension() + ".sql");
	}

	@Override
	public Collection<SQLMatch> check() throws IOException {
		matches = sqlDriver.runQuery(getName());
		return matches;
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
