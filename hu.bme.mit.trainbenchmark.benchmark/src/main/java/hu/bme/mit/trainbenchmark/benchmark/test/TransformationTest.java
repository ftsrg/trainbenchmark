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

package hu.bme.mit.trainbenchmark.benchmark.test;

import static org.junit.Assert.assertEquals;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.scenarios.GenericBenchmarkLogic;
import hu.bme.mit.trainbenchmark.benchmark.util.BenchmarkResult;

import java.io.IOException;

import org.apache.commons.cli.ParseException;

public abstract class TransformationTest extends TrainBenchmarkTest {

	protected void testTransformation(final String queryName, final String scenario, final int expectedResultSize) throws ParseException,
			IOException {
		final GenericBenchmarkLogic bl = bi.initializeBenchmark(queryName, scenario);
		testTransformation(bl, expectedResultSize);
	}

	protected void testTransformation(final GenericBenchmarkLogic bl, final int expectedResultSize) throws IOException {
		final AbstractBenchmarkCase<?> benchmarkCase = (bl.getTestCase());
		
		try {
			benchmarkCase.benchmarkInit(bl.getBc());
			benchmarkCase.benchmarkRead();
			benchmarkCase.benchmarkCheck();
			benchmarkCase.benchmarkModify();
			benchmarkCase.benchmarkCheck();

			final int resultSize = benchmarkCase.getResults().size();
			assertEquals(expectedResultSize, resultSize);

			final BenchmarkResult benchmarkResult = benchmarkCase.getBenchmarkResult();
			System.out.println(benchmarkResult);
		} finally {
			benchmarkCase.benchmarkDestroy();
		}
	}

}
