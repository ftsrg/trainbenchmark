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

package hu.bme.mit.trainbenchmark.benchmark.sql.analyzer;

import hu.bme.mit.trainbenchmark.benchmark.analyzer.ModelAnalyzer;
import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.abstracts.NumberOfEdgesMetric;
import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.abstracts.NumberOfNodesMetric;
import hu.bme.mit.trainbenchmark.benchmark.sql.analyzer.metrics.SQLNumberOfEdgesMetric;
import hu.bme.mit.trainbenchmark.benchmark.sql.analyzer.metrics.SQLNumberOfNodesMetric;
import hu.bme.mit.trainbenchmark.benchmark.sql.driver.SQLDriver;

public class SQLModelAnalyzer extends ModelAnalyzer<SQLDriver> {

	@Override
	public void attachConcreteMetrics(SQLDriver driver) {
		NumberOfNodesMetric.instance().attachConcreteMetric(
				new SQLNumberOfNodesMetric(driver));
		NumberOfEdgesMetric.instance().attachConcreteMetric(
				new SQLNumberOfEdgesMetric(driver));

	}
}
