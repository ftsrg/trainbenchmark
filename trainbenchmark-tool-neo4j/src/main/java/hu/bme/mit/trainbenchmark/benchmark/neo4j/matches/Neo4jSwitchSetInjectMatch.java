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
package hu.bme.mit.trainbenchmark.benchmark.neo4j.matches;

import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SW;

import java.util.Map;

import org.neo4j.graphdb.Node;

import hu.bme.mit.trainbenchmark.benchmark.matches.SwitchSetInjectMatch;

public class Neo4jSwitchSetInjectMatch extends Neo4jMatch implements SwitchSetInjectMatch {

	public Neo4jSwitchSetInjectMatch(final Map<String, Object> match) {
		super(match);
	}

	@Override
	public Node getSw() {
		return (Node) match.get(VAR_SW);
	}

}
