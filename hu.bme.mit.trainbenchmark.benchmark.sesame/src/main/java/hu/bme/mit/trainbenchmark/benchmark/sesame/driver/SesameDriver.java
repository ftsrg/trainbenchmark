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

import static hu.bme.mit.trainbenchmark.rdf.RDFConstants.BASE_PREFIX;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import org.openrdf.OpenRDFException;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.vocabulary.RDF;
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
import org.openrdf.rio.RDFParseException;
import org.openrdf.sail.inferencer.fc.ForwardChainingRDFSInferencer;
import org.openrdf.sail.memory.MemoryStore;

import hu.bme.mit.trainbenchmark.benchmark.rdf.RDFDriver;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameMatch;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.rdf.RDFConstants;

public class SesameDriver extends RDFDriver<URI> {

	protected RepositoryConnection connection;
	protected Repository repository;
	protected ValueFactory vf;

	protected final Comparator<URI> elementComparator = new URIComparator();

	public SesameDriver(boolean inferencing) {
		super(inferencing);
	}

	@Override
	public void beginTransaction() {
		vf = repository.getValueFactory();
	}

	@Override
	public void finishTransaction() throws RepositoryException {
		connection.commit();
	}

	@Override
	public void read(final String modelPathWithoutExtension)
			throws RepositoryException, RDFParseException, IOException, OpenRDFException {
		if (inferencing) {
			repository = new SailRepository(new ForwardChainingRDFSInferencer(new MemoryStore()));
		} else {
			repository = new SailRepository(new MemoryStore());
		}
		load(modelPathWithoutExtension);
	}

	protected void load(final String modelPathWithoutExtension)
			throws RepositoryException, RDFParseException, IOException {
		final File modelFile = new File(modelPathWithoutExtension + getPostfix());

		repository.initialize();
		connection = repository.getConnection();
		connection.add(modelFile, RDFConstants.BASE_PREFIX, RDFFormat.TURTLE);
	}

	public Collection<SesameMatch> runQuery(final Query query, final String queryDefinition)
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
	public Comparator<URI> getElementComparator() {
		return elementComparator;
	}

	@Override
	public void destroy() throws Exception {
		if (connection != null) {
			connection.clear();
			connection.close();
		}
	}

	// read

	@Override
	public Collection<URI> collectVertices(final String type) throws RepositoryException {
		final URI typeURI = vf.createURI(BASE_PREFIX + type);
		final Collection<URI> vertices = new ArrayList<>();

		final RepositoryResult<Statement> statements = connection.getStatements(null, RDF.TYPE, typeURI, true);
		while (statements.hasNext()) {
			final Statement s = statements.next();
			final URI uri = (URI) s.getSubject();
			vertices.add(uri);
		}

		return vertices;
	}

	// delete

	public void deleteOneOutgoingEdge(final Collection<URI> vertices, final String vertexType, final String edgeType)
			throws RepositoryException {
		deleteEdges(vertices, edgeType, true, false);
	}

	public void deleteSingleOutgoingEdge(final Collection<URI> vertices, final String vertexType, final String edgeType)
			throws RepositoryException {
		deleteEdges(vertices, edgeType, true, false);
	}
	
	public void deleteOutgoingEdges(final Collection<URI> vertices, final String vertexType, final String edgeType)
			throws RepositoryException {
		deleteEdges(vertices, edgeType, true, true);
	}

	protected void deleteEdges(final Collection<URI> vertices, final String edgeType, final boolean outgoing,
			final boolean all) throws RepositoryException {
		final List<Statement> itemsToRemove = new ArrayList<>();

		final URI edge = vf.createURI(BASE_PREFIX + edgeType);

		for (final URI vertex : vertices) {
			RepositoryResult<Statement> statementsToRemove;
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
