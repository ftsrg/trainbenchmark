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

import static hu.bme.mit.trainbenchmark.constants.Query.CONNECTEDSEGMENTS;
import static hu.bme.mit.trainbenchmark.constants.Query.POSLENGTH;
import static hu.bme.mit.trainbenchmark.constants.Query.ROUTESENSOR;
import static hu.bme.mit.trainbenchmark.constants.Query.SEMAPHORENEIGHBOR;
import static hu.bme.mit.trainbenchmark.constants.Query.SWITCHSENSOR;
import static hu.bme.mit.trainbenchmark.constants.Query.SWITCHSET;
import static org.hamcrest.Matchers.is;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.scenarios.AbstractBenchmarkLogic;
import hu.bme.mit.trainbenchmark.benchmark.util.BenchmarkResult;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.constants.Scenario;

import java.io.IOException;

import org.apache.commons.cli.ParseException;
import org.junit.Test;

public abstract class BatchTest extends TrainBenchmarkTest {

	protected void testQuery(final Query query, final int expectedResultSize) throws ParseException, IOException {
		final AbstractBenchmarkLogic bl = bi.initializeBenchmark(query, Scenario.BATCH);
		runQuery(bl, expectedResultSize);
	}

	protected void runQuery(final AbstractBenchmarkLogic bl, final int expectedResultSize) throws IOException {
		final AbstractBenchmarkCase<?, ?> benchmarkCase = bl.getBenchmarkCase();

		try {
			benchmarkCase.benchmarkInit(bl.getBc());
			benchmarkCase.benchmarkRead();
			benchmarkCase.benchmarkCheck();
			
			final int resultSize = benchmarkCase.getMatches().size();
			collector.checkThat(resultSize, is(expectedResultSize));

			final BenchmarkResult br = benchmarkCase.getBenchmarkResult();
			br.publish(true);
		} finally {
			benchmarkCase.benchmarkDestroy();
		}
	}

	@Test
	public void connectedSegments() throws ParseException, IOException {
		testQuery(CONNECTEDSEGMENTS, 4);
	}

	@Test
	public void posLength() throws ParseException, IOException {
		testQuery(POSLENGTH, 92);
	}

	@Test
	public void routeSensor() throws ParseException, IOException {
		testQuery(ROUTESENSOR, 7);
	}

	@Test
	public void semaphoreNeighbor() throws ParseException, IOException {
		testQuery(SEMAPHORENEIGHBOR, 1);
	}

	@Test
	public void switchSensor() throws ParseException, IOException {
		testQuery(SWITCHSENSOR, 3);
	}

	@Test
	public void switchSet() throws ParseException, IOException {
		testQuery(SWITCHSET, 2);
	}
	
}