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
package hu.bme.mit.trainbenchmark.benchmark.hawk;

import java.io.IOException;
import java.util.Comparator;

import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.Transformation;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.checker.EMFIncQueryChecker;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.matches.EMFIncQueryMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.hawk.config.HawkBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.hawk.driver.HawkDriver;
import hu.bme.mit.trainbenchmark.benchmark.hawk.transformation.HawkTransformation;
import hu.bme.mit.trainbenchmark.railway.RailwayElement;

public class HawkBenchmarkCase<TMatch extends BasePatternMatch>
		extends AbstractBenchmarkCase<TMatch, RailwayElement, HawkDriver<TMatch>, HawkBenchmarkConfig, EMFIncQueryChecker<TMatch>> {

	@Override
	public HawkDriver<TMatch> createDriver(final HawkBenchmarkConfig benchmarkConfig) throws Exception {
		return new HawkDriver<>(benchmarkConfig);
	}

	@Override
	public EMFIncQueryChecker<TMatch> createChecker(final HawkBenchmarkConfig benchmarkConfig, final HawkDriver<TMatch> driver) throws Exception {
		final EMFIncQueryChecker<TMatch> checker = (EMFIncQueryChecker<TMatch>) EMFIncQueryChecker.newInstance(benchmarkConfig, driver,
				benchmarkConfig.getQueries());
		driver.registerChecker(checker);
		return checker;
	}

	@Override
	public Transformation<?, ?> createTransformation(final HawkBenchmarkConfig benchmarkConfig, final HawkDriver<TMatch> driver)
			throws IOException {
		return HawkTransformation.newInstance(driver, benchmarkConfig.getQueries(), benchmarkConfig.getScenario(), benchmarkConfig);
	}

	@Override
	public Comparator<?> createMatchComparator() {
		return new EMFIncQueryMatchComparator();
	}

}
