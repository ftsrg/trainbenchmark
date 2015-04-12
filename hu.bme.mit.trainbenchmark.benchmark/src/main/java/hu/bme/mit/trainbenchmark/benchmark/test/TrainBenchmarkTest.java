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

import hu.bme.mit.trainbenchmark.benchmark.scenarios.AbstractBenchmarkLogic;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.constants.Scenario;

import java.io.IOException;

public abstract class TrainBenchmarkTest {

	protected TestBenchmarkInitializer<?> bi;

	public AbstractBenchmarkLogic initialize(final Query query, final String tool, final Scenario scenario) throws IOException {
		return bi.initializeBenchmark(query, scenario);
	}

}
