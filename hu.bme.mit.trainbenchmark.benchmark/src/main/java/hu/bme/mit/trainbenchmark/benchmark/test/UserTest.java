/*******************************************************************************
 * Copyright (c) 2010-2014, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
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
import static hu.bme.mit.trainbenchmark.constants.Scenario.USER;

import java.io.IOException;

import org.apache.commons.cli.ParseException;
import org.junit.Test;

public abstract class UserTest extends TransformationTest {

	@Test
	public void connectedSegmentsUser() throws ParseException, IOException {
		testTransformation(CONNECTEDSEGMENTS, USER, 5);
	}

	@Test
	public void posLengthUser() throws ParseException, IOException {
		testTransformation(POSLENGTH, USER, 22);
	}

	@Test
	public void routeSensorUser() throws ParseException, IOException {
		testTransformation(ROUTESENSOR, USER, 2);
	}

	@Test
	public void semaphoreNeighborUser() throws ParseException, IOException {
		testTransformation(SEMAPHORENEIGHBOR, USER, 2);
	}

	@Test
	public void switchSensorUser() throws ParseException, IOException {
		testTransformation(SWITCHSENSOR, USER, 1);
	}

	@Test
	public void switchSetUser() throws ParseException, IOException {
		testTransformation(SWITCHSET, USER, 1);
	}

}
