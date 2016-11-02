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

import hu.bme.mit.trainbenchmark.benchmark.matches.SwitchSetMatch;
import scala.collection.immutable.Map;

public class IngraphSwitchSetMatch extends IngraphMatch implements SwitchSetMatch {

	public IngraphSwitchSetMatch(final Map<Object, Object> qs) {
		super(qs);
	}

	@Override
	public Long getSemaphore() {
		return (Long) qs.get(0);
	}

	@Override
	public Long getRoute() {
		return (Long) qs.get(1);
	}

	@Override
	public Long getSwP() {
		return (Long) qs.get(2);
	}

	@Override
	public Long getSw() {
		return (Long) qs.get(3);
	}

	public String getPosition() {
		return (String) qs.get(4);
	}

	public String getCurrentPosition() {
		return (String) qs.get(5);
	}

}
