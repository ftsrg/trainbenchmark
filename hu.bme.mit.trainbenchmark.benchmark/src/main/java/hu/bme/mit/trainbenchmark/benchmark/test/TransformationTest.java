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
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractTransformationBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.scenarios.GenericBenchmarkLogic;
import hu.bme.mit.trainbenchmark.benchmark.util.BenchmarkResult;

import java.io.IOException;

import org.apache.commons.cli.ParseException;
import org.hamcrest.Matchers;
import org.junit.Assert;

public abstract class TransformationTest extends TrainBenchmarkTest {

	protected void testTransformation(final String queryName, final String scenario, final int expectedResultSize1,
			final int expectedResultSize2) throws ParseException, IOException {
		final GenericBenchmarkLogic bl = bi.initializeBenchmark(queryName, scenario);
		testTransformation(bl, expectedResultSize1, expectedResultSize2);
	}

	protected void testTransformation(final GenericBenchmarkLogic bl, final int expectedResultSize1, final int expectedResultSize2)
			throws IOException {
		final AbstractTransformationBenchmarkCase<?> benchmarkCase = (AbstractTransformationBenchmarkCase<?>) (bl.getTestCase());

		try {
			benchmarkCase.benchmarkInit(bl.getBc());
			benchmarkCase.benchmarkRead();
			benchmarkCase.benchmarkCheck();

			// System.out.println(bl.getBc().getQuery());
			// System.out.println(testCase.getResultSize());

			final int resultSize1 = benchmarkCase.getResults().size();
//			assertEquals(expectedResultSize1, resultSize1);
			System.out.println("resultSize1: " + resultSize1);
			benchmarkCase.benchmarkModify();
			benchmarkCase.benchmarkCheck();

			final int resultSize2 = benchmarkCase.getResults().size();
			System.out.println("resultSize2: " + resultSize2);
			
			// TODO we should remove this inequality as it may prevent the detection of buggy implementations in the user scenario (where
			// errors are injected)
			final boolean strict = true;
			if (strict) {
				assertEquals(expectedResultSize2, benchmarkCase.getResults().size());		
			} else {
				if (expectedResultSize2 > expectedResultSize1) {
					Assert.assertThat(benchmarkCase.getResults().size(), Matchers.greaterThanOrEqualTo(expectedResultSize1));
				} else {
					assertEquals(expectedResultSize2, benchmarkCase.getResults().size());
				}
			}

			final BenchmarkResult benchmarkResult = benchmarkCase.getBenchmarkResult();
			System.out.println(benchmarkResult);
			// System.out.println(testCase.getResultSize());
		} finally {
			benchmarkCase.benchmarkDestroy();
		}
	}

}
