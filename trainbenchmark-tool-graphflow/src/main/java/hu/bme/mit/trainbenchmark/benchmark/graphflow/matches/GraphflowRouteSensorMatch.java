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
package hu.bme.mit.trainbenchmark.benchmark.graphflow.matches;

import ca.waterloo.dsg.graphflow.query.operator.udf.subgraph.Vertex;
import hu.bme.mit.trainbenchmark.benchmark.matches.RouteSensorMatch;
import hu.bme.mit.trainbenchmark.constants.QueryConstants;

import java.util.Map;

public class GraphflowRouteSensorMatch extends GraphflowMatch implements RouteSensorMatch {

	public GraphflowRouteSensorMatch(final Map<String, Object> match) {
		super(match);
	}

	@Override
	public Vertex getRoute() {
		return (Vertex) match.get(QueryConstants.VAR_ROUTE);
	}

	@Override
	public Vertex getSensor() {
		return (Vertex) match.get(QueryConstants.VAR_SENSOR);
	}

	@Override
	public Vertex getSwP() {
		return (Vertex) match.get(QueryConstants.VAR_SWP);
	}

	@Override
	public Vertex getSw() {
		return (Vertex) match.get(QueryConstants.VAR_SW);
	}

}
