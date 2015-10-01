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

import java.io.IOException;

import org.apache.commons.cli.ParseException;

import hu.bme.mit.trainbenchmark.benchmark.rdf.RDFBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.scenarios.BenchmarkLogic;

public class AllegroBenchmarkMain {

	public static void main(final String[] args) throws ParseException, IOException {
		final RDFBenchmarkConfig rdfbc = new RDFBenchmarkConfig(args, "AllegroGraph");
		final BenchmarkLogic allegroBenchmarkLogic = new BenchmarkLogic(rdfbc, new AllegroBenchmarkCase());
		allegroBenchmarkLogic.runBenchmark();
	}
}
