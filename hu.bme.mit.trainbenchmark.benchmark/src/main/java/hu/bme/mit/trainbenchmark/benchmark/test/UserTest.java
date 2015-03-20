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

import static hu.bme.mit.trainbenchmark.constants.QueryConstants.POSLENGTH;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.ROUTESENSOR;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.SEMAPHORENEIGHBOR;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.SWITCHSENSOR;
import static hu.bme.mit.trainbenchmark.constants.ScenarioConstants.USER;

import java.io.IOException;

import org.apache.commons.cli.ParseException;
import org.junit.Test;

public abstract class UserTest extends TransformationTest {

	@Test
	public void posLengthUser() throws ParseException, IOException {
		testTransformation(POSLENGTH, USER, 487);
	}

	@Test
	public void routeSensorUser() throws ParseException, IOException {
		testTransformation(ROUTESENSOR, USER, 18);
	}

	@Test
	public void semaphoreNeighborUser() throws ParseException, IOException {
		testTransformation(SEMAPHORENEIGHBOR, USER, 1);
	}

	@Test
	public void switchSensorUser() throws ParseException, IOException {
		testTransformation(SWITCHSENSOR, USER, 5);
	}
	
//	@Test
//	public void switchSetUser() throws ParseException, IOException {
//		testTransformation(QueryConstants.SWITCHSENSOR, ScenarioConstants.USER, 5);
//	}

}
