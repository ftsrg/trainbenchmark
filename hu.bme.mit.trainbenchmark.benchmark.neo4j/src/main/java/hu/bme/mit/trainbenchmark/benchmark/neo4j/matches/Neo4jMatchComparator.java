/*******************************************************************************
 * Copyright (c) 2010-2015, Gabor Szarnyas, Benedek Izso, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.benchmark.neo4j.matches;

import hu.bme.mit.trainbenchmark.benchmark.matches.MatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.NodeComparator;

import org.neo4j.graphdb.Node;

public class Neo4jMatchComparator extends MatchComparator<Neo4jMatch, Node> {

	protected NodeComparator nc = new NodeComparator();

	@Override
	public int compare(final Neo4jMatch o1, final Neo4jMatch o2) {
		final Node[] m1 = o1.toArray();
		final Node[] m2 = o2.toArray();
		return compareArrays(m1, m2, nc);
	}

}
