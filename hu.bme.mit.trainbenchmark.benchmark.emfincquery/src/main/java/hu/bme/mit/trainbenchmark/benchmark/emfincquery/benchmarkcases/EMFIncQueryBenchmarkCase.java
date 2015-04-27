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
package hu.bme.mit.trainbenchmark.benchmark.emfincquery.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.config.EMFIncQueryBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.driver.EMFIncQueryDriver;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.matches.EMFIncQueryMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.transformations.EMFIncQueryTransformation;
import hu.bme.mit.trainbenchmark.constants.Scenario;
import hu.bme.mit.trainbenchmark.railway.RailwayContainer;
import hu.bme.mit.trainbenchmark.railway.RailwayElement;

import java.io.IOException;
import java.util.Comparator;

import org.apache.log4j.Level;
import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;
import org.eclipse.incquery.runtime.util.IncQueryLoggingUtil;

public class EMFIncQueryBenchmarkCase<M extends BasePatternMatch> extends AbstractBenchmarkCase<M, RailwayElement> {

	protected EMFIncQueryDriver<M> eiqDriver;
	protected RailwayContainer container;

	protected EMFIncQueryBenchmarkConfig getEMFIncQueryBenchmarkConfig() {
		return (EMFIncQueryBenchmarkConfig) bc;
	}

	@Override
	public void init() throws IOException {
		IncQueryLoggingUtil.getDefaultLogger().setLevel(Level.OFF);
	}

	@Override
	public void benchmarkInit(final BenchmarkConfig bc) throws IOException {
		super.benchmarkInit(bc);
		eiqDriver = new EMFIncQueryDriver(getEMFIncQueryBenchmarkConfig());
		driver = eiqDriver;
		final EMFIncQueryChecker eiqChecker = EMFIncQueryChecker.newInstance(eiqDriver, bc.getQuery());
		checker = eiqChecker;
		eiqDriver.registerChecker(eiqChecker);
		if (bc.getScenario() != Scenario.BATCH) {
			transformation = EMFIncQueryTransformation.newInstance(eiqDriver, bc.getQuery(), bc.getScenario());
		}
	}

	@Override
	protected Comparator<?> getComparator() {
		switch (bc.getScenario()) {
		case BATCH:
		case USER:
			return driver.getElementComparator();
		case REPAIR:
			return new EMFIncQueryMatchComparator();
		default:
			return null;
		}
	}

}
