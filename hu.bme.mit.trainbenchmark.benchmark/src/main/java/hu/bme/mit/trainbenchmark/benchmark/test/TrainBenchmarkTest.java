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

import static org.hamcrest.Matchers.equalTo;
import hu.bme.mit.trainbenchmark.benchmark.scenarios.AbstractBenchmarkLogic;
import hu.bme.mit.trainbenchmark.benchmark.util.BenchmarkResult;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.constants.Scenario;

import java.io.IOException;

import org.apache.commons.cli.ParseException;
import org.junit.Rule;
import org.junit.rules.ErrorCollector;

public abstract class TrainBenchmarkTest {

	@Rule
	public ErrorCollector collector = new ErrorCollector();

	protected TestBenchmarkInitializer<?> bi;

	public AbstractBenchmarkLogic initialize(final Query query, final String tool, final Scenario scenario) throws IOException {
		return bi.initializeBenchmark(query, scenario);
	}

	protected void testQuery(final Query query, final Scenario scenario, final int expectedResultSize) throws ParseException, IOException {
		final AbstractBenchmarkLogic bl = bi.initializeBenchmark(query, scenario);
		runQuery(bl, expectedResultSize);
	}

	private void runQuery(final AbstractBenchmarkLogic bl, final long expectedResultSize) throws IOException {
		final BenchmarkResult br = bl.runBenchmark();
		collector.checkThat(br.getLastResultSize(), equalTo(expectedResultSize));	
	}

}
