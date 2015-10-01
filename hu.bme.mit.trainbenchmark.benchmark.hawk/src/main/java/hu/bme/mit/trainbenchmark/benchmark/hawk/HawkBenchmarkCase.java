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

import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.EMFIncQueryBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.checker.EMFIncQueryChecker;
import hu.bme.mit.trainbenchmark.benchmark.hawk.config.HawkBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.hawk.driver.HawkDriver;

public class HawkBenchmarkCase<M extends BasePatternMatch> extends EMFIncQueryBenchmarkCase<M> {

	protected HawkDriver<M> hawkDriver;

	protected HawkBenchmarkConfig getHawkBenchmarkConfig() {
		return (HawkBenchmarkConfig) bc;
	}

	@Override
	public void init() throws IOException {
		super.init();
	}

	@Override
	public void benchmarkInit(final BenchmarkConfig bc) throws Exception {
		super.benchmarkInit(bc);

		final HawkBenchmarkConfig hbc = (HawkBenchmarkConfig) bc;
		driver = eiqDriver = hawkDriver = new HawkDriver<>(hbc);
		final EMFIncQueryChecker eiqChecker = EMFIncQueryChecker.newInstance(getHawkBenchmarkConfig(), hawkDriver, bc.getQuery());
		checker = eiqChecker;

		hawkDriver.registerChecker(eiqChecker);
	}

}
