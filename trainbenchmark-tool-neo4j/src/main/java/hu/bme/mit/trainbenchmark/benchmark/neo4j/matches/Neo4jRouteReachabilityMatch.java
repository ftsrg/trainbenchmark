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

import hu.bme.mit.trainbenchmark.benchmark.matches.RouteReachabilityMatch;
import hu.bme.mit.trainbenchmark.constants.QueryConstants;
import org.neo4j.graphdb.Node;

import java.util.Map;

public class Neo4jRouteReachabilityMatch extends Neo4jMatch implements RouteReachabilityMatch {

	public Neo4jRouteReachabilityMatch(final Map<String, Object> match) {
		super(match);
	}

	@Override
	public Node getRoute() {
		return (Node) match.get(QueryConstants.VAR_ROUTE);
	}

	@Override
	public Node getSwP1() {
		return (Node) match.get(QueryConstants.VAR_SWP1);
	}

	@Override
	public Node getSwP2() {
		return (Node) match.get(QueryConstants.VAR_SWP2);
	}

	@Override
	public Node getSw1() {
		return (Node) match.get(QueryConstants.VAR_SW1);
	}

	@Override
	public Node getSw2() {
		return (Node) match.get(QueryConstants.VAR_SW2);
	}


}
