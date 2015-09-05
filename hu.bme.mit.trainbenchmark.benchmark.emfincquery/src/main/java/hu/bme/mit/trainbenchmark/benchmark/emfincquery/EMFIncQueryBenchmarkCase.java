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
package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import hu.bme.mit.trainbenchmark.benchmark.analyzer.Analyzer;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.BenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.layers.AnalyzedBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.checker.EMFIncQueryChecker;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.config.EMFIncQueryBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.driver.EMFIncQueryDriver;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.matches.EMFIncQueryMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.transformations.EMFIncQueryTransformation;
import hu.bme.mit.trainbenchmark.emf.analyzer.EMFModelAnalyzer;
import hu.bme.mit.trainbenchmark.railway.RailwayElement;

import java.util.Comparator;

import org.apache.log4j.Level;
import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;
import org.eclipse.incquery.runtime.util.IncQueryLoggingUtil;

public class EMFIncQueryBenchmarkCase<M extends BasePatternMatch> extends
		BenchmarkCase<M, RailwayElement, EMFIncQueryDriver<M>> implements AnalyzedBenchmarkCase {

	protected EMFIncQueryDriver<M> eiqDriver;

	protected EMFIncQueryBenchmarkConfig getEMFIncQueryBenchmarkConfig() {
		return (EMFIncQueryBenchmarkConfig) benchmarkConfig;
	}

	@Override
	public void init() throws Exception {
		final EMFIncQueryBenchmarkConfig eiqbc = (EMFIncQueryBenchmarkConfig) benchmarkConfig;
		driver = eiqDriver = new EMFIncQueryDriver(getEMFIncQueryBenchmarkConfig());
		final EMFIncQueryChecker eiqChecker = EMFIncQueryChecker.newInstance(eiqbc, eiqDriver,
				benchmarkConfig.getQuery());
		checker = eiqChecker;
		eiqDriver.registerChecker(eiqChecker);

		transformation = EMFIncQueryTransformation.newInstance(eiqDriver, benchmarkConfig.getQuery(),
				benchmarkConfig.getScenario());
		IncQueryLoggingUtil.getDefaultLogger().setLevel(Level.OFF);

	}

	@Override
	protected Comparator<?> getMatchComparator() {
		return new EMFIncQueryMatchComparator();
	}

	@Override
	public void initAnalyzer() {
		modelAnalyzer = (Analyzer) new EMFModelAnalyzer(eiqDriver);
	}

}
