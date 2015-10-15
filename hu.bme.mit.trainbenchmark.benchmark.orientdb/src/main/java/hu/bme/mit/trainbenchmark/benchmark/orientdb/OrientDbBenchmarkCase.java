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
package hu.bme.mit.trainbenchmark.benchmark.orientdb;

import java.io.IOException;
import java.util.Comparator;

import com.tinkerpop.blueprints.Vertex;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.Transformation;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.checkers.OrientDbChecker;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.driver.OrientDbDriver;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.matches.OrientDbMatch;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.matches.OrientDbMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.transformations.OrientDbTransformation;

public class OrientDbBenchmarkCase
		extends AbstractBenchmarkCase<OrientDbMatch, Vertex, OrientDbDriver, BenchmarkConfig, OrientDbChecker<OrientDbMatch>> {

	@Override
	public OrientDbDriver createDriver(final BenchmarkConfig benchmarkConfig) throws Exception {
		return new OrientDbDriver(benchmarkConfig);
	}

	@Override
	public OrientDbChecker<OrientDbMatch> createChecker(final BenchmarkConfig benchmarkConfig, final OrientDbDriver driver)
			throws Exception {
		return OrientDbChecker.newInstance(driver, benchmarkConfig.getQuery());
	}

	@Override
	public Comparator<?> createMatchComparator() {
		return new OrientDbMatchComparator();
	}

	@Override
	public Transformation<?, ?> createTransformation(final BenchmarkConfig benchmarkConfig, final OrientDbDriver driver) throws IOException {
		return OrientDbTransformation.newInstance(driver, benchmarkConfig.getQuery(), benchmarkConfig.getScenario());
	}

}
