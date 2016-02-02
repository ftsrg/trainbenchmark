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

import static hu.bme.mit.trainbenchmark.constants.Query.CONNECTEDSEGMENTS;
import static hu.bme.mit.trainbenchmark.constants.Query.POSLENGTH;
import static hu.bme.mit.trainbenchmark.constants.Query.ROUTESENSOR;
import static hu.bme.mit.trainbenchmark.constants.Query.SEMAPHORENEIGHBOR;
import static hu.bme.mit.trainbenchmark.constants.Query.SWITCHSENSOR;
import static hu.bme.mit.trainbenchmark.constants.Query.SWITCHSET;
import static hu.bme.mit.trainbenchmark.constants.Scenario.INJECT;

import org.junit.Test;

public abstract class InjectTest extends TrainBenchmarkTest {
	
	@Test
	public void connectedSegmentsInject() throws Exception {
		testTransformation(CONNECTEDSEGMENTS, INJECT, 5, 7);
	}

	@Test
	public void posLengthInject() throws Exception {
		testTransformation(POSLENGTH, INJECT, 13, 15);
	}

	@Test
	public void routeSensorInject() throws Exception {
		testTransformation(ROUTESENSOR, INJECT, 6, 8);
	}

	@Test
	public void semaphoreNeighborInject() throws Exception {
		testTransformation(SEMAPHORENEIGHBOR, INJECT, 9, 17);
	}

	@Test
	public void switchSensorInject() throws Exception {
		testTransformation(SWITCHSENSOR, INJECT, 0, 2);
	}

	@Test
	public void switchSetInject() throws Exception {
		testTransformation(SWITCHSET, INJECT, 0, 0);
	}

}
