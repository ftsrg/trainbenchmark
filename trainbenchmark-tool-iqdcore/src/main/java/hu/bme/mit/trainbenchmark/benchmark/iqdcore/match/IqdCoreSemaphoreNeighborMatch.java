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
package hu.bme.mit.trainbenchmark.benchmark.iqdcore.match;

import hu.bme.mit.trainbenchmark.benchmark.matches.SemaphoreNeighborMatch;
import scala.collection.immutable.Vector;

public class IqdCoreSemaphoreNeighborMatch extends IqdCoreMatch implements SemaphoreNeighborMatch {

	public IqdCoreSemaphoreNeighborMatch(final Vector<Object> qs) {
		super(qs);
	}

	@Override
	public Long getSemaphore() {
		return (Long) qs.apply(0);
	}

	@Override
	public Long getRoute1() {
		return (Long) qs.apply(1);
	}

	@Override
	public Long getRoute2() {
		return (Long) qs.apply(2);
	}

	@Override
	public Long getSensor1() {
		return (Long) qs.apply(3);
	}

	@Override
	public Long getSensor2() {
		return (Long) qs.apply(4);
	}

	@Override
	public Long getTe1() {
		return (Long) qs.apply(5);
	}

	@Override
	public Long getTe2() {
		return (Long) qs.apply(6);
	}

}
