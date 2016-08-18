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

import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT1;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT2;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT3;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT4;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT5;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT6;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SENSOR;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.query.BindingSet;

import hu.bme.mit.trainbenchmark.benchmark.matches.ConnectedSegmentsMatch;

public class Rdf4jConnectedSegmentsMatch extends Rdf4jMatch implements ConnectedSegmentsMatch {

	public Rdf4jConnectedSegmentsMatch(final BindingSet bs) {
		super(bs);
	}

	@Override
	public IRI getSensor() {
		return (IRI) bs.getValue(VAR_SENSOR);
	}

	@Override
	public IRI getSegment1() {
		return (IRI) bs.getValue(VAR_SEGMENT1);
	}

	@Override
	public IRI getSegment2() {
		return (IRI) bs.getValue(VAR_SEGMENT2);
	}

	@Override
	public IRI getSegment3() {
		return (IRI) bs.getValue(VAR_SEGMENT3);
	}

	@Override
	public IRI getSegment4() {
		return (IRI) bs.getValue(VAR_SEGMENT4);
	}

	@Override
	public IRI getSegment5() {
		return (IRI) bs.getValue(VAR_SEGMENT5);
	}

	@Override
	public IRI getSegment6() {
		return (IRI) bs.getValue(VAR_SEGMENT6);
	}

}
