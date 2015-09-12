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
import static hu.bme.mit.trainbenchmark.constants.Scenario.MINIMAL;

import java.io.IOException;

import org.apache.commons.cli.ParseException;
import org.junit.Test;

public abstract class MinimalTest extends TrainBenchmarkTest {

	@Test
	public void connectedSegments() throws ParseException, IOException {
		testQuery(CONNECTEDSEGMENTS, MINIMAL, 1);
	}

	@Test
	public void posLength() throws ParseException, IOException {
		testQuery(POSLENGTH, MINIMAL, 1);
	}

	@Test
	public void routeSensor() throws ParseException, IOException {
		testQuery(ROUTESENSOR, MINIMAL, 1);
	}

	@Test
	public void semaphoreNeighbor() throws ParseException, IOException {
		testQuery(SEMAPHORENEIGHBOR, MINIMAL, 1);
	}

	@Test
	public void switchSensor() throws ParseException, IOException {
		testQuery(SWITCHSENSOR, MINIMAL, 1);
	}

	@Test
	public void switchSet() throws ParseException, IOException {
		testQuery(SWITCHSET, MINIMAL, 1);
	}

}