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

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.AttributeOperation;
import hu.bme.mit.trainbenchmark.benchmark.driver.DatabaseDriver;
import hu.bme.mit.trainbenchmark.rdf.RDFConstants;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openrdf.OpenRDFException;
import org.openrdf.model.Literal;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.vocabulary.RDF;
import org.openrdf.query.BindingSet;
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
import org.openrdf.sail.memory.model.IntegerMemLiteral;

public class SesameDriver extends DatabaseDriver<URI> {

	protected String basePrefix;
	protected String query;
	protected RepositoryConnection con;
	protected Repository repository;
	protected ValueFactory f;

	public SesameDriver(final String basePrefix, final String queryPath) throws IOException {
		this.basePrefix = basePrefix;
		this.query = FileUtils.readFileToString(new File(queryPath));
	}

	@Override
	public void beginTransaction() {
		f = repository.getValueFactory();
	}

	@Override
	public void finishTransaction() throws IOException {
		try {
			con.commit();
		} catch (final RepositoryException e) {
			throw new IOException(e);
		}
	}

	@Override
	public List<URI> collectVertices(final String type) throws IOException {
		final URI typeURI = f.createURI(basePrefix + type);
		final List<URI> vertices = new ArrayList<>();

		try {
			final RepositoryResult<Statement> statements = con.getStatements(null, RDF.TYPE, typeURI, true);
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

	protected long newVertexId = 1000000000;
	private TupleQuery tupleQuery;

	@Override
	public void insertVertexWithEdge(final Object sourceVertex, final String sourceVertexType, final String targetVertexType,
			final String edgeType) throws IOException {
		final URI sourceVertexURI = (URI) sourceVertex;
		final URI vertexTypeURI = f.createURI(basePrefix + targetVertexType);
		final URI edgeTypeURI = f.createURI(basePrefix + edgeType);

		// TODO think about alternative solutions
		final URI targetVertexURI = f.createURI(basePrefix + targetVertexType + newVertexId);
		newVertexId++;

		try {
			// insert edge
			final Statement edgeStatement = f.createStatement(sourceVertexURI, edgeTypeURI, targetVertexURI);
			con.add(edgeStatement);

			// set vertex type
			final Statement typeStatement = f.createStatement(targetVertexURI, RDF.TYPE, vertexTypeURI);
			con.add(typeStatement);
		} catch (final RepositoryException e) {
			throw new IOException(e);
		}
	}

	@Override
	public void deleteAllOutgoingEdges(final Object vertex, final String edgeType) throws IOException {
		deleteEdges(vertex, edgeType, true, true);
	}

	@Override
	public void deleteAllIncomingEdges(final Object vertex, final String edgeType, final String sourceVertexType) throws IOException {
		deleteEdges(vertex, edgeType, false, true);
	}

	@Override
	public void deleteOneOutgoingEdge(final Object vertex, final String edgeType) throws IOException {
		deleteEdges(vertex, edgeType, true, false);
	}

	@Override
	public void deleteOutgoingEdge(final Object vertex, final String vertexType, final String edgeType) throws IOException {
		deleteEdges(vertex, edgeType, true, false);
	}

	public void deleteEdges(final Object vertex, final String edgeType, final boolean outgoing, final boolean all) throws IOException {
		final List<Statement> itemsToRemove = new ArrayList<>();

		final URI edge = f.createURI(basePrefix + edgeType);
		final URI nodeURI = (URI) vertex;
		RepositoryResult<Statement> statementsToRemove;
		try {
			if (outgoing) {
				statementsToRemove = con.getStatements(nodeURI, edge, null, true);
			} else {
				statementsToRemove = con.getStatements(null, edge, nodeURI, true);
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
				con.remove(s);
			}
		} catch (final RepositoryException e) {
			throw new IOException(e);
		}
	}

	@Override
	public void updateProperty(final Object vertex, final String vertexType, final String propertyName,
			final AttributeOperation attributeOperation) throws IOException {
		try {
			final URI vertexURI = (URI) vertex;
			final URI typeURI = f.createURI(basePrefix + propertyName);
			final RepositoryResult<Statement> statementsToRemove = con.getStatements(vertexURI, typeURI, null, true);

			if (!statementsToRemove.hasNext()) {
				throw new IOException("Property " + propertyName + " not found.");
			}

			final Statement statement = statementsToRemove.next();
			con.remove(statement);
			while (statementsToRemove.hasNext()) {
				con.remove(statementsToRemove.next());
			}

			// get the object of the first removed statement
			final IntegerMemLiteral integerMemLiteral = (IntegerMemLiteral) statement.getObject();
			final Integer currentValue = new Integer(integerMemLiteral.stringValue());
			final Literal literal = f.createLiteral(attributeOperation.op(currentValue));

			con.add(vertexURI, typeURI, literal);
		} catch (final RepositoryException e) {
			throw new IOException(e);
		}
	}

	@Override
	public void read(final String modelPath) throws IOException {
		repository = new SailRepository(new MemoryStore());
		final File modelFile = new File(modelPath);

		try {
			repository.initialize();
			con = repository.getConnection();
			con.add(modelFile, RDFConstants.BASE_PREFIX, RDFFormat.TURTLE);
		} catch (final OpenRDFException e) {
			throw new IOException(e);
		}
	}

	public List<Long> extractIds(final Collection<URI> elements) {
		final ArrayList<Long> ids = new ArrayList<Long>();
		for (final URI uri : elements) {
			final String idString = uri.getLocalName();
			final Long id = new Long(idString);
			ids.add(id);
		}
		return ids;
	}

	@Override
	public List<URI> runQuery() throws IOException {
		final List<URI> results = new ArrayList<>();
		TupleQueryResult queryResults;

		try {
			tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL, query);
			queryResults = tupleQuery.evaluate();
			try {
				final String bindingName = queryResults.getBindingNames().get(0);

				while (queryResults.hasNext()) {
					final BindingSet bs = queryResults.next();
					final Value resultValue = bs.getValue(bindingName);
					if (resultValue instanceof URI) {
						results.add((URI) resultValue);
					}
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
	public void destroy() throws IOException {
		try {
			con.close();
		} catch (final RepositoryException e) {
			throw new IOException(e);
		}
	}

	@Override
	public Comparator<URI> getComparator() {
		return new URIComparator();
	}

}
