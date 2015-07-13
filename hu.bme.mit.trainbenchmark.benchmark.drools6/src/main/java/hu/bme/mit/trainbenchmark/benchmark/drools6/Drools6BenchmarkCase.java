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

package hu.bme.mit.trainbenchmark.benchmark.drools6;

import hu.bme.mit.trainbenchmark.benchmark.drools6.checkers.Drools6Checker;
import hu.bme.mit.trainbenchmark.benchmark.drools6.driver.Drools6Driver;
import hu.bme.mit.trainbenchmark.constants.Scenario;
import hu.bme.mit.trainbenchmark.emf.benchmarkcases.EMFBenchmarkCase;
import hu.bme.mit.trainbenchmark.emf.transformation.EMFTransformation;

public class Drools6BenchmarkCase extends EMFBenchmarkCase {

	protected Drools6Driver drools6driver;

	@Override
	public void init() throws Exception {
		driver = drools6driver = new Drools6Driver(bc);
		checker = new Drools6Checker(drools6driver, bc.getQuery());

		if (bc.getScenario() != Scenario.BATCH) {
			transformation = EMFTransformation.newInstance(drools6driver,
					bc.getQuery(), bc.getScenario());
		}
	}

}
