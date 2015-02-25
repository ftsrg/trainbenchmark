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

import java.io.IOException;

import org.apache.commons.cli.ParseException;

public abstract class BatchTest extends TrainBenchmarkTest {

	protected void testQuery(final String queryName, final int expectedResultSize) throws ParseException, IOException {
		final GenericBenchmarkLogic bl = bi.initializeBenchmark(queryName, "Batch");
		runQuery(bl, expectedResultSize);
	}

	protected void runQuery(final GenericBenchmarkLogic bl, final int expectedResultSize) throws IOException {
		final AbstractBenchmarkCase<?> testCase = bl.getTestCase();
		testCase.benchmarkInit(bl.getBc());
		testCase.benchmarkRead();
		testCase.benchmarkCheck();
		assertEquals(expectedResultSize, testCase.getResults().size());
		testCase.benchmarkDestroy();
	}

}