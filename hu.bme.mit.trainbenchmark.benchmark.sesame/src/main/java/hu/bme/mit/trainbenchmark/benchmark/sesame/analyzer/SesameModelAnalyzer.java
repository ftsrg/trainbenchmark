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

import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleConstants.STATION;
import hu.bme.mit.trainbenchmark.benchmark.rdf.analyzer.RDFModelAnalyzer;
import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.constants.EdgeDirection;

import java.util.HashSet;
import java.util.Set;

import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.vocabulary.RDF;
import org.openrdf.query.BindingSet;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.RepositoryResult;

public class SesameModelAnalyzer extends RDFModelAnalyzer<SesameDriver> {

	protected ValueFactory vf;
	protected RepositoryConnection connection;

	public SesameModelAnalyzer(SesameDriver driver) {
		super(driver);
	}

	@Override
	public void calculateMetrics() {
		connection = driver.getConnection();
		vf = driver.getValueFactory();
		try {
			TupleQuery query = connection.prepareTupleQuery(QueryLanguage.SPARQL, getTriples);
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

	private void calculateDegrees(TupleQueryResult results) throws QueryEvaluationException,
			RepositoryException, MalformedQueryException {
		double degree = 0;
		BindingSet set;

		while (results.hasNext()) {
			set = results.next();
			degree = Double.parseDouble(set.getValue("outdegree").stringValue());
			determineClustering(vf.createURI(set.getValue("x").stringValue()));
			numberOfNodesWithOutgoingDegrees += degree > 0 ? 1 : 0;
			numberOfNodes++;

			changeMaximumDegree(EdgeDirection.OUTGOING, degree);
			degree += Double.parseDouble(set.getValue("indegree").stringValue());
			changeMaximumDegree(EdgeDirection.BOTH, degree);

		}
		calculateAverageDegree(EdgeDirection.BOTH);
		calculateAverageDegree(EdgeDirection.OUTGOING);
	}

	private void determineClustering(final URI subject) throws RepositoryException,
			MalformedQueryException, QueryEvaluationException {
		String queryString = buildNeighborQuery(subject);
		TupleQuery query = connection.prepareTupleQuery(QueryLanguage.SPARQL, queryString);
		TupleQueryResult result = query.evaluate();

		Set<String> neighbors = new HashSet<>();
		while (result.hasNext()) {
			neighbors.add(result.next().getValue("o").stringValue());
		}

		if (neighbors.size() == 0) {
			return;
		}

		int connected = 0;
		for (String n : neighbors) {
			result = connection.prepareTupleQuery(QueryLanguage.SPARQL,
					buildNeighborQuery(vf.createURI(n))).evaluate();
			while (result.hasNext()) {
				if (neighbors.contains(result.next().getValue("o").stringValue())) {
					connected++;
				}
			}
		}
		URI clazz;
		RepositoryResult<Statement> types = connection.getStatements(subject, RDF.TYPE, null, false);
		while (types.hasNext()) {
			if (cutPrefix(types.next().getObject().stringValue()).equals(STATION)) {
				addClusteringCoefficient(connected, neighbors.size(), STATION);
				return;
			}
		}
		addClusteringCoefficient(connected, neighbors.size());

	}

	private void calculateEdges(TupleQueryResult results) throws QueryEvaluationException {
		BindingSet set;
		while (results.hasNext()) {
			set = results.next();
			numberOfEdges = Double.parseDouble(set.getValue("triples").stringValue());
		}
	}

	protected String buildNeighborQuery(final URI subjectURI) {
		String queryString = String
				.format("PREFIX  base: <http://www.semanticweb.org/ontologies/2015/trainbenchmark#> "
						+ "PREFIX  rdf:  <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
						+ "SELECT  ?o "
						+ "WHERE "
						+ "{ { base:%s ?p ?o FILTER ( ( ! isLiteral(?o) ) && ( ?p != rdf:type ) )}}",
						cutPrefix(subjectURI.stringValue()));
		return queryString;
	}

	private void calculateNumberOfDegrees(final TupleQueryResult results) throws QueryEvaluationException {
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

	protected String cutPrefix(final String uri) {
		return uri.substring(uri.indexOf("#") + 1);
	}

}
