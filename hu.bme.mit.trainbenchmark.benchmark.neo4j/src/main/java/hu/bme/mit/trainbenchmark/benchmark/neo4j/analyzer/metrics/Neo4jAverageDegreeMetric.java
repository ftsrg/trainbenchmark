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

public class Neo4jAverageDegreeMetric extends Neo4jConcreteMetric {

	protected double averageDegree;

	protected double sumDegree;

	public Neo4jAverageDegreeMetric(Neo4jDriver driver) {
		super(driver);
	}

	@Override
	public void calculate(final MetricToken token) {
		beginTransaction();
		Iterator<Node> iterator = getNodesIterator();
		while (iterator.hasNext()) {
			sumDegree += iterator.next().getDegree();
		}
		averageDegree = sumDegree / token.getNumberOfNodes();
		finishTransaction();
		token.setAverageDegree(averageDegree);

	}

	@Override
	public String getValue() {
		return Double.toString(averageDegree);
	}

}
