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
package hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.user;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.Transformation;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.TransformationDefinition;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;

import java.io.IOException;
import java.util.List;

public class SignalNeighbor extends TransformationDefinition {

	@Override
	protected void lhs() throws IOException {
		final List<? extends Object> routes = driver.collectVertices(ModelConstants.ROUTE);
		elementsToModify = Transformation.pickRandom(nElementsToModify, routes);
	}

	@Override
	protected void rhs() throws IOException {
		for (final Object route : elementsToModify) {
			driver.deleteOutgoingEdge(route, ModelConstants.ROUTE, ModelConstants.ROUTE_ENTRY);
		}
	}

}
