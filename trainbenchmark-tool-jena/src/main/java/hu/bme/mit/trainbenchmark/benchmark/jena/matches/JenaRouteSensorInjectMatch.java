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
package hu.bme.mit.trainbenchmark.benchmark.jena.matches;

import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_ROUTE;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SENSOR;

import org.apache.jena.query.QuerySolution;
import org.apache.jena.rdf.model.Resource;

import hu.bme.mit.trainbenchmark.benchmark.matches.RouteSensorInjectMatch;

public class JenaRouteSensorInjectMatch extends JenaMatch implements RouteSensorInjectMatch {

	public JenaRouteSensorInjectMatch(final QuerySolution qs) {
		super(qs);
	}

	@Override
	public Resource getRoute() {
		return qs.getResource(VAR_ROUTE);
	}

	@Override
	public Resource getSensor() {
		return qs.getResource(VAR_SENSOR);
	}

}
