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
package hu.bme.mit.trainbenchmark.benchmark.fourstore.driver;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.AttributeOperation;
import hu.bme.mit.trainbenchmark.benchmark.driver.DatabaseDriver;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.io.FileUtils;

import eu.mondo.driver.fourstore.FourStoreGraphDriverReadWrite;

public class FourStoreDriver extends DatabaseDriver<Long> {

	protected FourStoreGraphDriverReadWrite driver;
	protected final String basePrefix;
	protected final String query;

	public FourStoreDriver(final String basePrefix, final String queryPath, final String clusterName) throws IOException {
		this.basePrefix = basePrefix;
		this.query = FileUtils.readFileToString(new File(queryPath));
		this.driver = new FourStoreGraphDriverReadWrite(clusterName);
		driver.start();
	}

	@Override
	public void read(final String modelPath) throws IOException {
		try {
			driver.load(modelPath);
		} catch (final InterruptedException e) {
			throw new IOException(e);
		}
	}

	@Override
	public List<Long> collectVertices(final String type) throws IOException {
		return driver.collectVertices(type);
	}

	@Override
	public void deleteAllOutgoingEdges(final Object vertex, final String edgeType) throws IOException {
		// 4s-query trainbenchmark_cluster -s -1 -f text
		// "SELECT ?x WHERE {?x <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.semanticweb.org/ontologies/2011/1/TrainRequirementOntology.owl#Route>}"

		// 4s-update trainbenchmark_cluster
		// "
		// DELETE {?x <http://www.w3.org/1999/02/22-rdf-syntax-ns#type>
		// <http://www.semanticweb.org/ontologies/2011/1/TrainRequirementOntology.owl#Route>}
		// WHERE {?x <http://www.w3.org/1999/02/22-rdf-syntax-ns#type>
		// <http://www.semanticweb.org/ontologies/2011/1/TrainRequirementOntology.owl#Route>}
		// "
		final String query = String.format("DELETE {<%sx%d> <%s> ?z} WHERE {<%sx%d> <%s> ?z}", basePrefix, vertex, basePrefix + edgeType,
				basePrefix, vertex, basePrefix + edgeType);
		driver.runUpdate(query);
	}

	@Override
	public void deleteAllIncomingEdges(final Object vertex, final String edgeType, final String sourceVertexType) throws IOException {
		final String query = String.format( //
				"DELETE {?x <%s> <%sx%d>} WHERE {?x <%s> <%sx%d>}", //
				basePrefix + edgeType, basePrefix, vertex, //
				basePrefix + edgeType, basePrefix, vertex);
		System.out.println(query);
		driver.runUpdate(query);
	}

	@Override
	public void updateProperties(final Object vertex, final String vertexType, final String propertyName,
			final AttributeOperation attributeOperation) throws IOException {

	}

	@Override
	public void deleteOneOutgoingEdge(final Object vertex, final String edgeType) throws IOException {

	}

	@Override
	public void insertVertexWithEdge(final Object sourceVertex, final String sourceVertexType, final String targetVertexType,
			final String edgeType) throws IOException {

	}

	@Override
	public void deleteOutgoingEdge(final Object vertex, final String vertexType, final String edgeType) throws IOException {

	}

	@Override
	public List<Long> runQuery() throws IOException {
		final List<Long> results = driver.queryIds(query);
		return results;
	}
	
	@Override
	public void destroy() throws IOException {
		driver.stop();
	}

	@Override
	public Comparator<Long> getComparator() {
		// a null comparator provides natural ordering
		return null;
	}

}
