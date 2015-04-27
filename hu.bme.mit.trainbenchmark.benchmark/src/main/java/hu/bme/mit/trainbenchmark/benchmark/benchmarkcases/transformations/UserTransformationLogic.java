/*******************************************************************************
 * Copyright (c) 2010-2015, Gabor Szarnyas, Benedek Izso, Istvan Rath and Daniel Varro
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

import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.constants.Query;

import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Ordering;

public class UserTransformationLogic<M, T> extends TransformationLogic<M, T, T> {

	protected UserTransformationLogic(final Comparator comparator) {
		super(comparator);
	}

	protected static Map<Query, String> VERTEX_TYPES = ImmutableMap.of( //
			Query.POSLENGTH, ModelConstants.SEGMENT, //
			Query.ROUTESENSOR, ModelConstants.ROUTE, //
			Query.SEMAPHORENEIGHBOR, ModelConstants.ROUTE, //
			Query.SWITCHSENSOR, ModelConstants.SWITCH, //
			Query.SWITCHSET, ModelConstants.SWITCH //
			);

	@Override
	protected void lhs(final Collection<M> currentMatches) throws IOException {
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
