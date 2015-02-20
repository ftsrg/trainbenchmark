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
import hu.bme.mit.trainbenchmark.rdf.RDFConstants;

import java.io.IOException;
import java.util.List;

import eu.mondo.driver.fourstore.FourStoreGraphDriverReadWrite;

public class FourStoreDriver extends DatabaseDriver {

	protected FourStoreGraphDriverReadWrite fsDriver;

	public FourStoreDriver(final FourStoreGraphDriverReadWrite fsDriver) {
		this.fsDriver = fsDriver;
	}

	@Override
	public List<? extends Object> collectVertices(final String type) throws IOException {
		return fsDriver.collectVertices(type);
	}

	@Override
	public void deleteAllOutgoingEdges(final Object vertex, final String edgeType) throws IOException {
		//4s-query trainbenchmark_cluster -s -1 -f text "SELECT ?x WHERE {?x <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.semanticweb.org/ontologies/2011/1/TrainRequirementOntology.owl#Route>}"  
		
		//4s-update trainbenchmark_cluster 
		//"
		//DELETE {?x <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.semanticweb.org/ontologies/2011/1/TrainRequirementOntology.owl#Route>} 
		//WHERE  {?x <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.semanticweb.org/ontologies/2011/1/TrainRequirementOntology.owl#Route>}
		//"
		final String query = String.format("DELETE {<%sx%d> <%s> ?z} WHERE {<%sx%d> <%s> ?z}",
				RDFConstants.BASE_PREFIX, vertex, RDFConstants.BASE_PREFIX + edgeType, 
				RDFConstants.BASE_PREFIX, vertex, RDFConstants.BASE_PREFIX + edgeType 
				);
		fsDriver.runUpdate(query);
		
	}

	@Override
	public void deleteAllIncomingEdges(final Object vertex, final String edgeType, final String sourceVertexType) throws IOException {
		final String query = String.format("DELETE {?x <%s> <%sx%d>} WHERE {?x <%s> <%sx%d>}",
				RDFConstants.BASE_PREFIX + edgeType, RDFConstants.BASE_PREFIX, vertex,  
				RDFConstants.BASE_PREFIX + edgeType, RDFConstants.BASE_PREFIX, vertex
				);
		System.out.println(query);
		fsDriver.runUpdate(query);
	}

	@Override
	public void updateProperty(final Object vertex, final String propertyName, final AttributeOperation attributeOperation)
			throws IOException {

	}

	@Override
	public void deleteOneOutgoingEdge(final Object vertex, final String edgeType) throws IOException {

	}

	@Override
	public void insertVertexWithEdge(final Object sourceVertex, final String sourceVertexType, final String targetVertexType,
			final String edgeType) throws IOException {

	}

}
