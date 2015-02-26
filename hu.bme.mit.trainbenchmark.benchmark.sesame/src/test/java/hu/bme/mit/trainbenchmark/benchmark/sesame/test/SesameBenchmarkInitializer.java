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

package hu.bme.mit.trainbenchmark.benchmark.sesame.test;

import hu.bme.mit.trainbenchmark.benchmark.scenarios.GenericBenchmarkLogic;
import hu.bme.mit.trainbenchmark.benchmark.sesame.SesameBenchmarkLogic;
import hu.bme.mit.trainbenchmark.benchmark.test.BenchmarkInitializer;

import org.apache.commons.cli.ParseException;

public class SesameBenchmarkInitializer extends BenchmarkInitializer {

	@Override
	public GenericBenchmarkLogic initializeBenchmark(final String queryName, final String scenario) throws ParseException {
		// @formatter:off
		final String[] args = {
				"-query", queryName, 
				"-benchmarkArtifact", "../models/railway-test-1.ttl",
				"-scenario", scenario, 
				"-workspacePath", "../"
			};
		// @formatter:on

		return new SesameBenchmarkLogic(args);
	}

}
