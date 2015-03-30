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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.PropertyOperation;
import hu.bme.mit.trainbenchmark.benchmark.driver.DatabaseDriver;

import org.apache.commons.io.FileUtils;

import com.orientechnologies.orient.graph.gremlin.OCommandGremlin;
import com.orientechnologies.orient.graph.gremlin.OGremlinHelper;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.util.io.graphml.GraphMLReader;

public class OrientDbDriver extends DatabaseDriver<Long> {

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
		try {
			GraphMLReader graphMLReader = new GraphMLReader(graphDb);
			graphMLReader.inputGraph(filePath, 100);
		} catch (Exception e) {
			throw new IOException(e);
		}
	}

	@Override
	public List<Long> runQuery() throws IOException {
		List<Long> results = new ArrayList<Long>();
		
		OGremlinHelper.global().create();
		try {
			OCommandGremlin gremcomm = new OCommandGremlin(query);
			results = gremcomm.execute();
		} catch (Exception e) {
			new IOException(e);
		}
		
		return results;
	}

	@Override
	public Comparator<Long> getComparator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void destroy() {
		graphDb.shutdown();
	}

	@Override
	public void insertVertexWithEdge(List<Long> sourceVertices,
			String sourceVertexType, String targetVertexType, String edgeType)
			throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long insertVertexWithEdge(Long sourceVertex,
			String sourceVertexType, String targetVertexType, String edgeType)
			throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertEdge(Long sourceVertex, String sourceVertexType,
			Long targetVertex, String edgeType) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Long> collectVertices(String type) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Long> collectOutgoingConnectedVertices(Long sourceVertex,
			String sourceVertexType, String targetVertexType, String edgeType)
			throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateProperties(List<Long> vertices, String vertexType,
			String propertyName, PropertyOperation propertyOperation)
			throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllIncomingEdges(List<Long> vertices,
			String sourceVertexType, String edgeType) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllOutgoingEdges(List<Long> vertices, String vertexType,
			String edgeType) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteOneOutgoingEdge(List<Long> vertices, String vertexType,
			String edgeType) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteSingleOutgoingEdge(List<Long> vertices,
			String vertexType, String edgeType) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteVertex(Long vertex, String vertexType) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteVertex(Long vertex) throws IOException {
		// TODO Auto-generated method stub
		
	}

}
