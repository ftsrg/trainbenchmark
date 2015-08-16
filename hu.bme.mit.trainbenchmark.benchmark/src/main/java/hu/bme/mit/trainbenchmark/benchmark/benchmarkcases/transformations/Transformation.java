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
package hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations;

import hu.bme.mit.trainbenchmark.constants.ScenarioConstants;

import java.util.Collection;

public abstract class Transformation<O> {

	// As the transformations are implemented on a wide range of technologies, they may throw any exception.
	// Using "throws Exception" is generally considered bad practice in production systems, however, it is acceptable in the benchmark code.
	public abstract void rhs(Collection<O> objects) throws Exception;

	protected static boolean hasTransformation(final ScenarioConstants scenario) {
		switch (scenario) {
		case ANALYSIS:
			return false;
		case BATCH:
			return false;
		case DESCRIBE:
			return false;
		case INJECT:
			return true;
		case REPAIR:
			return true;
		default:
			throw new UnsupportedOperationException("Scenario: " + scenario);
		}
	}

}
