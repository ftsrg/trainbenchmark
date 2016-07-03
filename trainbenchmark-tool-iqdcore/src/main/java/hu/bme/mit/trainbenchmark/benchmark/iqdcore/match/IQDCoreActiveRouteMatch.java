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

import hu.bme.mit.trainbenchmark.benchmark.matches.ActiveRouteMatch;
import scala.collection.immutable.Vector;

public class IQDCoreActiveRouteMatch extends IQDCoreMatch implements ActiveRouteMatch {

	public IQDCoreActiveRouteMatch(final Vector<Object> qs) {
		super(qs);
	}

	@Override
	public Long getRoute() {
		return (Long) qs.apply(0);
	}

	@Override
	public Long[] toArray() {
		return new Long[] { getRoute() };
	}
	
	@Override
	public String toString() {
		return super.toString();
	}

}
