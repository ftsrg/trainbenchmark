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

package hu.bme.mit.trainbenchmark.benchmark.drools5;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.drools5.driver.Drools5Driver;
import hu.bme.mit.trainbenchmark.benchmark.emf.benchmarkcases.EmfBenchmarkCase;

public class Drools5BenchmarkCase extends EmfBenchmarkCase<Drools5Driver, BenchmarkConfigWrapper> {

	@Override
	public Drools5Driver createDriver(final BenchmarkConfigWrapper benchmarkConfigWrapper) throws Exception {
		return new Drools5Driver();
	}

}
