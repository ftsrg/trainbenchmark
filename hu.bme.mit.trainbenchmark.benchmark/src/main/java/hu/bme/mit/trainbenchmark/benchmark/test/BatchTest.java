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
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.BenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.scenarios.GenericBenchmarkLogic;

import java.io.IOException;

import org.apache.commons.cli.ParseException;

public abstract class BatchTest extends TrainBenchmarkTest {

	protected void testQuery(String queryName, int expectedResultSize) throws ParseException, IOException {
		GenericBenchmarkLogic bl = bi.initializeBenchmark(queryName, "Batch");
		runQuery(bl, expectedResultSize);
	}

	protected void runQuery(GenericBenchmarkLogic bl, int expectedResultSize) throws IOException {
		BenchmarkCase testCase = bl.getTestCase();
		testCase.init(bl.getBc());
		testCase.load();
		testCase.check();
		assertEquals(expectedResultSize, testCase.getResultSize());
		testCase.destroy();
	}

}