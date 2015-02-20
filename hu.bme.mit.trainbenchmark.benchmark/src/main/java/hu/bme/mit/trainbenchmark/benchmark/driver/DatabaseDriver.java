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
package hu.bme.mit.trainbenchmark.benchmark.driver;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.AttributeOperation;

import java.io.IOException;
import java.util.List;

public abstract class DatabaseDriver {

	public void beginTransaction() throws IOException {
	}

	public void finishTransaction() throws IOException {
	}

	public void destroy() throws IOException {
	}

	public abstract List<? extends Object> collectVertices(final String type) throws IOException;

	public abstract void deleteAllOutgoingEdges(final Object vertex, final String edgeType) throws IOException;

	public abstract void deleteAllIncomingEdges(Object vertex, String edgeType, String sourceVertexType) throws IOException;

	public abstract void updateProperty(final Object vertex, final String propertyName, final AttributeOperation attributeOperation)
			throws IOException;

	public abstract void deleteOneOutgoingEdge(final Object vertex, final String edgeType) throws IOException;

	public abstract void insertVertexWithEdge(Object sourceVertex, String sourceVertexType, String targetVertexType, String edgeType) throws IOException;

}
