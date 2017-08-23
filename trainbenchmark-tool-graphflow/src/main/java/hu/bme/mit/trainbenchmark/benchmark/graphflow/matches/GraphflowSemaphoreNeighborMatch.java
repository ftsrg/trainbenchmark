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
import hu.bme.mit.trainbenchmark.benchmark.matches.SemaphoreNeighborMatch;

import java.util.Map;

import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_ROUTE1;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_ROUTE2;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEMAPHORE;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SENSOR1;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SENSOR2;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_TE1;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_TE2;

public class GraphflowSemaphoreNeighborMatch extends GraphflowMatch implements SemaphoreNeighborMatch {

	public GraphflowSemaphoreNeighborMatch(final Map<String, Object> match) {
		super(match);
	}

	@Override
	public Vertex getSemaphore() {
		return (Vertex) match.get(VAR_SEMAPHORE);
	}

	@Override
	public Vertex getRoute1() {
		return (Vertex) match.get(VAR_ROUTE1);
	}

	@Override
	public Vertex getRoute2() {
		return (Vertex) match.get(VAR_ROUTE2);
	}

	@Override
	public Vertex getSensor1() {
		return (Vertex) match.get(VAR_SENSOR1);
	}

	@Override
	public Vertex getSensor2() {
		return (Vertex) match.get(VAR_SENSOR2);
	}

	@Override
	public Vertex getTe1() {
		return (Vertex) match.get(VAR_TE1);
	}

	@Override
	public Vertex getTe2() {
		return (Vertex) match.get(VAR_TE2);
	}

}
