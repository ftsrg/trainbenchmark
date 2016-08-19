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

import hu.bme.mit.trainbenchmark.benchmark.matches.comparators.BaseMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jMatch;

public class Neo4jMatchComparator extends BaseMatchComparator<Neo4jMatch, Node> {

	public Neo4jMatchComparator() {
		super(new NodeComparator());
	}

}
