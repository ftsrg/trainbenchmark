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

import hu.bme.mit.trainbenchmark.benchmark.analyzer.ModelAnalyzer;
import hu.bme.mit.trainbenchmark.benchmark.rdf.RDFBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.constants.EdgeDirection;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openrdf.query.BindingSet;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;

public class SesameModelAnalyzer extends ModelAnalyzer<SesameDriver> {

	private String getDegreesQuery;

	private String getTriples;

	private RDFBenchmarkConfig benchmarkConfig;

	public SesameModelAnalyzer(SesameDriver driver) {
		super(driver);
	}

	private void initQueries() {
		String degreesQueryPath = benchmarkConfig.getWorkspacePath()
				+ "/hu.bme.mit.trainbenchmark.benchmark.rdf/src/main/resources/queries/util/GetDegrees.sparql";
		String triplesQueryPath = benchmarkConfig.getWorkspacePath()
				+ "/hu.bme.mit.trainbenchmark.benchmark.rdf/src/main/resources/queries/util/GetTriples.sparql";
		try {
			getDegreesQuery = FileUtils.readFileToString(new File(degreesQueryPath));
			getTriples = FileUtils.readFileToString(new File(triplesQueryPath));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void calculateMetrics() {
		initQueries();
		numberOfNodesWithOutgoingDegrees = 0;
		numberOfNodes = 0;
		numberOfEdges = 0;

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

	public RDFBenchmarkConfig getBenchmarkConfig() {
		return benchmarkConfig;
	}

	public void setBenchmarkConfig(RDFBenchmarkConfig benchmarkConfig) {
		this.benchmarkConfig = benchmarkConfig;
	}

}
