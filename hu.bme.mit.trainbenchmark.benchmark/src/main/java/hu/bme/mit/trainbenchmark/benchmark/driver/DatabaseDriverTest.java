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
package hu.bme.mit.trainbenchmark.benchmark.driver;

import static org.junit.Assert.assertEquals;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.SetToZero;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;

import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public abstract class DatabaseDriverTest {

	protected DatabaseDriver driver;

	public void init() throws IOException {
	}

	public void destroy() throws IOException {
		driver.destroy();
	}

	@Before
	public void before() throws IOException {
		init();
		driver.beginTransaction();
	}

	@After
	public void after() throws IOException {
		driver.finishTransaction();
		destroy();
	}

	@Test
	public void testCollectVertices() throws IOException {
		final List<? extends Object> routes = driver.collectVertices(ModelConstants.ROUTE);
		assertEquals(2, routes.size());
	}

	@Test
	public void testUpdateProperty() throws IOException {
		final List<? extends Object> segments = driver.collectVertices(ModelConstants.SEGMENT);
		driver.updateProperties(segments, ModelConstants.SEGMENT, ModelConstants.LENGTH, new SetToZero());

		final List<? extends Object> segments2 = driver.collectVertices(ModelConstants.SEGMENT);
		for (final Object segment : segments2) {
			assertEquals(0, extractLength(segment));
			break;
		}
	}

	@Test
	public void testDeleteOutgoingEdges() throws IOException {
		final List<? extends Object> routes = driver.collectVertices(ModelConstants.ROUTE);
		driver.deleteAllOutgoingEdges(routes, ModelConstants.ROUTE, ModelConstants.DEFINED_BY);
	}

	@Test
	public void testDeleteIncomingEdges() throws IOException {
		final List<? extends Object> routes = driver.collectVertices(ModelConstants.SENSOR);
		driver.deleteIncomingEdge(routes, ModelConstants.TRACKELEMENT, ModelConstants.SENSOR_EDGE);
	}

	protected abstract long extractLength(Object segment) throws IOException;
}
