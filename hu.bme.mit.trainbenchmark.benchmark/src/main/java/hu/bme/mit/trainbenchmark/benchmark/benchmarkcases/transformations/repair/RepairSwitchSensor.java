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

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.TRACKELEMENT;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR_EDGE;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.TransformationDefinition;

import java.io.IOException;

public class RepairSwitchSensor<T> extends TransformationDefinition<T> {

	@Override
	protected void lhs() throws IOException {
		elementCandidates = currentResults;
	}

	@Override
	protected void rhs() throws IOException {
		driver.insertVertexWithEdge(elementsToModify, TRACKELEMENT, SENSOR, SENSOR_EDGE);
	}

}
