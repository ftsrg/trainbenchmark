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
package hu.bme.mit.trainbenchmark.benchmark.ingraph.match;

import hu.bme.mit.trainbenchmark.benchmark.matches.SemaphoreNeighborMatch;
import scala.collection.immutable.Map;
import scala.collection.immutable.Vector;

public class IngraphSemaphoreNeighborMatch extends IngraphMatch implements SemaphoreNeighborMatch {

	public IngraphSemaphoreNeighborMatch(final Map<Object, Object> qs) {
		super(qs);
	}

	@Override
	public Long getSemaphore() {
		return (Long) qs.get("semaphore");
	}

	@Override
	public Long getRoute1() {
		return (Long) qs.get("route1");
	}

	@Override
	public Long getRoute2() {
		return (Long) qs.get("route2");
	}

	@Override
	public Long getSensor1() {
		return (Long) qs.get("sensor1");
	}

	@Override
	public Long getSensor2() {
		return (Long) qs.get("sensor2");
	}

	@Override
	public Long getTe1() {
		return (Long) qs.get("te1");
	}

	@Override
	public Long getTe2() {
		return (Long) qs.get("te2");
	}

}
