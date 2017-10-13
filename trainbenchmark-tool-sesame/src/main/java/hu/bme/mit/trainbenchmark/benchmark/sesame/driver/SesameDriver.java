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
package hu.bme.mit.trainbenchmark.benchmark.sesame.driver;

import hu.bme.mit.trainbenchmark.benchmark.rdf.driver.RdfDriver;
import hu.bme.mit.trainbenchmark.benchmark.sesame.comparators.UriComparator;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.rdf.RdfConstants;
import org.openrdf.OpenRDFException;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.query.BindingSet;
import org.openrdf.query.BooleanQuery;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.RepositoryResult;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.rio.RDFFormat;
import org.openrdf.sail.inferencer.fc.ForwardChainingRDFSInferencer;
import org.openrdf.sail.memory.MemoryStore;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import static hu.bme.mit.trainbenchmark.rdf.RdfConstants.BASE_PREFIX;

public class SesameDriver extends RdfDriver {

	protected RepositoryConnection connection;
	protected Repository repository;
	protected ValueFactory vf;

	protected final Comparator<URI> elementComparator = new UriComparator();

	public SesameDriver(final boolean inferencing) {
		super(inferencing);
	}

	@Override
	public void beginTransaction() {
	}

	@Override
	public void finishTransaction() throws RepositoryException {
		connection.commit();
	}

	@Override
	public void read(final String modelPath) throws IOException, OpenRDFException {
		if (inferencing) {
			repository = new SailRepository(new ForwardChainingRDFSInferencer(new MemoryStore()));
		} else {
			repository = new SailRepository(new MemoryStore());
		}

		final File modelFile = new File(modelPath);

		repository.initialize();
		vf = repository.getValueFactory();
		connection = repository.getConnection();
		connection.add(modelFile, RdfConstants.BASE_PREFIX, RDFFormat.TURTLE);
	}

	public Collection<? extends SesameMatch> runQuery(final RailwayQuery query, final String queryDefinition)
			throws RepositoryException, MalformedQueryException, QueryEvaluationException {
		final Collection<SesameMatch> results = new ArrayList<>();

		final TupleQuery tupleQuery = connection.prepareTupleQuery(QueryLanguage.SPARQL, queryDefinition);
		final TupleQueryResult queryResults = tupleQuery.evaluate();
		try {
			while (queryResults.hasNext()) {
				final BindingSet bs = queryResults.next();
				final SesameMatch match = SesameMatch.createMatch(query, bs);
				results.add(match);
			}
		} finally {
			queryResults.close();
		}

		return results;
	}

	@Override
	public void destroy() throws Exception {
		if (connection != null) {
			connection.clear();
			connection.close();
		}
	}

	// delete

	public void deleteOutgoingEdges(final Collection<URI> vertices, final String edgeType)
			throws RepositoryException {
		deleteEdges(vertices, edgeType, true, true);
	}

	protected void deleteEdges(final Collection<URI> vertices, final String edgeType, final boolean outgoing,
			final boolean all) throws RepositoryException {
		final List<Statement> itemsToRemove = new ArrayList<>();

		final URI edge = vf.createURI(BASE_PREFIX + edgeType);

		for (final URI vertex : vertices) {
			final RepositoryResult<Statement> statementsToRemove;
			if (outgoing) {
				statementsToRemove = connection.getStatements(vertex, edge, null, true);
			} else {
				statementsToRemove = connection.getStatements(null, edge, vertex, true);
			}

			while (statementsToRemove.hasNext()) {
				final Statement s = statementsToRemove.next();
				itemsToRemove.add(s);

				// break if we only want to delete one edge
				if (!all) {
					break;
				}
			}

			for (final Statement s : itemsToRemove) {
				connection.remove(s);
			}
		}
	}

	// utility

	@Override
	protected boolean ask(final String askQuery)
			throws RepositoryException, MalformedQueryException, QueryEvaluationException {
		final BooleanQuery q = connection.prepareBooleanQuery(QueryLanguage.SPARQL, askQuery);
		final boolean result = q.evaluate();
		return result;
	}

	public RepositoryConnection getConnection() {
		return connection;
	}

	public ValueFactory getValueFactory() {
		return vf;
	}

	public boolean isInferencing() {
		return inferencing;
	}

}
