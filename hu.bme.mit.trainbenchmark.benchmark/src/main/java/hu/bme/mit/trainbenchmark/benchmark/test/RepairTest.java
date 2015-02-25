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

import java.io.IOException;

import org.apache.commons.cli.ParseException;
import org.junit.Test;

public abstract class RepairTest extends TransformationTest {

	@Test
	public void posLengthRepair() throws ParseException, IOException {
		testTransformation("PosLength", "Repair", 15, 14);
	}

	@Test
	public void routeSensorRepair() throws ParseException, IOException {
		testTransformation("RouteSensor", "Repair", 12, 11);
	}

	@Test
	public void signalNeighborRepair() throws ParseException, IOException {
		testTransformation("SignalNeighbor", "Repair", 1, 0);
	}

	@Test
	public void switchSensorRepair() throws ParseException, IOException {
		testTransformation("SwitchSensor", "Repair", 4, 3);
	}

}
