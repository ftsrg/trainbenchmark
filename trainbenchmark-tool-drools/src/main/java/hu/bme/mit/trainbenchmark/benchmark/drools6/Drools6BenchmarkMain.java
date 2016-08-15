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

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.drools6.config.Drools6BenchmarkConfigWrapper;

public class Drools6BenchmarkMain {

	public static void main(final String[] args) throws Exception {
		final Drools6BenchmarkConfigWrapper bcw = BenchmarkConfigWrapper.fromFile(args[0], Drools6BenchmarkConfigWrapper.class);
		final Drools6BenchmarkScenario scenario = new Drools6BenchmarkScenario(bcw);
		scenario.performBenchmark();	
	}

}
