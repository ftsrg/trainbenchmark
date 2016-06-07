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

package hu.bme.mit.trainbenchmark.benchmark.blazegraph;

import hu.bme.mit.trainbenchmark.benchmark.blazegraph.driver.BlazegraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.rdf.RdfBenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.sesame.SesameBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;

public class BlazegraphBenchmarkCase extends SesameBenchmarkCase {

	@Override
	public SesameDriver createDriver(final RdfBenchmarkConfigWrapper benchmarkConfig) throws Exception {
		return new BlazegraphDriver(benchmarkConfig.isInferencing());
	}

}
