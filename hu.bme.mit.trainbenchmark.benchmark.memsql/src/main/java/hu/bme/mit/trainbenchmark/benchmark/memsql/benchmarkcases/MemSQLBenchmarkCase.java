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
package hu.bme.mit.trainbenchmark.benchmark.memsql.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.memsql.driver.MemSQLDriver;

import java.io.IOException;
import java.util.Collection;

public class MemSQLBenchmarkCase extends AbstractBenchmarkCase<Long> {

	@Override
	protected void init() throws IOException {
		final String queryPath = bc.getWorkspacePath() + "/hu.bme.mit.trainbenchmark.sql/src/main/resources/queries/" + getName() + ".sql";
		driver = new MemSQLDriver(queryPath);
	}

	@Override
	protected void read() throws IOException {
		driver.read(bc.getModelPathNameWithoutExtension() + ".sql");
	}

	@Override
	protected Collection<Long> check() throws IOException {
		results = driver.runQuery();
		return results;
	}

	@Override
	protected void destroy() throws IOException {
		driver.destroy();
	}

}
