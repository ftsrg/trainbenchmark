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

import java.util.Comparator;

import org.apache.tinkerpop.gremlin.structure.Vertex;

public class VertexComparator implements Comparator<Vertex> {

	@Override
	public int compare(final Vertex v1, final Vertex v2) {
		final String id1 = (String) v1.id();
		final String id2 = (String) v2.id();
		return id1.compareTo(id2);
	}

}
