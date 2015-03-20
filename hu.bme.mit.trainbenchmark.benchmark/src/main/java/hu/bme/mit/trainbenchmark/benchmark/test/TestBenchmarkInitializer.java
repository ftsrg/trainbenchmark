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

package hu.bme.mit.trainbenchmark.benchmark.test;

import hu.bme.mit.trainbenchmark.benchmark.config.ModificationMethod;
import hu.bme.mit.trainbenchmark.benchmark.scenarios.AbstractBenchmarkLogic;
import hu.bme.mit.trainbenchmark.constants.Scenario;

public abstract class TestBenchmarkInitializer<T extends AbstractBenchmarkLogic> {

	protected final int size = 1;
	protected final ModificationMethod modificationMethod = ModificationMethod.CONSTANT;
	protected final int modificationConstant = 1;
	protected final int runIndex = 1;
	protected final int iterationCount = 1;
	
	protected abstract T initializeBenchmark(String queryName, Scenario scenario);

}
