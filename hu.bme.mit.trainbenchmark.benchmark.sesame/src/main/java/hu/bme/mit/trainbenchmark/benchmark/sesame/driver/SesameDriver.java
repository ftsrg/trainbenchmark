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
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameMatch;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.rdf.RDFConstants;
import hu.bme.mit.trainbenchmark.rdf.RDFDatabaseDriver;

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
import org.openrdf.sail.memory.MemoryStore;

public class SesameDriver extends RDFDatabaseDriver<URI> {

	protected RepositoryConnection connection;
	protected Repository repository;
	protected ValueFactory vf;
	protected TupleQuery tupleQuery;

	protected Comparator<URI> elementComparator = new URIComparator();

	@Override
	public void beginTransaction() {
		vf = repository.getValueFactory();
	}

	@Override
	public void finishTransaction() throws IOException {
		try {
			connection.commit();
		} catch (final RepositoryException e) {
			throw new IOException(e);
		}
	}

	@Override
	public void read(final String modelPathWithoutExtension) throws IOException {
		repository = new SailRepository(new MemoryStore());
		final File modelFile = new File(modelPathWithoutExtension + getExtension());

		try {
			repository.initialize();
			connection = repository.getConnection();
			connection.add(modelFile, RDFConstants.BASE_PREFIX, RDFFormat.TURTLE);
		} catch (final OpenRDFException e) {
			throw new IOException(e);
		}
	}

	@Override
	public List<SesameMatch> runQuery(final Query query, final String queryDefinition) throws IOException {
		final List<SesameMatch> results = new ArrayList<>();
		TupleQueryResult queryResults;

		try {
			tupleQuery = connection.prepareTupleQuery(QueryLanguage.SPARQL, queryDefinition);
			queryResults = tupleQuery.evaluate();
			try {
				while (queryResults.hasNext()) {
					final BindingSet bs = queryResults.next();
					final SesameMatch match = SesameMatch.createMatch(query, bs);
					results.add(match);
				}
			} finally {
				queryResults.close();
			}
		} catch (final QueryEvaluationException | RepositoryException | MalformedQueryException e) {
			throw new IOException(e);
		}

		return results;
	}

	@Override
	public Comparator<URI> getElementComparator() {
		return elementComparator;
	}

	@Override
	public void destroy() throws IOException {
		try {
			if (connection != null) {
				connection.clear();
				connection.close();
			}
		} catch (final RepositoryException e) {
			throw new IOException(e);
		}
	}

	// read

	@Override
	public List<URI> collectVertices(final String type) throws IOException {
		final URI typeURI = vf.createURI(BASE_PREFIX + type);
		final List<URI> vertices = new ArrayList<>();

		try {
			final RepositoryResult<Statement> statements = connection.getStatements(null, RDF.TYPE, typeURI, true);
			while (statements.hasNext()) {
				final Statement s = statements.next();
				final URI uri = (URI) s.getSubject();
				vertices.add(uri);
			}
		} catch (final RepositoryException e) {
			throw new IOException(e);
		}

		return vertices;
	}

	// delete

	public void deleteOneOutgoingEdge(final Collection<URI> vertices, final String vertexType, final String edgeType) throws IOException {
		deleteEdges(vertices, edgeType, true, false);
	}

	public void deleteSingleOutgoingEdge(final Collection<URI> vertices, final String vertexType, final String edgeType) throws IOException {
		deleteEdges(vertices, edgeType, true, false);
	}

	protected void deleteEdges(final Collection<URI> vertices, final String edgeType, final boolean outgoing, final boolean all)
			throws IOException {
		final List<Statement> itemsToRemove = new ArrayList<>();

		final URI edge = vf.createURI(BASE_PREFIX + edgeType);

		try {
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
		} catch (final RepositoryException e) {
			throw new IOException(e);
		}
	}


	// utility

	@Override
	protected boolean ask(final String askQuery) throws IOException {
		try {
			final BooleanQuery q = connection.prepareBooleanQuery(QueryLanguage.SPARQL, askQuery);
			final boolean result = q.evaluate();
			return result;
		} catch (RepositoryException | MalformedQueryException | QueryEvaluationException e) {
			throw new IOException(e);
		}
	}

	public RepositoryConnection getConnection() {
		return connection;
	}

	public ValueFactory getValueFactory() {
		return vf;
	}

}
