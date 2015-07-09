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

package hu.bme.mit.trainbenchmark.benchmark.neo4j.analyzer;

import hu.bme.mit.trainbenchmark.benchmark.analyzer.ModelAnalyzer;
import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.abstracts.AverageDegreeDistributionMetric;
import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.abstracts.AverageDegreeMetric;
import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.abstracts.MaximumDegreeDistributionMetric;
import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.abstracts.MaximumDegreeMetric;
import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.abstracts.NumberOfEdgesMetric;
import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.abstracts.NumberOfNodesMetric;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.analyzer.metrics.Neo4jAverageDegreeDistributionMetric;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.analyzer.metrics.Neo4jAverageDegreeMetric;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.analyzer.metrics.Neo4jMaximumDegreeDistributionMetric;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.analyzer.metrics.Neo4jMaximumDegreeMetric;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.analyzer.metrics.Neo4jNumberOfEdgesMetric;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.analyzer.metrics.Neo4jNumberOfNodesMetric;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;

public class Neo4jModelAnalyzer extends ModelAnalyzer<Neo4jDriver> {

	@Override
	public void attachConcreteMetrics(Neo4jDriver driver) {
		NumberOfNodesMetric.instance().attachConcreteMetric(
				new Neo4jNumberOfNodesMetric(driver));
		NumberOfEdgesMetric.instance().attachConcreteMetric(
				new Neo4jNumberOfEdgesMetric(driver));
		AverageDegreeMetric.instance().attachConcreteMetric(
				new Neo4jAverageDegreeMetric(driver));
		MaximumDegreeMetric.instance().attachConcreteMetric(
				new Neo4jMaximumDegreeMetric(driver));
		AverageDegreeDistributionMetric
				.instance()
				.attachConcreteMetric(
						new Neo4jAverageDegreeDistributionMetric(
								driver));
		MaximumDegreeDistributionMetric
				.instance()
				.attachConcreteMetric(
						new Neo4jMaximumDegreeDistributionMetric(
								driver));

	}
}
