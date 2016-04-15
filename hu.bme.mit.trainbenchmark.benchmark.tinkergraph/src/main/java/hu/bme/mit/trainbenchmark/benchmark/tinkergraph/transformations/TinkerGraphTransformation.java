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
package hu.bme.mit.trainbenchmark.benchmark.tinkergraph.transformations;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.Transformation;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.driver.TinkerGraphDriver;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.constants.ScenarioEnum;

public abstract class TinkerGraphTransformation<TObject> extends Transformation<TObject, TinkerGraphDriver> {

	protected TinkerGraphTransformation(final TinkerGraphDriver driver) {
		super(driver);
	}

	public static Transformation<?, ?> newInstance(final TinkerGraphDriver driver, final RailwayQuery query, final ScenarioEnum scenario) {
		throw new UnsupportedOperationException("Query: " + query.toString() + ", scenario: " + scenario);
	}
}
