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
package hu.bme.mit.trainbenchmark.benchmark.sesame.matches;

import static hu.bme.mit.trainbenchmark.constants.railway.RailwayQueryConstants.VAR_SW;
import hu.bme.mit.trainbenchmark.benchmark.matches.railway.SwitchSensorMatch;

import org.openrdf.model.URI;
import org.openrdf.query.BindingSet;

public class SesameSwitchSensorMatch extends SesameMatch implements SwitchSensorMatch {

	public SesameSwitchSensorMatch(final BindingSet bs) {
		super(bs);
	}

	@Override
	public URI getSw() {
		return (URI) bs.getValue(VAR_SW);
	}

	@Override
	public URI[] toArray() {
		return new URI[] { getSw() };
	}

}
