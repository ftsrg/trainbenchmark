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

package hu.bme.mit.trainbenchmark.benchmark.scenarios;

public class ScenarioFactory {

	public static Scenario<?> getScenarioLogic(final String scenario) {
		switch (scenario) {
		case "User":
			return new UserScenario();
		case "XForm":
			return new ModelXFormScenario();
		case "Batch":
			return new BatchScenario();
		default:
			throw new UnsupportedOperationException("Invalid scenario.");
		}
	}
}
