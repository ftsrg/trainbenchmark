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
package hu.bme.mit.trainbenchmark.benchmark.jena.driver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Selector;
import org.apache.jena.rdf.model.SimpleSelector;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;

import hu.bme.mit.trainbenchmark.benchmark.rdf.RdfDriver;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.rdf.RdfConstants;

public class JenaDriver extends RdfDriver<Resource> {

	protected Model model;

	protected Comparator<Statement> statementComparator = new StatementComparator();

	public JenaDriver(final boolean inferencing) {
		super(inferencing);
	}

	public static JenaDriver create(final boolean inferencing) {
		return new JenaDriver(inferencing);
	}

	@Override
	public void read(final String modelPath) throws IOException {
		final Model defaultModel = ModelFactory.createDefaultModel();
		defaultModel.read(modelPath);

		// run inferencing if required
		if (inferencing) {
			final Reasoner reasoner = ReasonerRegistry.getRDFSSimpleReasoner();
			model = ModelFactory.createInfModel(reasoner, defaultModel);
		} else {
			model = defaultModel;
		}
	}

	public Collection<QuerySolution> runQuery(final RailwayQuery query, final String queryDefinition)
			throws IOException {
		final Collection<QuerySolution> results = new ArrayList<>();
		try (QueryExecution queryExecution = QueryExecutionFactory.create(queryDefinition, model)) {
			final ResultSet resultSet = queryExecution.execSelect();

			while (resultSet.hasNext()) {
				final QuerySolution qs = resultSet.next();
				results.add(qs);
			}
		}

		return results;
	}

	@Override
	public void destroy() throws IOException {
		if (model != null) {
			model.close();
		}
	}

//	@Override
//	public Collection<Resource> collectVertices(final String type) throws IOException {
//		final ResIterator vertexStatements = model.listSubjectsWithProperty(RDF.type,
//				model.getResource(BASE_PREFIX + type));
//		final Collection<Resource> vertices = vertexStatements.toList();
//		return vertices;
//	}

	public void deleteAllOutgoingEdges(final Collection<Resource> vertices, final String vertexType,
			final String edgeType) throws IOException {
		deleteEdges(vertices, edgeType, true, true);
	}

	public void deleteSingleOutgoingEdge(final Collection<Resource> vertices, final String vertexType,
			final String edgeType) throws IOException {
		deleteEdges(vertices, edgeType, true, true);
	}

	protected void deleteEdges(final Collection<Resource> vertices, final String edgeType, final boolean outgoing,
			final boolean all) {
		final Property property = model.getProperty(RdfConstants.BASE_PREFIX + edgeType);

		for (final Resource vertex : vertices) {
			final Selector selector = outgoing ? new SimpleSelector(vertex, property, (RDFNode) null) //
					: new SimpleSelector(null, property, vertex);

			final StmtIterator edges = model.listStatements(selector);

			final List<Statement> statementsToRemove = new ArrayList<>();
			while (edges.hasNext()) {
				final Statement edge = edges.next();
				statementsToRemove.add(edge);
			}

			Collections.sort(statementsToRemove, statementComparator);
			for (final Statement statement : statementsToRemove) {
				model.remove(statement);

				if (!all) {
					return;
				}
			}
		}
	}

	@Override
	protected boolean ask(final String askQuery) {
		try (QueryExecution queryExecution = QueryExecutionFactory.create(askQuery, model)) {
			final boolean result = queryExecution.execAsk();
			return result;
		}
	}

	public Model getModel() {
		return model;
	}

}
