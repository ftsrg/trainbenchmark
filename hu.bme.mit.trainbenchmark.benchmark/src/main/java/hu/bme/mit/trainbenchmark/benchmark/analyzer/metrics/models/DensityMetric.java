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

package hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.models;

import static hu.bme.mit.trainbenchmark.constants.EdgeDirection.BOTH;
import hu.bme.mit.trainbenchmark.constants.EdgeDirection;

public class DensityMetric extends ModelMetric {

	public DensityMetric(EdgeDirection direction) {
		super(direction);
	}

	@Override
	public void calculate() {
		double numOfNodes = analyzer.getNumberOfNodes(withOutgoingDegree);
		metricValue = analyzer.getNumberOfEdges() / numOfNodes;
		metricValue /= (numOfNodes - 1);
	}

	@Override
	protected String getIdentifier() {
		if (direction == BOTH) {
			return "Density";
		} else {
			return "DensityWithOutgoing";
		}
	}
}
