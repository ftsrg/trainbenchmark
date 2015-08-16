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
import static hu.bme.mit.trainbenchmark.constants.ScenarioConstants.INJECT;

import java.io.IOException;

import org.apache.commons.cli.ParseException;
import org.junit.Test;

public abstract class InjectTest extends TrainBenchmarkTest {

	@Test
	public void connectedSegmentsInject() throws ParseException, IOException {
		testQuery(CONNECTEDSEGMENTS, INJECT, 5);
	}

	@Test
	public void posLengthInject() throws ParseException, IOException {
		testQuery(POSLENGTH, INJECT, 22);
	}

	@Test
	public void routeSensorInject() throws ParseException, IOException {
		testQuery(ROUTESENSOR, INJECT, 2);
	}

	@Test
	public void semaphoreNeighborInject() throws ParseException, IOException {
		testQuery(SEMAPHORENEIGHBOR, INJECT, 2);
	}

	@Test
	public void switchSensorInject() throws ParseException, IOException {
		testQuery(SWITCHSENSOR, INJECT, 1);
	}

	@Test
	public void switchSetInject() throws ParseException, IOException {
		testQuery(SWITCHSET, INJECT, 1);
	}

}
