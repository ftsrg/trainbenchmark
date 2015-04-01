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
package hu.bme.mit.trainbenchmark.benchmark.orientdb.driver;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.PropertyOperation;
import hu.bme.mit.trainbenchmark.benchmark.driver.DatabaseDriver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.google.common.collect.Lists;
import com.orientechnologies.orient.graph.gremlin.OCommandGremlin;
import com.orientechnologies.orient.graph.gremlin.OGremlinHelper;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.util.io.graphml.GraphMLReader;

public class OrientDbDriver extends DatabaseDriver<Vertex> {

	protected OrientGraph graphDb;
	protected String dbPath;
	protected String query;

	public OrientDbDriver(String dbPath, final String query) throws IOException {
		// start with a clean slate: delete old directory
		if (new File(dbPath).exists()) {
			FileUtils.deleteDirectory(new File(dbPath));
		}

		// start the database
		this.dbPath = dbPath;
		this.query = query;
	}

	@Override
	public void read(String filePath) throws IOException {
		graphDb = new OrientGraph("plocal:" + dbPath);
		GraphMLReader graphMLReader = new GraphMLReader(graphDb);
		graphMLReader.inputGraph(filePath, 100);
	}

	@Override
	public List<Vertex> runQuery() throws IOException {
		List<Vertex> results = new ArrayList<Vertex>();

		OGremlinHelper.global().create();

		OCommandGremlin gremcomm = new OCommandGremlin(query);
		results = gremcomm.execute();

		return results;
	}

	@Override
	public Comparator<Vertex> getComparator() {
		return new VertexComparator();
	}

	@Override
	public void destroy() {
		graphDb.shutdown();
	}

	// create
	
	@Override
	public void insertVertexWithEdge(List<Vertex> sourceVertices,
			String sourceVertexType, String targetVertexType, String edgeType)
			throws IOException {
		for (final Vertex vertex : sourceVertices) {
			insertVertexWithEdge(vertex, (String)vertex.getProperty("labels"), targetVertexType, edgeType);
		}
	}

	@Override
	public Vertex insertVertexWithEdge(Vertex sourceVertex,
			String sourceVertexType, String targetVertexType, String edgeType)
			throws IOException {
		Vertex targetVertex = graphDb.addVertex(null);
		targetVertex.setProperty("labels", targetVertexType);
		sourceVertex.addEdge(edgeType, targetVertex);
		return targetVertex;
	}

	@Override
	public void insertEdge(Vertex sourceVertex, String sourceVertexType,
			Vertex targetVertex, String edgeType) throws IOException {
		graphDb.addEdge(null, sourceVertex, targetVertex, edgeType);
	}

	// read
	
	@Override
	public List<Vertex> collectVertices(String type) throws IOException {
		final Iterable<Vertex> vertices = graphDb.getVertices("labels", type);
		List<Vertex> list = Lists.newArrayList(vertices);
		return list;
	}

	@Override
	public List<Vertex> collectOutgoingConnectedVertices(Vertex sourceVertex,
			String sourceVertexType, String targetVertexType, String edgeType)
			throws IOException {
		final List<Vertex> neighbors = new ArrayList<Vertex>();
		final Iterable<Vertex> targetVertices = sourceVertex.getVertices(Direction.OUT, edgeType);
		for (Vertex vertex : targetVertices) {
			if (vertex.getProperty("labels") == targetVertexType) {
				neighbors.add(vertex);
			}
		}
		return neighbors;
	}

	// update
	
	@Override
	public void updateProperties(List<Vertex> vertices, String vertexType,
			String propertyName, PropertyOperation propertyOperation)
			throws IOException {
		for (final Vertex vertex : vertices) {
			final Integer property = (Integer) vertex.getProperty(propertyName);
			vertex.setProperty(propertyName, propertyOperation.op(property));
		}
	}
	
	// delete

	@Override
	public void deleteAllIncomingEdges(List<Vertex> vertices,
			String sourceVertexType, String edgeType) throws IOException {
		deleteEdges(vertices, edgeType, false, true);
	}

	@Override
	public void deleteAllOutgoingEdges(List<Vertex> vertices,
			String vertexType, String edgeType) throws IOException {
		deleteEdges(vertices, edgeType, true, true);
	}

	@Override
	public void deleteOneOutgoingEdge(List<Vertex> vertices, String vertexType,
			String edgeType) throws IOException {
		deleteEdges(vertices, edgeType, true, false);
	}

	@Override
	public void deleteSingleOutgoingEdge(List<Vertex> vertices,
			String vertexType, String edgeType) throws IOException {
		deleteEdges(vertices, edgeType, true, false);
	}
	
	public void deleteEdges(List<Vertex> vertices, String edgeType, boolean outgoing, boolean all) {
		Direction direction = outgoing ? Direction.OUT : Direction.IN;
		
		for (Vertex vertex : vertices) {
			Iterable<Edge> edges = vertex.getEdges(direction, edgeType);
			for (Edge edge : edges) {
				graphDb.removeEdge(edge);
				
				if (!all) {
					break;
				}
			}
		}
		
	}

	@Override
	public void deleteVertex(Vertex vertex, String vertexType)
			throws IOException {
		graphDb.removeVertex(vertex);
	}

	@Override
	public void deleteVertex(Long vertex) throws IOException {
		// TODO Auto-generated method stub
	}
	
	// utility
	
	public OrientGraph getGraphDb() {
		return graphDb;
	}

}
