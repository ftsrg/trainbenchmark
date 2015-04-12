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
import hu.bme.mit.trainbenchmark.benchmark.scenarios.AbstractBenchmarkLogic;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.constants.Scenario;

import java.io.IOException;

import org.apache.commons.cli.ParseException;
import org.junit.Test;

public abstract class BatchTest extends TrainBenchmarkTest {

	@Test
	public void posLength() throws ParseException, IOException {
		testQuery(Query.POSLENGTH, 411);
	}

	@Test
	public void routeSensor() throws ParseException, IOException {
		testQuery(Query.ROUTESENSOR, 26);
	}

	@Test
	public void semaphoreNeighbor() throws ParseException, IOException {
		testQuery(Query.SEMAPHORENEIGHBOR, 0);
	}

	@Test
	public void switchSensor() throws ParseException, IOException {
		testQuery(Query.SWITCHSENSOR, 5);
	}

	@Test
	public void switchSet() throws ParseException, IOException {
		testQuery(Query.SWITCHSET, 11);
	}

	protected void testQuery(final Query query, final int expectedResultSize) throws ParseException, IOException {
		final AbstractBenchmarkLogic bl = bi.initializeBenchmark(query, Scenario.BATCH);
		runQuery(bl, expectedResultSize);
	}

	protected void runQuery(final AbstractBenchmarkLogic bl, final int expectedResultSize) throws IOException {
		final AbstractBenchmarkCase<?, ?> testCase = bl.getBenchmarkCase();
		try {
			testCase.benchmarkInit(bl.getBc());
			testCase.benchmarkRead();
			testCase.benchmarkCheck();
			assertEquals(expectedResultSize, testCase.getMatches().size());
		} finally {
			testCase.benchmarkDestroy();
		}
	}

}