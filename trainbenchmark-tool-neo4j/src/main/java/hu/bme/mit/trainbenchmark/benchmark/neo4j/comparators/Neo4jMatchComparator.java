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
package hu.bme.mit.trainbenchmark.benchmark.neo4j.comparators;

import org.neo4j.graphdb.Node;

import hu.bme.mit.trainbenchmark.benchmark.matches.comparators.MatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jMatch;

public class Neo4jMatchComparator extends MatchComparator<Neo4jMatch, Node> {

	protected Neo4jMatchComparator() {
		super(new NodeComparator());
	}
	
	public static Neo4jMatchComparator create() {
		return new Neo4jMatchComparator();
	}

	@Override
	public int compare(final Neo4jMatch o1, final Neo4jMatch o2) {
		final Node[] m1 = o1.toArray();
		final Node[] m2 = o2.toArray();
		return compareArrays(m1, m2);
	}

}
