/*******************************************************************************
 * Copyright (c) 2010-2014, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/

package hu.bme.mit.trainbenchmark.benchmark.java.benchmarkcases;

import java.util.ArrayList;
import java.util.List;

import Concept.Switch;

public class SwitchSensor extends JavaBenchmarkCase<Switch> {

	@Override
	protected List<Switch> check() {
		results = new ArrayList<>();

		for (final Object eObject : pack.getContains()) {
			if (eObject instanceof Switch) {
				final Switch aSwitch = (Switch) eObject;
				if (aSwitch.getTrackElement_sensor().isEmpty()) {
					results.add(aSwitch);
				}
			}
		}

		return results;
	}
	
}
