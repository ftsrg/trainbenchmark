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

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ROUTE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SEGMENT;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCH;
import static hu.bme.mit.trainbenchmark.constants.Query.CONNECTEDSEGMENTS;
import static hu.bme.mit.trainbenchmark.constants.Query.POSLENGTH;
import static hu.bme.mit.trainbenchmark.constants.Query.ROUTESENSOR;
import static hu.bme.mit.trainbenchmark.constants.Query.SEMAPHORENEIGHBOR;
import static hu.bme.mit.trainbenchmark.constants.Query.SWITCHSENSOR;
import static hu.bme.mit.trainbenchmark.constants.Query.SWITCHSET;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Ordering;

import hu.bme.mit.trainbenchmark.constants.Query;

public class InjectTransformationLogic<TMatch, TElement> extends TransformationLogic<TMatch, TElement, TElement> {

	protected InjectTransformationLogic(final Comparator<TElement> comparator) {
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
	protected void lhs(final Collection<TMatch> currentMatches) throws Exception {
		final String vertexType = VERTEX_TYPES.get(bc.getQuery());
		candidatesToModify = driver.collectVertices(vertexType);
	}

	@Override
	protected List<TElement> copyAndSort() {
		final Ordering<TElement> ordering = Ordering.from(comparator);
		final List<TElement> sortedMatches = ordering.sortedCopy(candidatesToModify);
		return sortedMatches;
	}

}
