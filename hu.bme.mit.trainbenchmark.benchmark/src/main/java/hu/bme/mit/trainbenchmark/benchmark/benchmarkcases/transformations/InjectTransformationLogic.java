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
package hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations;

import static hu.bme.mit.trainbenchmark.constants.Query.CONNECTEDSEGMENTS;
import static hu.bme.mit.trainbenchmark.constants.Query.POSLENGTH;
import static hu.bme.mit.trainbenchmark.constants.Query.ROUTESENSOR;
import static hu.bme.mit.trainbenchmark.constants.Query.SEMAPHORENEIGHBOR;
import static hu.bme.mit.trainbenchmark.constants.Query.SWITCHSENSOR;
import static hu.bme.mit.trainbenchmark.constants.Query.SWITCHSET;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayModelConstants.ROUTE;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayModelConstants.SEGMENT;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayModelConstants.SWITCH;
import hu.bme.mit.trainbenchmark.constants.Query;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Ordering;

public class InjectTransformationLogic<M, T> extends TransformationLogic<M, T, T> {

	protected InjectTransformationLogic(final Comparator<?> comparator) {
		super(comparator);
	}

	protected static Map<Query, String> VERTEX_TYPES = ImmutableMap.<Query, String> builder() //
			.put(POSLENGTH, SEGMENT) //
			.put(ROUTESENSOR, ROUTE) //
			.put(SEMAPHORENEIGHBOR, ROUTE) //
			.put(SWITCHSENSOR, SWITCH) //
			.put(SWITCHSET, SWITCH) //
			.put(CONNECTEDSEGMENTS, SEGMENT) //
			.build();

	@Override
	protected void lhs(final Collection<M> currentMatches) throws Exception {
		final String vertexType = VERTEX_TYPES.get(bc.getQuery());
		candidatesToModify = driver.collectVertices(vertexType);
	}

	@Override
	protected List<T> copyAndSort() {
		final Ordering<T> ordering = Ordering.from(comparator);
		final List<T> sortedMatches = ordering.sortedCopy(candidatesToModify);
		return sortedMatches;
	}

}
