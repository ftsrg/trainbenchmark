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

package hu.bme.mit.trainbenchmark.benchmark.allegro;

import hu.bme.mit.trainbenchmark.benchmark.allegro.driver.AllegroDriver;
import hu.bme.mit.trainbenchmark.benchmark.rdf.RDFBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.sesame.SesameBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.sesame.checkers.SesameChecker;
import hu.bme.mit.trainbenchmark.benchmark.sesame.transformations.SesameTransformation;

import java.io.IOException;

public class AllegroBenchmarkCase extends SesameBenchmarkCase {

	@Override
	protected void init() throws IOException {
		this.rbc = (RDFBenchmarkConfig) bc;

		driver = sesameDriver = new AllegroDriver();
		checker = new SesameChecker(sesameDriver, bc);

		transformation = SesameTransformation.newInstance(sesameDriver, bc.getQuery(),
				bc.getScenario());
		initAnalyzer();
	}

}
