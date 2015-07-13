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
package hu.bme.mit.trainbenchmark.benchmark.jena.checkers;

import hu.bme.mit.trainbenchmark.benchmark.checker.Checker;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.jena.driver.JenaDriver;
import hu.bme.mit.trainbenchmark.benchmark.jena.match.JenaMatch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;

public class JenaChecker extends Checker<JenaMatch> {

	protected JenaDriver jenaDriver;
	protected Query query;
	protected hu.bme.mit.trainbenchmark.constants.Query tbQuery;

	public JenaChecker(final JenaDriver jenaDriver, final BenchmarkConfig bc) throws IOException {
		super();
		this.jenaDriver = jenaDriver;
		final String queryPath = bc.getWorkspacePath() + "/hu.bme.mit.trainbenchmark.benchmark.rdf/src/main/resources/queries/" + bc.getQuery()
				+ ".sparql";
		tbQuery = bc.getQuery();
		
		query = QueryFactory.read(queryPath);
	}

	@Override
	public Collection<JenaMatch> check() throws IOException {
		final List<JenaMatch> matches = new ArrayList<>();
		try (QueryExecution queryExecution = QueryExecutionFactory.create(query, jenaDriver.getModel())) {
			final ResultSet resultSet = queryExecution.execSelect();

			while (resultSet.hasNext()) {
				final QuerySolution qs = resultSet.next();
				final JenaMatch match = JenaMatch.createMatch(tbQuery, qs);
				matches.add(match);
			}
		}

		return matches;
	}

}
