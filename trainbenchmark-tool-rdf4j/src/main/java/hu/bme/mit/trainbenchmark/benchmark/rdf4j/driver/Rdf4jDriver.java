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
package hu.bme.mit.trainbenchmark.benchmark.rdf4j.driver;

import hu.bme.mit.trainbenchmark.benchmark.rdf.driver.RdfDriver;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.comparators.IriComparator;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.matches.Rdf4jMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.rdf.RdfConstants;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.query.*;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.RepositoryException;
import org.eclipse.rdf4j.repository.RepositoryResult;
import org.eclipse.rdf4j.repository.sail.SailRepository;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.sail.inferencer.fc.ForwardChainingRDFSInferencer;
import org.eclipse.rdf4j.sail.memory.MemoryStore;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import static hu.bme.mit.trainbenchmark.rdf.RdfConstants.BASE_PREFIX;

public class Rdf4jDriver extends RdfDriver<IRI> {

    protected RepositoryConnection connection;
    protected Repository repository;
    protected ValueFactory vf;

    protected final Comparator<IRI> elementComparator = new IriComparator();

    protected Rdf4jDriver(final boolean inferencing) {
        super(inferencing);
    }

    public static Rdf4jDriver create(final boolean inferencing) {
        return new Rdf4jDriver(inferencing);
    }

    @Override
    public void beginTransaction() {
    }

    @Override
    public void finishTransaction() {
        connection.commit();
    }

    @Override
    public void read(final String modelPath) throws IOException {
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

    public Collection<? extends Rdf4jMatch> runQuery(final RailwayQuery query, final String queryDefinition) {
        final Collection<Rdf4jMatch> results = new ArrayList<>();

        final TupleQuery tupleQuery = connection.prepareTupleQuery(QueryLanguage.SPARQL, queryDefinition);
        final TupleQueryResult queryResults = tupleQuery.evaluate();
        try {
            while (queryResults.hasNext()) {
                final BindingSet bs = queryResults.next();
                final Rdf4jMatch match = Rdf4jMatch.createMatch(query, bs);
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

    public void deleteSingleOutgoingEdge(final Collection<IRI> vertices, final String edgeType) {
        deleteEdges(vertices, edgeType, true, false);
    }

    public void deleteOutgoingEdges(final Collection<IRI> vertices, final String edgeType) {
        deleteEdges(vertices, edgeType, true, true);
    }

    protected void deleteEdges(final Collection<IRI> vertices, final String edgeType, final boolean outgoing,
                               final boolean all) throws RepositoryException {
        final List<Statement> itemsToRemove = new ArrayList<>();

        final IRI edge = vf.createIRI(BASE_PREFIX + edgeType);

        for (final IRI vertex : vertices) {
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
