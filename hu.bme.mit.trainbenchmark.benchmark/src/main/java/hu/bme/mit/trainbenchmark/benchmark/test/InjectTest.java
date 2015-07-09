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

import java.io.IOException;

import org.apache.commons.cli.ParseException;
import org.junit.Test;

public abstract class InjectTest extends TrainBenchmarkTest {

	@Test
	public void connectedSegmentsInject() throws ParseException, IOException {
		testTransformation(CONNECTEDSEGMENTS, INJECT, 4, 5);
	}

	@Test
	public void posLengthInject() throws ParseException, IOException {
		testTransformation(POSLENGTH, INJECT, 21, 22);
	}

	@Test
	public void routeSensorInject() throws ParseException, IOException {
		testTransformation(ROUTESENSOR, INJECT, 2, 2);
	}

	@Test
	public void semaphoreNeighborInject() throws ParseException, IOException {
		testTransformation(SEMAPHORENEIGHBOR, INJECT, 1, 2);
	}

	@Test
	public void switchSensorInject() throws ParseException, IOException {
		testTransformation(SWITCHSENSOR, INJECT, 0, 1);
	}

	@Test
	public void switchSetInject() throws ParseException, IOException {
		testTransformation(SWITCHSET, INJECT, 1, 1);
	}

}
