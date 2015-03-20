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

package hu.bme.mit.trainbenchmark.benchmark.fourstore.test;

import hu.bme.mit.trainbenchmark.benchmark.fourstore.FourStoreBenchmarkLogic;
import hu.bme.mit.trainbenchmark.benchmark.scenarios.AbstractBenchmarkLogic;
import hu.bme.mit.trainbenchmark.benchmark.test.TestBenchmarkInitializer;

import org.apache.commons.cli.ParseException;

public class FourStoreBenchmarkInitializer extends TestBenchmarkInitializer {

	@Override
	protected AbstractBenchmarkLogic initializeBenchmark(String queryName, String scenario) throws ParseException {
		// @formatter:off
		String[] args = {
				"-query", queryName, 
				"-benchmarkArtifact", "../models/railway-test-1.ttl",
				"-scenario", scenario, 
				"-workspacePath", "../" 
			};
		// @formatter:on

		return new FourStoreBenchmarkLogic(args);
	}

}
