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
import hu.bme.mit.trainbenchmark.benchmark.util.Util;

import java.io.IOException;
import java.util.List;

public abstract class AbstractBenchmarkCase<T> {
	
	private BenchmarkResult bmr;
	protected BenchmarkConfig bc;
	protected DatabaseDriver driver;
	protected List<T> results;

	// simple getters and setters
	public BenchmarkResult getBenchmarkResult() {
		return bmr;
	}

	// shorthands
	public String getName() {
		return bc.getQuery();
	}

	public List<T> getResults() {
		return results;
	}

	// these should be implemented for each tools

	protected void init() throws IOException {}

	protected void destroy() throws IOException {}

	protected abstract void load() throws IOException;

	protected abstract List<T> check() throws IOException;

	protected long getMemoryUsage() throws IOException {
		Util.runGC();
		return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
	}

	public void benchmarkInit(final BenchmarkConfig bc) throws IOException {
		this.bc = bc;
		
		bmr = new BenchmarkResult(bc.getTool(), bc.getQuery());
		bmr.setBenchmarkConfig(bc);
		init();
		
		runGC(bc);
	}

	public void benchmarkDestroy() throws IOException {
		destroy();
	}

	public void benchmarkLoad() throws IOException {
		bmr.startStopper();
		load();
		bmr.setReadTime();
	}

	public void benchmarkCheck() throws IOException {
		bmr.startStopper();
		check();
		bmr.addInvalid();
		bmr.addCheckTime();
	}

	protected void runGC(final BenchmarkConfig bc) throws IOException {
		Util.runGC();
		if (bc.isBenchmarkMode()) {
			Util.freeCache(bc);
		}
	}
	
}
