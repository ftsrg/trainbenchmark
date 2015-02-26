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

import hu.bme.mit.trainbenchmark.constants.ModelConstants;

import java.io.IOException;
import java.util.List;

public class SignalNeighbor<T> extends UserTransformationDefinition<T> {

	@Override
	protected void lhs() throws IOException {
		final List<T> signals = driver.collectVertices(ModelConstants.SIGNAL);
		elementsToModify = pickRandom(nElementsToModify, signals, currentResults);
	}

	@Override
	protected void rhs() throws IOException {
		for (final Object signal : elementsToModify) {
			driver.deleteAllIncomingEdges(signal, ModelConstants.ROUTE_ENTRY, ModelConstants.ROUTE);
		}
	}

}
