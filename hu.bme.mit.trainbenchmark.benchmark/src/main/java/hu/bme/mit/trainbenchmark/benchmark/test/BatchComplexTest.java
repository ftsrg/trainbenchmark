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

public abstract class BatchComplexTest extends BatchTest {

	@Test
	public void posLength() throws ParseException, IOException {
		testQuery("PosLength", 4);
	}

	@Test
	public void routeSensor() throws ParseException, IOException {
		testQuery("RouteSensor", 2);
	}

	@Test
	public void signalNeighbor() throws ParseException, IOException {
		testQuery("SignalNeighbor", 1);
	}

	@Test
	public void switchSensor() throws ParseException, IOException {
		testQuery("SwitchSensor", 7);
	}

}
