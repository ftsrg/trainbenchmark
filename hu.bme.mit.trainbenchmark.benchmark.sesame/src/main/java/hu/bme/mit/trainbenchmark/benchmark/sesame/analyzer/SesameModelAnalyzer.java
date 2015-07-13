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

package hu.bme.mit.trainbenchmark.benchmark.sesame.analyzer;

import hu.bme.mit.trainbenchmark.benchmark.rdf.analyzer.RDFModelAnalyzer;
import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.constants.EdgeDirection;

import org.openrdf.query.BindingSet;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;

public class SesameModelAnalyzer extends RDFModelAnalyzer<SesameDriver> {

	public SesameModelAnalyzer(SesameDriver driver) {
		super(driver);
	}

	@Override
	public void calculateMetrics() {
		RepositoryConnection connection = driver.getConnection();
		try {
			TupleQuery query = connection.prepareTupleQuery(QueryLanguage.SPARQL,
					getTriples);
			TupleQueryResult results = query.evaluate();

			calculateEdges(results);

			query = connection.prepareTupleQuery(QueryLanguage.SPARQL, getDegreesQuery);
			results = query.evaluate();
			calculateDegrees(results);

			results = query.evaluate();
			calculateNumberOfDegrees(results);

		} catch (RepositoryException | MalformedQueryException | QueryEvaluationException e) {
			throw new RuntimeException(e);
		}

	}

	private void calculateDegrees(TupleQueryResult results) throws QueryEvaluationException {
		double degree = 0;
		BindingSet set;

		while (results.hasNext()) {
			set = results.next();
			degree = Double.parseDouble(set.getValue("outdegree").stringValue());
			numberOfNodesWithOutgoingDegrees += degree > 0 ? 1 : 0;
			numberOfNodes++;

			changeMaximumDegree(EdgeDirection.OUTGOING, degree);
			degree += Double.parseDouble(set.getValue("indegree").stringValue());
			changeMaximumDegree(EdgeDirection.BOTH, degree);

		}
		calculateAverageDegree(EdgeDirection.BOTH);
		calculateAverageDegree(EdgeDirection.OUTGOING);
	}

	private void calculateEdges(TupleQueryResult results) throws QueryEvaluationException {
		BindingSet set;
		while (results.hasNext()) {
			set = results.next();
			numberOfEdges = Double.parseDouble(set.getValue("triples").stringValue());
		}
	}

	private void calculateNumberOfDegrees(final TupleQueryResult results)
			throws QueryEvaluationException {
		BindingSet set;
		double degree = 0;
		int roundedDegree = roundAverageDegree(EdgeDirection.BOTH);
		int roundedOutgoingDegree = roundAverageDegree(EdgeDirection.OUTGOING);

		while (results.hasNext()) {
			set = results.next();
			degree = Double.parseDouble(set.getValue("outdegree").stringValue());
			changeNumberOfDegrees(EdgeDirection.OUTGOING, degree, roundedOutgoingDegree);
			degree += Double.parseDouble(set.getValue("indegree").stringValue());
			changeNumberOfDegrees(EdgeDirection.BOTH, degree, roundedDegree);
		}
	}

}
