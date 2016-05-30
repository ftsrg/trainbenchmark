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

package hu.bme.mit.trainbenchmark.benchmark.iqdcore;

import java.io.IOException;

import hu.bme.mit.trainbenchmark.benchmark.iqdcore.config.IQDCoreBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.rdf.RDFBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.scenarios.BenchmarkRunner;
import org.apache.commons.cli.ParseException;

public class IQDCoreBenchmarkMain {

	public static void main(String[] args) throws Exception {
		Thread.sleep(10000); //wait for remote system to unquarantine us
		final IQDCoreBenchmarkConfig benchmarkConfig = new IQDCoreBenchmarkConfig(args);
		final BenchmarkRunner benchmarkLogic = new BenchmarkRunner(benchmarkConfig, new IQDCoreBenchmarkCase(benchmarkConfig));
		benchmarkLogic.runBenchmark();
		System.exit(0);
		return;
	}

}
