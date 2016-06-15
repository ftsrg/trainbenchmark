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

package hu.bme.mit.trainbenchmark.benchmark.test;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.phases.BenchmarkScenario;
import hu.bme.mit.trainbenchmark.constants.TransformationStrategy;

public abstract class TestBenchmarkInitializer<TPatternMatch, TDriver extends Driver<?>, TConfig extends BenchmarkConfigWrapper> {

	protected final int size = 1;
	protected final TransformationStrategy transformationStrategy = TransformationStrategy.FIXED;
	protected final int transformationConstant = 2;
	protected final int runIndex = 1;
	protected final int iterationCount = 1;

	public abstract BenchmarkScenario<TPatternMatch, TDriver, TConfig> initialize(TConfig config);
}
