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

import hu.bme.mit.trainbenchmark.benchmark.memsql.driver.MemSQLDriver;
import hu.bme.mit.trainbenchmark.sql.benchmarkcases.SQLBenchmarkCase;
import hu.bme.mit.trainbenchmark.sql.driver.SQLDriver;

import java.io.IOException;

public class MemSQLBenchmarkCase extends SQLBenchmarkCase {

	protected SQLDriver sqlDriver;

	@Override
	protected void init() throws IOException {
		super.init();
		driver = sqlDriver = new MemSQLDriver(queryPath());
	}

}
