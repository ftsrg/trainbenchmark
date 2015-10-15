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
package hu.bme.mit.trainbenchmark.benchmark.rdf.checkers;

import hu.bme.mit.trainbenchmark.benchmark.checker.Checker;
import hu.bme.mit.trainbenchmark.benchmark.rdf.RDFBenchmarkConfig;
import hu.bme.mit.trainbenchmark.constants.Query;

public abstract class RDFChecker<TMatch> extends Checker<TMatch> {

	protected final Query query;
	protected final String queryPath;

	public RDFChecker(final RDFBenchmarkConfig benchmarkConfig) {
		query = benchmarkConfig.getQuery();

		queryPath = benchmarkConfig.getWorkspacePath() + "hu.bme.mit.trainbenchmark.benchmark.rdf/src/main/resources/queries/" + benchmarkConfig.getQuery()
				+ ".sparql";
	}

}
