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

import hu.bme.mit.trainbenchmark.benchmark.checker.Checker;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameMatch;
import hu.bme.mit.trainbenchmark.constants.Query;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.repository.RepositoryException;

public class SesameChecker extends Checker<SesameMatch> {

	protected final SesameDriver driver;
	protected final Query query;
	protected final String queryDefinition;

	public SesameChecker(final SesameDriver driver, final BenchmarkConfig bc) throws IOException {
		super();
		this.driver = driver;
		this.query = bc.getQuery();

		final String queryPath = bc.getWorkspacePath() + "/hu.bme.mit.trainbenchmark.benchmark.rdf/src/main/resources/queries/" + bc.getQuery()
				+ ".sparql";
		this.queryDefinition = FileUtils.readFileToString(new File(queryPath));
	}

	@Override
	public Collection<SesameMatch> check() throws RepositoryException, MalformedQueryException, QueryEvaluationException {
		return driver.runQuery(query, queryDefinition);
	}

}
