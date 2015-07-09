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

package hu.bme.mit.trainbenchmark.benchmark.neo4j.analyzer.metrics;

import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.MetricToken;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;

import java.util.Iterator;

import org.neo4j.graphdb.Node;

public class Neo4jMaximumDegreeMetric extends Neo4jConcreteMetric {

	protected double maximumDegree;

	public Neo4jMaximumDegreeMetric(Neo4jDriver driver) {
		super(driver);
	}

	@Override
	public void calculate(MetricToken token) {
		maximumDegree = 0;

		beginTransaction();
		Iterator<Node> iterator = getNodesIterator();
		while (iterator.hasNext()) {
			double currentDegree = iterator.next().getDegree();
			if (currentDegree > maximumDegree) {
				maximumDegree = currentDegree;
			}
		}
		token.setMaximumDegree(maximumDegree);
		finishTransaction();

	}

	@Override
	public String getValue() {
		return Double.toString(maximumDegree);
	}

}
