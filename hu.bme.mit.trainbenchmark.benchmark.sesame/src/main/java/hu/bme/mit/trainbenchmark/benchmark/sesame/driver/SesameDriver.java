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

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.CURRENTPOSITION;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.DEFINED_BY;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ENTRY;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.LENGTH;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR_EDGE;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_CURRENTPOSITION;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_LENGTH;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_POSITION;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_ROUTE;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_ROUTE2;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEMAPHORE;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SENSOR;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SW;
import static hu.bme.mit.trainbenchmark.rdf.RDFConstants.BASE_PREFIX;
import static hu.bme.mit.trainbenchmark.rdf.RDFConstants.ID_PREFIX;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.PosLengthRepairOperation;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.rdf.RDFConstants;
import hu.bme.mit.trainbenchmark.rdf.RDFDatabaseDriver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openrdf.OpenRDFException;
import org.openrdf.model.Literal;
import org.openrdf.model.Resource;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.Value;
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

public class SesameDriver extends RDFDatabaseDriver<BindingSet, URI> {

	protected Long newVertexId = null;

	protected String query;
	protected RepositoryConnection con;
	protected Repository repository;
	protected ValueFactory f;
	protected TupleQuery tupleQuery;

	public SesameDriver(final String queryPath) throws IOException {
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

	public List<BindingSet> runQuery() throws IOException {
		final List<BindingSet> results = new ArrayList<>();
		TupleQueryResult queryResults;

		try {
			tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL, query);
			queryResults = tupleQuery.evaluate();
			try {
				while (queryResults.hasNext()) {
					final BindingSet bs = queryResults.next();
					results.add(bs);
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
	public Comparator<BindingSet> getMatchComparator() {
		// return new URIComparator();
		return null;
	}

	@Override
	public void destroy() throws IOException {
		try {
			con.clear();
			con.close();
		} catch (final RepositoryException e) {
			throw new IOException(e);
		}
	}

	// read

	@Override
	public List<URI> collectVertices(final String type) throws IOException {
		final URI typeURI = f.createURI(BASE_PREFIX + type);
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

		final URI edge = f.createURI(BASE_PREFIX + edgeType);

		try {
			for (final URI vertex : vertices) {
				RepositoryResult<Statement> statementsToRemove;
				if (outgoing) {
					statementsToRemove = con.getStatements(vertex, edge, null, true);
				} else {
					statementsToRemove = con.getStatements(null, edge, vertex, true);
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
			}
		} catch (final RepositoryException e) {
			throw new IOException(e);
		}
	}

	public void deleteVertex(final URI vertex, final String vertexType) throws IOException {
		try {
			final RepositoryResult<Statement> statementsToRemove = con.getStatements(vertex, RDF.TYPE, null, false);
			while (statementsToRemove.hasNext()) {
				final Statement s = statementsToRemove.next();
				con.remove(s);
			}
		} catch (final RepositoryException e) {
			throw new IOException();
		}
	}

	// utility

	@Override
	protected boolean ask(final String askQuery) throws IOException {
		try {
			final BooleanQuery q = con.prepareBooleanQuery(QueryLanguage.SPARQL, askQuery);
			final boolean result = q.evaluate();
			return result;
		} catch (RepositoryException | MalformedQueryException | QueryEvaluationException e) {
			throw new IOException(e);
		}
	}

	// repair

	@Override
	public void posLengthRepair(final Collection<BindingSet> matches) throws IOException {
		final PosLengthRepairOperation operation = new PosLengthRepairOperation();
		final URI lengthProperty = f.createURI(BASE_PREFIX + LENGTH);

		try {
			for (final BindingSet match : matches) {
				final Resource segment = (Resource) match.getValue(VAR_SEGMENT);
				final Value length = match.getValue(VAR_LENGTH);

				final RepositoryResult<Statement> statementsToRemove = con.getStatements(segment, lengthProperty, length, true);
				while (statementsToRemove.hasNext()) {
					con.remove(statementsToRemove.next());
				}

				final Integer lengthInteger = new Integer(length.stringValue());
				final Literal newLength = f.createLiteral(operation.op(lengthInteger));
				con.add(segment, lengthProperty, newLength);
			}
		} catch (final RepositoryException e) {
			throw new IOException(e);
		}
	}

	@Override
	public void routeSensorRepair(final Collection<BindingSet> matches) throws IOException {
		final URI definedBy = f.createURI(BASE_PREFIX + DEFINED_BY);

		try {
			for (final BindingSet match : matches) {
				final Resource route = (Resource) match.getValue(VAR_ROUTE);
				final Resource sensor = (Resource) match.getValue(VAR_SENSOR);

				con.add(route, definedBy, sensor);
			}
		} catch (final RepositoryException e) {
			throw new IOException(e);
		}
	}

	@Override
	public void semaphoreNeighborRepair(final Collection<BindingSet> matches) throws IOException {
		final URI entry = f.createURI(BASE_PREFIX + ENTRY);

		try {
			for (final BindingSet match : matches) {
				final Resource route2 = (Resource) match.getValue(VAR_ROUTE2);
				final Resource semaphore = (Resource) match.getValue(VAR_SEMAPHORE);

				System.out.println(route2);
				System.out.println(entry);
				System.out.println(semaphore);

				con.add(route2, entry, semaphore);
			}
		} catch (final RepositoryException e) {
			throw new IOException(e);
		}
	}

	@Override
	public void switchSensorRepair(final Collection<BindingSet> matches) throws IOException {
		final URI sensorEdge = f.createURI(BASE_PREFIX + SENSOR_EDGE);
		final URI sensorType = f.createURI(BASE_PREFIX + SENSOR);

		if (newVertexId == null) {
			newVertexId = determineNewVertexId();
		}

		try {
			for (final BindingSet match : matches) {
				final Resource sw = (Resource) match.getValue(VAR_SW);

				final URI sensorURI = f.createURI(BASE_PREFIX + ID_PREFIX + newVertexId);
				newVertexId++;

				// set vertex type
				con.add(sensorURI, RDF.TYPE, sensorType);
				// insert edge
				con.add(sw, sensorEdge, sensorURI);
			}
		} catch (final RepositoryException e) {
			throw new IOException(e);
		}
	}

	@Override
	public void switchSetRepair(final Collection<BindingSet> matches) throws IOException {
		final URI currentPositionProperty = f.createURI(BASE_PREFIX + CURRENTPOSITION);

		try {
			for (final BindingSet match : matches) {
				final Resource sw = (Resource) match.getValue(VAR_SW);
				final Resource position = (Resource) match.getValue(VAR_POSITION);
				final Resource currentPosition = (Resource) match.getValue(VAR_CURRENTPOSITION);

				final RepositoryResult<Statement> statementsToRemove = con.getStatements(sw, currentPositionProperty, currentPosition,
						false);
				while (statementsToRemove.hasNext()) {
					con.remove(statementsToRemove.next());
				}

				con.add(sw, currentPositionProperty, position);
			}
		} catch (final RepositoryException e) {
			throw new IOException(e);
		}
	}

	// user

	@Override
	public void posLengthUser(final Collection<URI> segments) throws IOException {
		final URI typeURI = f.createURI(BASE_PREFIX + ModelConstants.LENGTH);
		final Literal zeroLiteral = f.createLiteral(0);

		try {
			for (final URI segment : segments) {
				final RepositoryResult<Statement> statementsToRemove = con.getStatements(segment, typeURI, null, true);
				while (statementsToRemove.hasNext()) {
					con.remove(statementsToRemove.next());
				}

				con.add(segment, typeURI, zeroLiteral);
			}
		} catch (final RepositoryException e) {
			throw new IOException(e);
		}
	}

	@Override
	public void routeSensorUser(final Collection<URI> routes) throws IOException {
		deleteOneOutgoingEdge(routes, ModelConstants.ROUTE, ModelConstants.DEFINED_BY);
	}

	@Override
	public void semaphoreNeighborUser(final Collection<URI> routes) throws IOException {
		deleteSingleOutgoingEdge(routes, ModelConstants.ROUTE, ModelConstants.ENTRY);
	}

	@Override
	public void switchSensorUser(final Collection<URI> switches) throws IOException {
		deleteSingleOutgoingEdge(switches, ModelConstants.SWITCH, ModelConstants.SENSOR_EDGE);
	}

	@Override
	public void switchSetUser(final Collection<URI> switches) throws IOException {

	}

}
