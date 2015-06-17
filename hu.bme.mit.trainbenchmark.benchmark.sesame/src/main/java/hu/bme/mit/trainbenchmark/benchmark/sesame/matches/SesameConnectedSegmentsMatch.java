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

import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT1;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT2;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT3;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT4;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT5;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT6;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SENSOR;
import hu.bme.mit.trainbenchmark.benchmark.matches.ConnectedSegmentsMatch;

import org.openrdf.model.URI;
import org.openrdf.query.BindingSet;

public class SesameConnectedSegmentsMatch extends SesameMatch implements ConnectedSegmentsMatch {

	public SesameConnectedSegmentsMatch(final BindingSet bs) {
		super(bs);
	}

	@Override
	public URI getSensor() {
		return (URI) bs.getValue(VAR_SENSOR);
	}

	@Override
	public URI getSegment1() {
		return (URI) bs.getValue(VAR_SEGMENT1);
	}

	@Override
	public URI getSegment2() {
		return (URI) bs.getValue(VAR_SEGMENT2);
	}

	@Override
	public URI getSegment3() {
		return (URI) bs.getValue(VAR_SEGMENT3);
	}

	@Override
	public URI getSegment4() {
		return (URI) bs.getValue(VAR_SEGMENT4);
	}

	@Override
	public URI getSegment5() {
		return (URI) bs.getValue(VAR_SEGMENT5);
	}

	@Override
	public URI getSegment6() {
		return (URI) bs.getValue(VAR_SEGMENT6);
	}

	@Override
	public URI[] toArray() {
		return new URI[] { getSensor(), getSegment1(), getSegment2(), getSegment3(), getSegment4(), getSegment5(), getSegment6() };
	}

}
