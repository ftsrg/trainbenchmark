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

import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;

import org.neo4j.graphdb.Node;

public abstract class Neo4jDegreeDistributionMetric extends Neo4jConcreteMetric {

	protected double numberOfDegree;

	protected double degreeDistribution;

	public Neo4jDegreeDistributionMetric(Neo4jDriver driver) {
		super(driver);
	}

	protected void calculateDegreeDistribution(final double degree,
			final int numberOfNodes) {
		beginTransaction();
		BigDecimal roundedDegree = new BigDecimal(degree);
		roundedDegree = roundedDegree.setScale(0, RoundingMode.HALF_UP);

		numberOfDegree = 0;
		Iterator<Node> iterator = getNodesIterator();
		while (iterator.hasNext()) {
			if (iterator.next().getDegree() == roundedDegree
					.intValue()) {
				numberOfDegree++;
			}
		}
		degreeDistribution = numberOfDegree / numberOfNodes;

		finishTransaction();
	}

	@Override
	public String getValue() {
		return Double.toString(degreeDistribution);
	}

}
