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

import hu.bme.mit.trainbenchmark.constants.Query;
import scala.collection.immutable.Vector;

public abstract class IQDCoreMatch {

	protected Vector<Object> qs;

	public IQDCoreMatch(final Vector<Object> qs2) {
		this.qs = qs2;
	}

	public abstract Long[] toArray();

	public static IQDCoreMatch createMatch(final Query query, final Vector<Object> qs) {
		switch (query) {
		case CONNECTEDSEGMENTS:
			return new IQDCoreConnectedSegmentsMatch(qs);
		case POSLENGTH:
			return new IQDCorePosLengthMatch(qs);
		case ROUTESENSOR:
			return new IQDCoreRouteSensorMatch(qs);
		case SEMAPHORENEIGHBOR:
			return new IQDCoreSemaphoreNeighborMatch(qs);
		case SWITCHSENSOR:
			return new IQDCoreSwitchSensorMatch(qs);
		case SWITCHSET:
			return new IQDCoreSwitchSetMatch(qs);
		default:
			throw new UnsupportedOperationException("Pattern not supported: " + query);
		}
	}

}
