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

import hu.bme.mit.trainbenchmark.benchmark.matches.SwitchSetMatch;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Semaphore;
import hu.bme.mit.trainbenchmark.railway.Switch;
import hu.bme.mit.trainbenchmark.railway.SwitchPosition;

public class EmfSwitchSetMatch extends EmfMatch implements SwitchSetMatch {

	protected final Semaphore semaphore;
	protected final Route route;
	protected final SwitchPosition swP;
	protected final Switch sw;
	
	public EmfSwitchSetMatch(final Semaphore semaphore, final Route route, final SwitchPosition swP, final Switch sw) {
		super();
		this.semaphore = semaphore;
		this.route = route;
		this.swP = swP;
		this.sw = sw;
	}

	@Override
	public Semaphore getSemaphore() {
		return semaphore;
	}

	@Override
	public Route getRoute() {
		return route;
	}

	@Override
	public SwitchPosition getSwP() {
		return swP;
	}

	@Override
	public Switch getSw() {
		return sw;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((route == null) ? 0 : route.hashCode());
		result = prime * result + ((semaphore == null) ? 0 : semaphore.hashCode());
		result = prime * result + ((sw == null) ? 0 : sw.hashCode());
		result = prime * result + ((swP == null) ? 0 : swP.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final EmfSwitchSetMatch other = (EmfSwitchSetMatch) obj;
		if (route == null) {
			if (other.route != null)
				return false;
		} else if (!route.equals(other.route))
			return false;
		if (semaphore == null) {
			if (other.semaphore != null)
				return false;
		} else if (!semaphore.equals(other.semaphore))
			return false;
		if (sw == null) {
			if (other.sw != null)
				return false;
		} else if (!sw.equals(other.sw))
			return false;
		if (swP == null) {
			if (other.swP != null)
				return false;
		} else if (!swP.equals(other.swP))
			return false;
		return true;
	}

}
