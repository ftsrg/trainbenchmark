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
package hu.bme.mit.trainbenchmark.constants;

import static hu.bme.mit.trainbenchmark.constants.ExecutionPhase.CHECK;
import static hu.bme.mit.trainbenchmark.constants.ExecutionPhase.TRANSFORMATION;

public enum RailwayQuery {
	// well-formedness constraint queries (LHS for the Repair scenario)
	ACTIVEROUTE("ActiveRoute", CHECK), //
	CONNECTEDSEGMENTS("ConnectedSegments", CHECK), //
	POSLENGTH("PosLength", CHECK), //
	ROUTEREACHABILITY("RouteReachability", CHECK), //
	ROUTELENGTH("RouteLength", CHECK), //
	ROUTESENSOR("RouteSensor", CHECK), //
	SEMAPHORENEIGHBOR("SemaphoreNeighbor", CHECK), //
	SWITCHMONITORED("SwitchMonitored", CHECK), //
	SWITCHSET("SwitchSet", CHECK), //

	// LHS queries for the Inject scenario
	CONNECTEDSEGMENTS_INJECT("ConnectedSegmentsInject", TRANSFORMATION), //
	POSLENGTH_INJECT("PosLengthInject", TRANSFORMATION), //
	ROUTESENSOR_INJECT("RouteSensorInject", TRANSFORMATION), //
	SEMAPHORENEIGHBOR_INJECT("SemaphoreNeighborInject", TRANSFORMATION), //
	SWITCHMONITORED_INJECT("SwitchMonitoredInject", TRANSFORMATION), //
	SWITCHSET_INJECT("SwitchSetInject", TRANSFORMATION), //
	;

	private final String name;
	private final ExecutionPhase executionPhase;

	RailwayQuery(final String name, final ExecutionPhase executionPhase) {
		this.name = name;
		this.executionPhase = executionPhase;
	}

	@Override
	public String toString() {
		return name;
	}

	public ExecutionPhase getExecutionPhase() {
		return executionPhase;
	}

}
