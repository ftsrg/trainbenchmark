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

public abstract class BatchSimpleTest extends BatchTest {

	@Test
	public void routeEntry() throws ParseException, IOException {
		testQuery("RouteEntry", 1);
	}

	@Test
	public void routeRouteDefinition() throws ParseException, IOException {
		testQuery("RouteRouteDefinition", 50);
	}

	@Test
	public void segmentLength() throws ParseException, IOException {
		testQuery("SegmentLength", 55);
	}

	@Test
	public void switchNodes() throws ParseException, IOException {
		testQuery("SwitchNodes", 28);
	}

	@Test
	public void trackElementSensor() throws ParseException, IOException {
		testQuery("TrackElementSensor", 107);
	}

}
