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
package hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.repair;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SEGMENT;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SEGMENT_LENGTH;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.NegateAndAddOne;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.TransformationDefinition;

import java.io.IOException;

public class PosLength<T> extends TransformationDefinition<T> {

	@Override
	protected void lhs() throws IOException {
		elementCandidates = currentResults;
	}

	@Override
	protected void rhs() throws IOException {
		driver.updateProperties(elementsToModify, SEGMENT, SEGMENT_LENGTH, new NegateAndAddOne());
	}

}
