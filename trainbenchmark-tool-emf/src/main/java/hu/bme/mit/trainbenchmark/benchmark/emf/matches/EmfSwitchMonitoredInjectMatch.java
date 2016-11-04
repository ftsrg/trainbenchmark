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

import hu.bme.mit.trainbenchmark.benchmark.matches.SwitchMonitoredInjectMatch;
import hu.bme.mit.trainbenchmark.railway.Switch;

public class EmfSwitchMonitoredInjectMatch extends EmfMatch implements SwitchMonitoredInjectMatch {

	protected final Switch sw;

	public EmfSwitchMonitoredInjectMatch(final Switch sw) {
		super();
		this.sw = sw;
	}

	@Override
	public Switch getSw() {
		return sw;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sw == null) ? 0 : sw.hashCode());
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
		final EmfSwitchMonitoredInjectMatch other = (EmfSwitchMonitoredInjectMatch) obj;
		if (sw == null) {
			if (other.sw != null)
				return false;
		} else if (!sw.equals(other.sw))
			return false;
		return true;
	}
	
}
