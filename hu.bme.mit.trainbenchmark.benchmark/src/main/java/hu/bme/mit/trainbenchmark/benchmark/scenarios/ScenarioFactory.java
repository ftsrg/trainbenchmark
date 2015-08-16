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

package hu.bme.mit.trainbenchmark.benchmark.scenarios;

import hu.bme.mit.trainbenchmark.constants.ScenarioConstants;

public class ScenarioFactory {

	public static Scenario<?> getScenario(final ScenarioConstants scenarioName) {
		switch (scenarioName) {
		case INJECT:
			return new InjectScenario();
		case REPAIR:
			return new RepairScenario();
		case BATCH:
			return new BatchScenario();
		default:
			throw new UnsupportedOperationException("Invalid scenario.");
		}
	}
}
