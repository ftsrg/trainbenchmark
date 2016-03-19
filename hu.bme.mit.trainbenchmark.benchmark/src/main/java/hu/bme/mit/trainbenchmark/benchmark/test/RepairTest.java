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

package hu.bme.mit.trainbenchmark.benchmark.test;

import static hu.bme.mit.trainbenchmark.constants.RailwayQuery.CONNECTEDSEGMENTS;
import static hu.bme.mit.trainbenchmark.constants.RailwayQuery.POSLENGTH;
import static hu.bme.mit.trainbenchmark.constants.RailwayQuery.ROUTESENSOR;
import static hu.bme.mit.trainbenchmark.constants.RailwayQuery.SEMAPHORENEIGHBOR;
import static hu.bme.mit.trainbenchmark.constants.RailwayQuery.SWITCHSENSOR;
import static hu.bme.mit.trainbenchmark.constants.RailwayQuery.SWITCHSET;
import static hu.bme.mit.trainbenchmark.constants.ScenarioEnum.REPAIR;

import org.junit.Test;

public abstract class RepairTest extends TrainBenchmarkTest {
	
	@Test
	public void connectedSegmentsRepair() throws Exception {
		testTransformation(CONNECTEDSEGMENTS, REPAIR, 9, 7);
	}

	@Test
	public void posLengthRepair() throws Exception {
		testTransformation(POSLENGTH, REPAIR, 197, 195);
	}

	@Test
	public void routeSensorRepair() throws Exception {
		testTransformation(ROUTESENSOR, REPAIR, 26, 24);
	}

	@Test
	public void semaphoreNeighborRepair() throws Exception {
		testTransformation(SEMAPHORENEIGHBOR, REPAIR, 3, 0);
	}

	@Test
	public void switchSensorRepair() throws Exception {
		testTransformation(SWITCHSENSOR, REPAIR, 1, 0);
	}

	@Test
	public void switchSetRepair() throws Exception {
		testTransformation(SWITCHSET, REPAIR, 2, 0);
	}

}
