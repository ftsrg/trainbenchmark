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

import hu.bme.mit.trainbenchmark.benchmark.matches.ActiveRouteMatch;
import hu.bme.mit.trainbenchmark.constants.QueryConstants;
import org.neo4j.graphdb.Node;

import java.util.Map;

public class Neo4jActiveRouteMatch extends Neo4jMatch implements ActiveRouteMatch {

	public Neo4jActiveRouteMatch(final Map<String, Object> match) {
		super(match);
	}

	@Override
	public Node getRoute() {
		return (Node) match.get(QueryConstants.VAR_ROUTE);
	}

	@Override
	public Node getSwP() {
		return (Node) match.get(QueryConstants.VAR_SWP);
	}

	@Override
	public Node getSw() {
		return (Node) match.get(QueryConstants.VAR_SW);
	}

}
