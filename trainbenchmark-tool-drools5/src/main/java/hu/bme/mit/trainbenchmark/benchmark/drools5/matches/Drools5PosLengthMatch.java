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

import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT;

import org.drools.runtime.rule.Row;

import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfPosLengthMatch;
import hu.bme.mit.trainbenchmark.railway.Segment;

public class Drools5PosLengthMatch extends EmfPosLengthMatch {

	public Drools5PosLengthMatch(final Row match) {
		super((Segment) match.get(VAR_SEGMENT));
	}

}
