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
package hu.bme.mit.trainbenchmark.benchmark.jena.match;

import hu.bme.mit.trainbenchmark.benchmark.matches.RouteSensorMatch;
import hu.bme.mit.trainbenchmark.constants.QueryConstants;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.rdf.model.Resource;

public class JenaRouteSensorMatch extends JenaMatch implements RouteSensorMatch {

	public JenaRouteSensorMatch(final QuerySolution qs) {
		super(qs);
	}

	@Override
	public Resource getRoute() {
		return qs.getResource(QueryConstants.VAR_ROUTE);
	}

	@Override
	public Resource getSensor() {
		return qs.getResource(QueryConstants.VAR_SENSOR);
	}

	@Override
	public Resource getSwP() {
		return qs.getResource(QueryConstants.VAR_SWP);
	}

	@Override
	public Resource getSw() {
		return qs.getResource(QueryConstants.VAR_SW);
	}

	@Override
	public Resource[] toArray() {
		return new Resource[] { getRoute(), getSensor(), getSwP(), getSw() };
	}

}
