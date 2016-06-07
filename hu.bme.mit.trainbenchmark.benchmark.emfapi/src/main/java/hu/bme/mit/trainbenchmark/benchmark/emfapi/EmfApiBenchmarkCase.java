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

package hu.bme.mit.trainbenchmark.benchmark.emfapi;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.emf.benchmarkcases.EmfBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.emf.driver.EmfDriver;

public class EmfApiBenchmarkCase<TBenchmarkConfigWrapper extends BenchmarkConfigWrapper> extends EmfBenchmarkCase<EmfDriver, TBenchmarkConfigWrapper> {

	@Override
	public EmfDriver createDriver(final TBenchmarkConfigWrapper benchmarkConfigWrapper) throws Exception {
		return EmfDriver.create();
	}

}
