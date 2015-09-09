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

import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.STATION;
import hu.bme.mit.trainbenchmark.benchmark.rdf.analyzer.RDFModelAnalyzer;
import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.constants.EdgeDirection;
import hu.bme.mit.trainbenchmark.constants.TrainBenchmarkConstants;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Random;
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
	protected List<URI> stations;

	public SesameModelAnalyzer(SesameDriver driver) {
		super(driver);
	}

	@Override
	public void calculateMetrics() {
		connection = driver.getConnection();
		vf = driver.getValueFactory();
		stations = new ArrayList<URI>();
		try {
			TupleQuery query = connection.prepareTupleQuery(QueryLanguage.SPARQL, getTriples);
			TupleQueryResult results = query.evaluate();

			calculateEdges(results);

			query = connection.prepareTupleQuery(QueryLanguage.SPARQL, getDegreesQuery);
			results = query.evaluate();
			calculateDegrees(results);

			results = query.evaluate();
			calculateNumberOfDegrees(results);

			calculateShortestPaths();

		} catch (RepositoryException | MalformedQueryException | QueryEvaluationException e) {
			throw new RuntimeException(e);
		}

	}

	protected void calculateDegrees(TupleQueryResult results) throws QueryEvaluationException,
			RepositoryException, MalformedQueryException {
		double degree = 0;
		BindingSet set;

		while (results.hasNext()) {
			set = results.next();
			degree = Double.parseDouble(set.getValue("outdegree").stringValue());
			if (set.getValue("x").stringValue().contains(":")) {
				if (isType(vf.createURI(set.getValue("x").stringValue()), STATION)) {
					determineClustering(vf.createURI(set.getValue("x").stringValue()));
					addStations(vf.createURI(set.getValue("x").stringValue()));
				}

			}
			numberOfNodesWithOutgoingDegrees += degree > 0 ? 1 : 0;
			numberOfNodes++;

			changeMaximumDegree(EdgeDirection.OUTGOING, degree);
			degree += Double.parseDouble(set.getValue("indegree").stringValue());
			changeMaximumDegree(EdgeDirection.BOTH, degree);
		}
		calculateAverageDegree(EdgeDirection.BOTH);
		calculateAverageDegree(EdgeDirection.OUTGOING);
	}

	protected void determineClustering(final URI subject) throws RepositoryException,
			MalformedQueryException, QueryEvaluationException {

		Set<String> neighbors = new HashSet<>();
		neighbors = getNeighbors(subject);
		if (neighbors.size() == 0) {
			return;
		}

		TupleQueryResult result;
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
		addClusteringCoefficient(connected, neighbors.size(), STATION);

	}

	protected void addStations(final URI subject) throws RepositoryException {
		stations.add(subject);
	}

	protected void calculateShortestPaths() throws QueryEvaluationException, RepositoryException,
			MalformedQueryException {
		if (stations.size() == 0) {
			return;
		}
		Random random = new Random(TrainBenchmarkConstants.RANDOM_SEED);
		int i = 0;
		URI source;
		URI target;
		int length;
		while (i < shortestPathMetric.getPairs()) {
			source = stations.get(random.nextInt(stations.size()));
			target = stations.get(random.nextInt(stations.size()));
			if (source != target) {
				length = determinePath(source, target);
				if (length != 0) {
					shortestPathMetric.add(length);
					i++;
				}
			}
		}
	}

	protected int determinePath(final URI source, final URI target) throws QueryEvaluationException,
			RepositoryException, MalformedQueryException {
		return determinePath(source, target, shortestPathMetric.getMaxDepth());
	}

	protected int determinePath(final URI source, final URI target, final int maxDepth)
			throws QueryEvaluationException, RepositoryException, MalformedQueryException {
		Queue<Entry<String, Integer>> queue = new LinkedList<>();
		queue.add(new AbstractMap.SimpleEntry<String, Integer>(source.stringValue(), 0));
		Entry<String, Integer> first;
		Set<String> checked = new HashSet<>();

		int depth;
		String node;
		while (!queue.isEmpty()) {
			first = queue.poll();
			depth = first.getValue();
			node = first.getKey();
			if (depth >= maxDepth) {
				return 0;
			}
			if (node.equals(target.stringValue())) {
				return depth;
			}
			checked.add(node);
			for (String n : getNeighbors(vf.createURI(node))) {
				if (n.equals(target.stringValue())) {
					return depth + 1;
				}
				if (!checked.contains(n)) {
					queue.add(new AbstractMap.SimpleEntry<String, Integer>(n, depth + 1));
				}
			}

		}
		return 0;
	}

	protected Set<String> getNeighbors(final URI source) throws QueryEvaluationException,
			RepositoryException, MalformedQueryException {
		String queryString = buildNeighborQuery(source);
		TupleQuery query = connection.prepareTupleQuery(QueryLanguage.SPARQL, queryString);
		TupleQueryResult result = query.evaluate();

		Set<String> neighbors = new HashSet<>();
		while (result.hasNext()) {
			neighbors.add(result.next().getValue("o").stringValue());
		}
		return neighbors;
	}

	protected boolean isType(final URI subject, final String type) throws RepositoryException {
		RepositoryResult<Statement> types = connection.getStatements(subject, RDF.TYPE, null, false);
		while (types.hasNext()) {
			if (cutPrefix(types.next().getObject().stringValue()).equals(type)) {
				return true;
			}
		}
		return false;
	}

	protected void calculateEdges(TupleQueryResult results) throws QueryEvaluationException {
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

	protected void calculateNumberOfDegrees(final TupleQueryResult results)
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

	protected String cutPrefix(final String uri) {
		return uri.substring(uri.indexOf("#") + 1);
	}

}
