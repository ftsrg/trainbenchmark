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
import com.orientechnologies.orient.client.db.ODatabaseHelper;
import com.orientechnologies.orient.core.db.ODatabase;
import com.orientechnologies.orient.core.exception.OConcurrentModificationException;
import com.orientechnologies.orient.graph.gremlin.OCommandGremlin;
import com.orientechnologies.orient.graph.gremlin.OGremlinHelper;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.GraphFactory;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
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
	public void beginTransaction() {
		graphDb.begin();
	}
	
	@Override
	public void finishTransaction() {
		graphDb.commit();
	}
	
	@Override
	public void read(String filePath) throws IOException {
		graphDb = new OrientGraph("plocal:" + dbPath);
		GraphMLReader graphMLReader = new GraphMLReader(graphDb);
		graphMLReader.inputGraph(filePath, 100);
	}

	@Override
	public List<Vertex> runQuery() throws IOException {
		OGremlinHelper.global().create();

		OCommandGremlin gremcomm = new OCommandGremlin(query);
		List<Vertex> results = gremcomm.execute();
		
		return results;
	}

	@Override
	public Comparator<Vertex> getComparator() {
		return new VertexComparator();
	}

	@Override
	public void destroy() throws IOException {
		graphDb.drop();
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
		int maxRetry = 10;
		for (int retry = 0; retry < maxRetry; ++retry) {
			try {
				targetVertex.setProperty("labels", targetVertexType);
				sourceVertex.addEdge(edgeType, targetVertex);
				graphDb.commit();
				break;
			} catch(OConcurrentModificationException e) {}
		}
		
		return targetVertex;
	}

	@Override
	public void insertEdge(Vertex sourceVertex, String sourceVertexType,
			Vertex targetVertex, String edgeType) throws IOException {
		int maxRetry = 10;
		for (int retry = 0; retry < maxRetry; ++retry) {
			try {
				graphDb.addEdge(null, sourceVertex, targetVertex, edgeType);
				graphDb.commit();
				break;
			} catch(OConcurrentModificationException e) {}
		}
	}

	// read
	
	public String typeTranslator(String type) {
		String result;
		switch (type) {
			case "Segment":
				result = ":TrackElement:Segment";
				break;
			case "Signal":
				result = ":Signal";
				break;
			case "Sensor":
				result = ":Sensor";
				break;
			case "Switch":
				result = ":Switch:TrackElement";
				break;
			case "Route":
				result = ":Route";
				break;
			case "SwitchPosition":
				result = ":SwitchPosition";
				break;
			default:
				result = type;
				break;
		}
		return result;
	}
	
	@Override
	public List<Vertex> collectVertices(String type) throws IOException {
		final Iterable<Vertex> vertices = graphDb.getVertices("labels", typeTranslator(type));
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
			System.out.println("[ORIENTDB] Vertex: " + vertex.toString() + ", old property: " + property + ", new property: " + propertyOperation.op(property));
			int maxRetry = 10;
			for (int retry = 0; retry < maxRetry; ++retry) {
				try {
					vertex.setProperty(propertyName, propertyOperation.op(property));
					graphDb.commit();
					break;
				} catch(OConcurrentModificationException e) {}
			}
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
				int maxRetry = 10;
				for (int retry = 0; retry < maxRetry; ++retry) {
					try {
						edge.remove();
						graphDb.commit();
						break;
					} catch(OConcurrentModificationException e) {}
				}
				
				if (!all) {
					break;
				}
			}
		}
		
	}

	@Override
	public void deleteVertex(Vertex vertex, String vertexType)
			throws IOException {
		int maxRetry = 10;
		for (int retry = 0; retry < maxRetry; ++retry) {
			try {
				vertex.remove();
				graphDb.commit();
				break;
			} catch(OConcurrentModificationException e) {}
		}
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
