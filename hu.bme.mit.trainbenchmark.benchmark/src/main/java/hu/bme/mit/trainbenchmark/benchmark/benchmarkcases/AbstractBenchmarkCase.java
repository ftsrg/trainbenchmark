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

package hu.bme.mit.trainbenchmark.benchmark.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.TransformationDefinition;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.driver.DatabaseDriver;
import hu.bme.mit.trainbenchmark.benchmark.util.BenchmarkResult;
import hu.bme.mit.trainbenchmark.benchmark.util.UniqRandom;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;
import hu.bme.mit.trainbenchmark.constants.TrainBenchmarkConstants;

import java.io.IOException;
import java.util.Collection;
import java.util.Random;

public abstract class AbstractBenchmarkCase<T> {
	
	protected Random random = new UniqRandom(TrainBenchmarkConstants.RANDOM_SEED);
	protected BenchmarkResult bmr;
	protected BenchmarkConfig bc;
	protected DatabaseDriver<T> driver;
	protected Collection<T> results;

	// simple getters and setters
	public BenchmarkResult getBenchmarkResult() {
		return bmr;
	}

	// shorthands
	public String getName() {
		return bc.getQuery();
	}

	public Collection<T> getResults() {
		return results;
	}

	// these should be implemented for each tool

	protected void init() throws IOException {}

	protected void destroy() throws IOException {}

	protected abstract void read() throws IOException;

	protected abstract Collection<T> check() throws IOException;

	public void benchmarkModify() throws IOException {
		modify();
	}
	
	protected void modify() throws IOException {
		final String className = "hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations." + bc.getScenario().toString().toLowerCase()
				+ "." + bc.getQuery();
		try {
			final Class<?> clazz = this.getClass().getClassLoader().loadClass(className);
			final TransformationDefinition td = (TransformationDefinition) clazz.newInstance();
			td.initialize(getBenchmarkResult(), driver, results, random);
			td.performTransformation();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw new UnsupportedOperationException(e);
		}
	}

	protected long getMemoryUsage() throws IOException {
		Util.runGC();
		return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
	}

	public void benchmarkInit(final BenchmarkConfig bc) throws IOException {
		this.bc = bc;
		
		bmr = new BenchmarkResult(bc.getTool(), bc.getQuery());
		bmr.setBenchmarkConfig(bc);
		init();
		runGC();
	}

	public void benchmarkDestroy() throws IOException {
		destroy();
	}

	public void benchmarkRead() throws IOException {
		bmr.restartClock();
		read();
		bmr.setReadTime();
	}

	public void benchmarkCheck() throws IOException {
		bmr.restartClock();
		check();
		bmr.addResultSize(results.size());
		bmr.addCheckTime();
	}

	protected void runGC() throws IOException {
		Util.runGC();
		if (bc.isBenchmarkMode()) {
			Util.freeCache(bc);
		}
	}
	
}
