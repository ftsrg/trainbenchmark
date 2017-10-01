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
package hu.bme.mit.trainbenchmark.benchmark.tinkergraph.comparators;

import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import org.apache.tinkerpop.gremlin.structure.Vertex;

import java.util.Comparator;

public class VertexComparator implements Comparator<Vertex> {

	@Override
	public int compare(final Vertex v1, final Vertex v2) {
		final long id1 = extractLong(v1.property(ModelConstants.ID).value());
		final long id2 = extractLong(v2.property(ModelConstants.ID).value());
		return Long.compare(id1, id2);
	}

	public Long extractLong(final Object id) {
		if (id instanceof String) {
			return Long.parseLong((String) id);
		}
		if (id instanceof Long) {
			return (Long) id;
		}
		if (id instanceof Integer) {
			return ((Integer) id).longValue();
		}
		throw new UnsupportedOperationException("IDs of type " + id.getClass() + " are not supported.");
	}

}
