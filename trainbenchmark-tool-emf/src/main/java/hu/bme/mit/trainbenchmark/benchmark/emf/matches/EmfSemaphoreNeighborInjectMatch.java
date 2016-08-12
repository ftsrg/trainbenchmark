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
package hu.bme.mit.trainbenchmark.benchmark.emf.matches;

import hu.bme.mit.trainbenchmark.benchmark.matches.SemaphoreNeighborInjectMatch;
import hu.bme.mit.trainbenchmark.railway.Route;

public class EmfSemaphoreNeighborInjectMatch extends EmfMatch implements SemaphoreNeighborInjectMatch {

	protected final Route route;

	public EmfSemaphoreNeighborInjectMatch(final Route route) {
		super();
		this.route = route;
	}

	public Route getRoute() {
		return route;
	}

}
