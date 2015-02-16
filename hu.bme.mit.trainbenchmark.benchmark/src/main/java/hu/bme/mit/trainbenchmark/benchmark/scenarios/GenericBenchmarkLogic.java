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

package hu.bme.mit.trainbenchmark.benchmark.scenarios;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.BenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;

import java.io.IOException;

import org.apache.commons.cli.ParseException;

public abstract class GenericBenchmarkLogic {

	protected BenchmarkConfig bc;

	public GenericBenchmarkLogic(final String[] args) throws ParseException {

	}

	@SuppressWarnings("unchecked")
	public void runBenchmark() throws IOException {
		final Scenario scl = ScenarioFactory.getScenarioLogic(bc.getScenario());
		final BenchmarkCase tc = getTestCase();
		scl.runBenchmark(bc, tc);
	}

	public BenchmarkCase getTestCase() {
		return getTestCase(this.getClass().getClassLoader());
	}

	protected BenchmarkCase getTestCase(final ClassLoader classLoader) {
		String className;
		if (("XForm".equals(bc.getScenario())) || ("User".equals(bc.getScenario()))) {
			className = "hu.bme.mit.trainbenchmark.benchmark." + getPackageName() + ".benchmarkcases." + bc.getScenario().toLowerCase() + "." + bc.getQuery();
		} else {
			className = "hu.bme.mit.trainbenchmark.benchmark." + getPackageName() + ".benchmarkcases.SesameBenchmarkCase";
		}
		try {
			return (BenchmarkCase) classLoader.loadClass(className).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public BenchmarkConfig getBc() {
		return bc;
	}

	protected abstract String getPackageName();
}
