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

import org.apache.tinkerpop.gremlin.structure.Vertex;

import hu.bme.mit.trainbenchmark.benchmark.matches.comparators.MatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphMatch;

public class TinkerGraphMatchComparator extends MatchComparator<TinkerGraphMatch, Vertex> {

	protected TinkerGraphMatchComparator() {
		super(new VertexComparator());
	}
	
	public static TinkerGraphMatchComparator create() {
		return new TinkerGraphMatchComparator();
	}
	
	@Override
	public int compare(final TinkerGraphMatch o1, final TinkerGraphMatch o2) {
		final Vertex[] m1 = o1.toArray();
		final Vertex[] m2 = o2.toArray();
		return compareArrays(m1, m2);
	}

}
