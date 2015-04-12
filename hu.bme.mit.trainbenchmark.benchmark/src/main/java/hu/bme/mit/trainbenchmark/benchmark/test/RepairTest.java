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

import static hu.bme.mit.trainbenchmark.constants.Scenario.REPAIR;
import hu.bme.mit.trainbenchmark.constants.Query;

import java.io.IOException;

import org.apache.commons.cli.ParseException;
import org.junit.Test;

public abstract class RepairTest extends TransformationTest {

	@Test
	public void posLengthRepair() throws ParseException, IOException {
		testTransformation(Query.POSLENGTH, REPAIR, 485);
	}

	@Test
	public void routeSensorRepair() throws ParseException, IOException {
		testTransformation(Query.ROUTESENSOR, REPAIR, 17);
	}

	@Test
	public void semaphoreNeighborRepair() throws ParseException, IOException {
		testTransformation(Query.SEMAPHORENEIGHBOR, REPAIR, 0);
	}

	@Test
	public void switchSensorRepair() throws ParseException, IOException {
		testTransformation(Query.SWITCHSENSOR, REPAIR, 3);
	}

	// @Test
	// public void switchSetRepair() throws ParseException, IOException {
	// testTransformation(Query.SWITCHSET, REPAIR, 3);
	// }

}
