/*******************************************************************************
 * Copyright (c) 2010-2015, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
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
import hu.bme.mit.trainbenchmark.benchmark.util.BenchmarkResult;

import java.util.List;

public abstract class AbstractBenchmarkCase<T> implements BenchmarkCase {

	protected BenchmarkResult bmr;
	protected BenchmarkConfig bc;
	protected List<T> invalids;
	
	public List<T> getInvalids() {
		return invalids;
	}

	@Override
	public BenchmarkResult getBenchmarkResult() {
		return bmr;
	}

	@Override
	public String getName() {
		return bc.getQuery();
	}

	@Override
	public int getResultSize() {
		return invalids.size();
	}
	
	
}
