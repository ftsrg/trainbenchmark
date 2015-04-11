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

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.driver.DatabaseDriver;
import hu.bme.mit.trainbenchmark.benchmark.util.BenchmarkResult;
import hu.bme.mit.trainbenchmark.benchmark.util.UniqRandom;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;
import hu.bme.mit.trainbenchmark.constants.TrainBenchmarkConstants;

import java.io.IOException;
import java.util.Collection;
import java.util.Random;

public abstract class AbstractBenchmarkCase<M, T> {

	protected Random random = new UniqRandom(TrainBenchmarkConstants.RANDOM_SEED);
	protected BenchmarkResult bmr;
	protected BenchmarkConfig bc;
	protected DatabaseDriver<M, T> driver;
	protected Collection<M> matches;

	// simple getters and setters
	public BenchmarkResult getBenchmarkResult() {
		return bmr;
	}

	// shorthands
	public String getName() {
		return bc.getQuery();
	}

	public Collection<M> getMatches() {
		return matches;
	}

	// these should be implemented for each tool

	protected void init() throws IOException {
	}

	protected void destroy() throws IOException {
	}

	// protected abstract TransformationAction getTransformationAction();

	// @SuppressWarnings("unchecked")
	protected void modify() throws IOException {
		System.out.println("Transformation comes here.");
		// final String className = "hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations."
		// + bc.getScenario().toString().toLowerCase() + "." + bc.getScenarioName() + bc.getQuery();
		// Transformation transformation;
		// switch (bc.getScenario()) {
		// case REPAIR:
		// transformation = new RepairTransformation<>();
		// break;
		// case USER:
		// transformation = new UserTransformation<>();
		// break;
		// default:
		// throw new UnsupportedOperationException("Scenario " + bc.getScenarioName() + " not supported");
		// }
		// transformation.initialize(bc, getBenchmarkResult(), driver, matches, random);
		// transformation.performTransformation();
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
		driver.read(bc.getModelPathNameWithoutExtension());
		bmr.setReadTime();
	}

	public void benchmarkCheck() throws IOException {
		bmr.restartClock();
		matches = driver.check();
		bmr.addMatchCount(matches.size());
		bmr.addCheckTime();
	}

	protected void runGC() throws IOException {
		Util.runGC();
		if (bc.isBenchmarkMode()) {
			Util.freeCache(bc);
		}
	}

}
