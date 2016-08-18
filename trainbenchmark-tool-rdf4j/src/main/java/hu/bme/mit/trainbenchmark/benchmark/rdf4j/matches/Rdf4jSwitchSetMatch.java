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
package hu.bme.mit.trainbenchmark.benchmark.rdf4j.matches;

import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_CURRENTPOSITION;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_POSITION;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_ROUTE;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEMAPHORE;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SW;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SWP;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.query.BindingSet;

import hu.bme.mit.trainbenchmark.benchmark.matches.SwitchSetMatch;

public class Rdf4jSwitchSetMatch extends Rdf4jMatch implements SwitchSetMatch {

	public Rdf4jSwitchSetMatch(final BindingSet bs) {
		super(bs);
	}

	@Override
	public IRI getSemaphore() {
		return (IRI) bs.getValue(VAR_SEMAPHORE);
	}

	@Override
	public IRI getRoute() {
		return (IRI) bs.getValue(VAR_ROUTE);
	}

	@Override
	public IRI getSwP() {
		return (IRI) bs.getValue(VAR_SWP);
	}

	@Override
	public IRI getSw() {
		return (IRI) bs.getValue(VAR_SW);
	}

	public Value getPosition() {
		return bs.getValue(VAR_POSITION);
	}

	public Value getCurrentPosition() {
		return bs.getValue(VAR_CURRENTPOSITION);
	}

}
