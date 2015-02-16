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

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.TransformationDefinition;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.driver.DatabaseDriver;
import hu.bme.mit.trainbenchmark.benchmark.util.BenchmarkResult;

import java.io.IOException;
import java.util.List;

public abstract class AbstractTransformationBenchmarkCase<T> implements TransformationBenchmarkCase  {

	protected BenchmarkResult bmr;
	protected BenchmarkConfig bc;
	protected DatabaseDriver driver;
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
	
	@Override
	public void modify() throws IOException {
		final String className = "hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations." + bc.getScenario().toLowerCase() + "." + bc.getQuery();
		try {
			final Class<?> clazz = this.getClass().getClassLoader().loadClass(className);
			final TransformationDefinition td = (TransformationDefinition) clazz.newInstance();
			td.initialize(bmr, driver, invalids);
			td.performTransformation();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw new UnsupportedOperationException(e);
		}
	}
	
}
