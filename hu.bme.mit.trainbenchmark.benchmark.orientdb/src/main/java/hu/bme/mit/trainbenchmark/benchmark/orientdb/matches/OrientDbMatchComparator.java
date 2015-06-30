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
package hu.bme.mit.trainbenchmark.benchmark.orientdb.matches;

import com.tinkerpop.blueprints.Vertex;

import hu.bme.mit.trainbenchmark.benchmark.matches.MatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.driver.VertexComparator;

public class OrientDbMatchComparator extends MatchComparator<OrientDbMatch, Vertex> {

	protected VertexComparator vc = new VertexComparator();
	
	@Override
	public int compare(final OrientDbMatch o1, final OrientDbMatch o2) {
		final Vertex[] m1 = o1.toArray();
		final Vertex[] m2 = o2.toArray();
		return compareArrays(m1, m2, vc);
	}
}
