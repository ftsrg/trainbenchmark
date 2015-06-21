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
package hu.bme.mit.trainbenchmark.benchmark.drools6.matches;

import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SW;
import hu.bme.mit.trainbenchmark.emf.matches.EMFSwitchSensorMatch;
import hu.bme.mit.trainbenchmark.railway.Switch;

import org.kie.api.runtime.rule.Row;

public class Drools6SwitchSensorMatch extends EMFSwitchSensorMatch {

	public Drools6SwitchSensorMatch(final Row match) {
		super((Switch) match.get(VAR_SW));
	}

}
