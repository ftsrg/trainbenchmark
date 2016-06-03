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

import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SW;

import org.apache.jena.query.QuerySolution;
import org.apache.jena.rdf.model.Resource;

import hu.bme.mit.trainbenchmark.benchmark.matches.SwitchMonitoredMatch;

public class JenaSwitchMonitoredMatch extends JenaMatch implements SwitchMonitoredMatch {

	public JenaSwitchMonitoredMatch(final QuerySolution qs) {
		super(qs);
	}

	@Override
	public Resource getSw() {
		return qs.getResource(VAR_SW);
	}

	@Override
	public Resource[] toArray() {
		return new Resource[] { getSw() };
	}

}
