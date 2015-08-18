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

import static hu.bme.mit.trainbenchmark.rdf.RDFConstants.BASE_PREFIX;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Selector;
import com.hp.hpl.jena.rdf.model.SimpleSelector;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.vocabulary.RDF;

import hu.bme.mit.trainbenchmark.benchmark.rdf.RDFBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.rdf.RDFDriver;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.rdf.RDFConstants;

public class JenaDriver extends RDFDriver<Resource> {

	protected Comparator<Resource> elementComparator = new ResourceComparator();
	protected Model model;
	protected Comparator<Statement> statementComparator = new StatemetComparator();

	public JenaDriver(final RDFBenchmarkConfig rdfbc) {
		super(rdfbc);
	}

	@Override
	public void read(final String modelPathWithoutExtension) throws IOException {
		model = ModelFactory.createDefaultModel();
		model.read(modelPathWithoutExtension + getPostfix());
	}

	@Override
	public List<QuerySolution> runQuery(final Query query, final String queryDefinition) throws IOException {
		final List<QuerySolution> results = new ArrayList<>();
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

	// read

	@Override
	public List<Resource> collectVertices(final String type) throws IOException {
		final ResIterator vertexStatements = model.listSubjectsWithProperty(RDF.type, model.getResource(BASE_PREFIX + type));
		final List<Resource> vertices = vertexStatements.toList();
		return vertices;
	}

	public void deleteIncomingEdge(final Collection<Resource> vertices, final String sourceVertexType, final String edgeType)
			throws IOException {
		deleteEdges(vertices, edgeType, false, true);
	}

	public void deleteAllOutgoingEdges(final Collection<Resource> vertices, final String vertexType, final String edgeType)
			throws IOException {
		deleteEdges(vertices, edgeType, true, true);
	}

	public void deleteOneOutgoingEdge(final Collection<Resource> vertices, final String vertexType, final String edgeType)
			throws IOException {
		deleteEdges(vertices, edgeType, true, false);
	}

	public void deleteSingleOutgoingEdge(final Collection<Resource> vertices, final String vertexType, final String edgeType)
			throws IOException {
		deleteEdges(vertices, edgeType, true, true);
	}

	protected void deleteEdges(final Collection<Resource> vertices, final String edgeType, final boolean outgoing, final boolean all) {
		final Property property = model.getProperty(RDFConstants.BASE_PREFIX + edgeType);

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

	@Override
	public Comparator<Resource> getElementComparator() {
		return elementComparator;
	}

	public Model getModel() {
		return model;
	}

}
