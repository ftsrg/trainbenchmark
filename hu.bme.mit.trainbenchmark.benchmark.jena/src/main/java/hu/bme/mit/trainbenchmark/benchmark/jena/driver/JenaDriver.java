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

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.AttributeOperation;
import hu.bme.mit.trainbenchmark.benchmark.driver.DatabaseDriver;
import hu.bme.mit.trainbenchmark.rdf.RDFConstants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
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

public class JenaDriver extends DatabaseDriver<Resource> {

	protected String basePrefix;
	protected Model model;
	protected Query query;
	protected String resultVar;

	public JenaDriver(final String basePrefix, final String queryPath) {
		this.basePrefix = basePrefix;
		query = QueryFactory.read(queryPath);
		resultVar = query.getResultVars().get(0);		
	}

	@Override
	public List<Resource> collectVertices(final String type) throws IOException {
		final ResIterator vertexStatements = model.listSubjectsWithProperty(RDF.type, model.getResource(RDFConstants.BASE_PREFIX + type));
		final List<Resource> vertices = vertexStatements.toList();
		return vertices;
	}

	@Override
	public void deleteAllOutgoingEdges(final Object vertex, final String edgeType) throws IOException {
		deleteEdges(vertex, edgeType, true, true);
	}

	private void deleteEdges(final Object vertex, final String edgeType, final boolean outgoing, final boolean all) {
		final Resource vertexResource = (Resource) vertex;
		final Property property = model.getProperty(RDFConstants.BASE_PREFIX + edgeType);

		// @formatter:off
		final Selector selector = outgoing ? 
				new SimpleSelector(vertexResource, property, (RDFNode) null) : 
				new SimpleSelector(null, property, vertexResource);
		// @formatter:on

		final StmtIterator edges = model.listStatements(selector);

		final List<Statement> statementsToRemove = new ArrayList<>();
		while (edges.hasNext()) {
			final Statement statementToRemove = edges.next();
			
			statementsToRemove.add(statementToRemove);

			if (!all) {
				break;
			}
		}

		for (final Statement statement : statementsToRemove) {
			model.remove(statement);
		}
	}

	@Override
	public void deleteAllIncomingEdges(final Object vertex, final String edgeType, final String sourceVertexType) throws IOException {
		deleteEdges(vertex, edgeType, false, true);
	}

	@Override
	public void updateProperty(final Object vertex, final String vertexType, final String propertyName,
			final AttributeOperation attributeOperation) throws IOException {
		final Resource sourceVertexResouce = (Resource) vertex;

		final Property property = model.getProperty(basePrefix + propertyName);
		final Selector selector = new SimpleSelector(sourceVertexResouce, property, (RDFNode) null);

		final StmtIterator statementsToRemove = model.listStatements(selector);
		if (statementsToRemove.hasNext()) {
			final Statement oldStatement = statementsToRemove.next();
			final Integer value = oldStatement.getInt();
			final Statement newStatement = model.createLiteralStatement(sourceVertexResouce, property, attributeOperation.op(value));
			model.remove(oldStatement);
			model.add(newStatement);
		}
	}

	@Override
	public void deleteOneOutgoingEdge(final Object vertex, final String edgeType) throws IOException {
		deleteEdges(vertex, edgeType, true, false);
	}

	@Override
	public void deleteOutgoingEdge(final Object vertex, final String vertexType, final String edgeType) throws IOException {
		deleteEdges(vertex, edgeType, true, true);
	}

	protected long newVertexId = 1000000000;

	@Override
	public void insertVertexWithEdge(final Object sourceVertex, final String sourceVertexType, final String targetVertexType,
			final String edgeType) throws IOException {
		final Resource sourceVertexResource = (Resource) sourceVertex;

		final Property edge = model.getProperty(basePrefix + edgeType);
		final Resource vertexType = model.getResource(basePrefix + targetVertexType);

		final Resource targetVertexResource = model.createResource(basePrefix + "x" + newVertexId);
		newVertexId++;

		model.add(model.createStatement(sourceVertexResource, edge, targetVertexResource));
		model.add(model.createStatement(targetVertexResource, RDF.type, vertexType));
	}

	@Override
	public void read(final String modelPath) throws IOException {
		model = ModelFactory.createDefaultModel();
		model.read(modelPath);
	}

	@Override
	public List<Resource> runQuery() throws IOException {
		final List<Resource> results = new ArrayList<>();
		try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
			final ResultSet resultSet = queryExecution.execSelect();

			while (resultSet.hasNext()) {
				final QuerySolution qs = resultSet.next();
				final Resource resource = qs.getResource(resultVar);
				results.add(resource);
			}
		}

		return results;
	}

	@Override
	public Comparator<Resource> getComparator() {
		return new JenaComparator();
	}

	@Override
	public void destroy() throws IOException {
		model.close();
	}

}
