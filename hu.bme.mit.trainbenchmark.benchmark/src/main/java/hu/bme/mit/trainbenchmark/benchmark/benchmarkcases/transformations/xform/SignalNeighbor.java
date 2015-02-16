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
package hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.xform;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.Transformation;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.TransformationDefinition;
import hu.bme.mit.trainbenchmark.benchmark.driver.DatabaseDriver;
import hu.bme.mit.trainbenchmark.benchmark.util.BenchmarkResult;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;

import java.io.IOException;
import java.util.List;

public class SignalNeighbor extends TransformationDefinition {

	public SignalNeighbor(final BenchmarkResult bmr, final DatabaseDriver driver, final List<? extends Object> invalids) {
		super(bmr, driver);
		this.invalids = invalids;
	}

	@Override
	protected void lhs() throws IOException {
		itemsToModify = Transformation.pickRandom(nElemToModify, invalids);
	}

	@Override
	protected void rhs() throws IOException {
		for (final Object object : itemsToModify) {
			driver.deleteAllOutgoingEdges(object, ModelConstants.ROUTE_EXIT);
		}
	}

}
