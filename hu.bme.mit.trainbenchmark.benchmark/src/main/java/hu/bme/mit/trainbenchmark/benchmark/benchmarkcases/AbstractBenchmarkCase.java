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

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.Transformation;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.TransformationLogic;
import hu.bme.mit.trainbenchmark.benchmark.checker.Checker;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.util.BenchmarkResult;
import hu.bme.mit.trainbenchmark.benchmark.util.UniqRandom;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.constants.TrainBenchmarkConstants;

import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.Random;

public abstract class AbstractBenchmarkCase<M, T> {

	protected Random random = new UniqRandom(TrainBenchmarkConstants.RANDOM_SEED);
	protected BenchmarkResult br;
	protected BenchmarkConfig bc;
	protected Driver<T> driver;
	protected Checker<M> checker;
	protected Collection<M> matches;
	protected TransformationLogic<M, T, ?> transformationLogic;
	protected Transformation<?> transformation;

	// simple getters and setters
	public BenchmarkResult getBenchmarkResult() {
		return br;
	}

	public Collection<M> getMatches() {
		return matches;
	}

	// shorthands
	public Query getQuery() {
		return bc.getQuery();
	}

	// these should be implemented for each tool

	protected void init() throws IOException {
	}

	protected void destroy() throws IOException {
	}

	protected long getMemoryUsage() throws IOException {
		Util.runGC();
		return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
	}

	public void benchmarkInit(final BenchmarkConfig bc) throws IOException {
		this.bc = bc;
		br = BenchmarkResult.newInstance(bc);
		init();
	}

	public void benchmarkInitTransformation() {
		transformationLogic = TransformationLogic.newInstance(bc.getScenario(), getComparator());
		if (transformationLogic != null) {
			transformationLogic.initialize(bc, br, driver, random);
		}
		transformationLogic.setTransformation(transformation);
	}

	// benchmark methods

	public void benchmarkRead() throws IOException {
		br.restartClock();
		driver.read(bc.getModelPathNameWithoutExtension());
		br.setReadTime();
	}

	public void benchmarkCheck() throws IOException {
		br.restartClock();
		matches = checker.check();
		br.addMatchCount(matches.size());
		br.addCheckTime();
	}

	public void benchmarkDestroy() throws IOException {
		destroy();
	}

	public void benchmarkModify() throws IOException {
		transformationLogic.performTransformation(matches);
	}

	protected abstract Comparator<?> getComparator();

}
