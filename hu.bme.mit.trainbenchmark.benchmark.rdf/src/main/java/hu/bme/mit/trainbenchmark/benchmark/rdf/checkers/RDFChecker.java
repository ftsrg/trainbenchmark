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

import java.io.File;

import hu.bme.mit.trainbenchmark.benchmark.checker.Checker;
import hu.bme.mit.trainbenchmark.benchmark.rdf.RDFBenchmarkConfig;
import hu.bme.mit.trainbenchmark.constants.Query;

public abstract class RDFChecker<M> extends Checker<M> {

	protected final Query query;
	protected final String queryPath;

	public RDFChecker(final RDFBenchmarkConfig rdfbc) {
		query = rdfbc.getQuery();

		final String queryDirectory = rdfbc.getWorkspacePath() + "hu.bme.mit.trainbenchmark.benchmark.rdf/src/main/resources/queries/";
		final String inferencingQueryPath = queryDirectory + rdfbc.getQuery() + "-inferencing.sparql";
		final String noInferencingQueryPath = queryDirectory + rdfbc.getQuery() + ".sparql";

		// if we use inferencing mode and there is a specific query available, we use that
		if (rdfbc.isInferencing() && new File(inferencingQueryPath).exists()) {
			queryPath = inferencingQueryPath;
		} else {
			queryPath = noInferencingQueryPath;
		}
	}

}
