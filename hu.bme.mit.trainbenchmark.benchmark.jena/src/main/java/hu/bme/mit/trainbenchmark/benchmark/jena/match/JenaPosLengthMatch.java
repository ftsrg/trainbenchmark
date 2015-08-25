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
package hu.bme.mit.trainbenchmark.benchmark.jena.match;

import static hu.bme.mit.trainbenchmark.constants.railway.RailwayQueryConstants.VAR_LENGTH;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayQueryConstants.VAR_SEGMENT;
import hu.bme.mit.trainbenchmark.benchmark.matches.PosLengthMatch;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Resource;

public class JenaPosLengthMatch extends JenaMatch implements PosLengthMatch {

	public JenaPosLengthMatch(final QuerySolution qs) {
		super(qs);
	}

	@Override
	public Resource getSegment() {
		return qs.getResource(VAR_SEGMENT);
	}

	public Literal getLength() {
		return qs.getLiteral(VAR_LENGTH);
	}

	@Override
	public Resource[] toArray() {
		return new Resource[] { getSegment() };
	}

}
