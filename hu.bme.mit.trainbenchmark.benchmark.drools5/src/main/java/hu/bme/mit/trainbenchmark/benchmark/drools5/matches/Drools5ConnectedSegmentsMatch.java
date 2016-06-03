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
package hu.bme.mit.trainbenchmark.benchmark.drools5.matches;

import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.constants.QueryConstants;
import hu.bme.mit.trainbenchmark.railway.Segment;
import hu.bme.mit.trainbenchmark.railway.Sensor;

import org.drools.runtime.rule.Row;

public class Drools5ConnectedSegmentsMatch extends EmfConnectedSegmentsMatch {

	public Drools5ConnectedSegmentsMatch(final Row match) {
		super((Sensor) match.get(QueryConstants.VAR_SENSOR), (Segment) match.get(QueryConstants.VAR_SEGMENT1), (Segment) match
				.get(QueryConstants.VAR_SEGMENT2), (Segment) match.get(QueryConstants.VAR_SEGMENT3), (Segment) match
				.get(QueryConstants.VAR_SEGMENT4), (Segment) match.get(QueryConstants.VAR_SEGMENT5), (Segment) match
				.get(QueryConstants.VAR_SEGMENT6));
	}

}
