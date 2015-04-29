/*******************************************************************************
 * Copyright (c) 2010-2015, Gabor Szarnyas, Benedek Izso, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.benchmark.sesame.matches;

import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_CURRENTPOSITION;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_POSITION;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_ROUTE;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEMAPHORE;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SW;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SWP;
import hu.bme.mit.trainbenchmark.benchmark.matches.SwitchSetMatch;

import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.query.BindingSet;

public class SesameSwitchSetMatch extends SesameMatch implements SwitchSetMatch {

	public SesameSwitchSetMatch(final BindingSet bs) {
		super(bs);
	}

	@Override
	public URI getSemaphore() {
		return (URI) bs.getValue(VAR_SEMAPHORE);
	}

	@Override
	public URI getRoute() {
		return (URI) bs.getValue(VAR_ROUTE);
	}

	@Override
	public URI getSwP() {
		return (URI) bs.getValue(VAR_SWP);
	}

	@Override
	public URI getSw() {
		return (URI) bs.getValue(VAR_SW);
	}

	public Value getPosition() {
		return bs.getValue(VAR_POSITION);
	}

	public Value getCurrentPosition() {
		return bs.getValue(VAR_CURRENTPOSITION);
	}

	@Override
	public URI[] toArray() {
		return new URI[] { getSemaphore(), getRoute(), getSwP(), getSw() };
	}

}
