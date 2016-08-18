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

import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_ROUTE1;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_ROUTE2;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEMAPHORE;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SENSOR1;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SENSOR2;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_TE1;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_TE2;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.query.BindingSet;

import hu.bme.mit.trainbenchmark.benchmark.matches.SemaphoreNeighborMatch;

public class Rdf4jSemaphoreNeighborMatch extends Rdf4jMatch implements SemaphoreNeighborMatch {

	public Rdf4jSemaphoreNeighborMatch(final BindingSet bs) {
		super(bs);
	}

	@Override
	public IRI getSemaphore() {
		return (IRI) bs.getValue(VAR_SEMAPHORE);
	}

	@Override
	public IRI getRoute1() {
		return (IRI) bs.getValue(VAR_ROUTE1);
	}

	@Override
	public IRI getRoute2() {
		return (IRI) bs.getValue(VAR_ROUTE2);
	}

	@Override
	public IRI getSensor1() {
		return (IRI) bs.getValue(VAR_SENSOR1);
	}

	@Override
	public IRI getSensor2() {
		return (IRI) bs.getValue(VAR_SENSOR2);
	}

	@Override
	public IRI getTe1() {
		return (IRI) bs.getValue(VAR_TE1);
	}

	@Override
	public IRI getTe2() {
		return (IRI) bs.getValue(VAR_TE2);
	}

}
