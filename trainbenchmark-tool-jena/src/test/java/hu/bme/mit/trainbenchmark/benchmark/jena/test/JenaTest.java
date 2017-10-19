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

package hu.bme.mit.trainbenchmark.benchmark.jena.test;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBase;
import hu.bme.mit.trainbenchmark.benchmark.jena.JenaBenchmarkScenario;
import hu.bme.mit.trainbenchmark.benchmark.jena.config.JenaBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.jena.config.JenaBenchmarkConfigBuilder;
import hu.bme.mit.trainbenchmark.benchmark.rdf.tests.RdfTest;
import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkResult;

@RunWith(Parameterized.class)
public class JenaTest extends RdfTest {

	@Override
	protected BenchmarkResult runTest(final BenchmarkConfigBase bcb) throws Exception {
		final JenaBenchmarkConfig bc = new JenaBenchmarkConfigBuilder().setConfigBase(bcb).setInferencing(false).createConfig();
		final JenaBenchmarkScenario scenario = new JenaBenchmarkScenario(bc);
		final BenchmarkResult result = scenario.performBenchmark();
		return result;
	}

}
