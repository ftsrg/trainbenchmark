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
package hu.bme.mit.trainbenchmark.benchmark.sesame.checkers;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.repository.RepositoryException;

import hu.bme.mit.trainbenchmark.benchmark.rdf.RDFBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.rdf.checkers.RDFChecker;
import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class SesameChecker extends RDFChecker<SesameMatch> {

	protected final SesameDriver driver;
	protected final String queryDefinition;

	public SesameChecker(final SesameDriver driver, final RDFBenchmarkConfig benchmarkConfig, final RailwayQuery query) throws IOException {
		super(benchmarkConfig, query);
		this.driver = driver;
		this.queryDefinition = FileUtils.readFileToString(new File(queryPath));
	}

	@Override
	public Collection<SesameMatch> check() throws RepositoryException, MalformedQueryException, QueryEvaluationException {
		return driver.runQuery(query, queryDefinition);
	}

}
