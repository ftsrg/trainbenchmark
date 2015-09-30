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

import org.apache.log4j.Level;
import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;
import org.eclipse.incquery.runtime.util.IncQueryLoggingUtil;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.EMFIncQueryBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.config.EMFIncQueryBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.hawk.driver.HawkDriver;
import hu.bme.mit.trainbenchmark.benchmark.hawk.matches.HawkMatchComparator;

public class HawkBenchmarkCase<M extends BasePatternMatch> extends EMFIncQueryBenchmarkCase<M> {

	protected HawkDriver<M> eiqDriver;

	@Override
	protected EMFIncQueryBenchmarkConfig getEMFIncQueryBenchmarkConfig() {
		return (EMFIncQueryBenchmarkConfig) bc;
	}

	@Override
	public void init() throws IOException {
		super.init();
		IncQueryLoggingUtil.getDefaultLogger().setLevel(Level.OFF);
	}

	@Override
	public void benchmarkInit(final BenchmarkConfig bc) throws Exception {
		super.benchmarkInit(bc);

	}

	@Override
	protected Comparator<?> getMatchComparator() {
		return new HawkMatchComparator();
	}

}
