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

import static hu.bme.mit.trainbenchmark.constants.QueryConstants.POSLENGTH;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.ROUTESENSOR;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.SEMAPHORENEIGHBOR;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.SWITCHSENSOR;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.SWITCHSET;
import static org.junit.Assert.assertEquals;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.scenarios.AbstractBenchmarkLogic;
import hu.bme.mit.trainbenchmark.constants.Scenario;

import java.io.IOException;

import org.apache.commons.cli.ParseException;
import org.junit.Test;

public abstract class BatchTest extends TrainBenchmarkTest {

	@Test
	public void posLength() throws ParseException, IOException {
		testQuery(POSLENGTH, 486);
	}

	@Test
	public void routeSensor() throws ParseException, IOException {
		testQuery(ROUTESENSOR, 18);
	}

	@Test
	public void semaphoreNeighbor() throws ParseException, IOException {
		testQuery(SEMAPHORENEIGHBOR, 1);
	}

	@Test
	public void switchSensor() throws ParseException, IOException {
		testQuery(SWITCHSENSOR, 4);
	}

	@Test
	public void switchSet() throws ParseException, IOException {
		testQuery(SWITCHSET, 15);
	}

	protected void testQuery(final String queryName, final int expectedResultSize) throws ParseException, IOException {
		final AbstractBenchmarkLogic bl = bi.initializeBenchmark(queryName, Scenario.BATCH);
		runQuery(bl, expectedResultSize);
	}

	protected void runQuery(final AbstractBenchmarkLogic bl, final int expectedResultSize) throws IOException {
		final AbstractBenchmarkCase<?> testCase = bl.getTestCase();
		try {
			testCase.benchmarkInit(bl.getBc());
			testCase.benchmarkRead();
			testCase.benchmarkCheck();
			assertEquals(expectedResultSize, testCase.getResults().size());
		} finally {
			testCase.benchmarkDestroy();
		}
	}

}