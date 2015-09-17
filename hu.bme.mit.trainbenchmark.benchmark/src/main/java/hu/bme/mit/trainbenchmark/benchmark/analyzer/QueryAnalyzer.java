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

package hu.bme.mit.trainbenchmark.benchmark.analyzer;

import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.Metric;
import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.queries.ConstantsMetric;
import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.queries.FiltersMetric;
import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.queries.NavigationsMetric;
import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.queries.QueryMetric;
import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.queries.VariablesMetric;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.publisher.TrainBenchmarkCaseDescriptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import eu.mondo.sam.core.metrics.BenchmarkMetric;

public abstract class QueryAnalyzer<D extends Driver<?>> extends Analyzer<D> {

	protected NavigationsMetric navigationsMetric;
	protected FiltersMetric filtersMetric;
	protected VariablesMetric variablesMetric;
	protected ConstantsMetric constantsMetric;

	protected Map<String, QueryMetric> queryMetrics;

	public QueryAnalyzer(D driver) {
		super(driver);
	}

	@Override
	public void calculateAll(final BenchmarkConfig benchmarkConfig,
			final TrainBenchmarkCaseDescriptor descriptor) {
		calculateMetrics();
	}

	@Override
	public void initializeMetrics() {
		if (metrics == null) {
			metrics = new ArrayList<BenchmarkMetric>();
		}
		queryMetrics = new HashMap<String, QueryMetric>();

		navigationsMetric = new NavigationsMetric();
		filtersMetric = new FiltersMetric();
		variablesMetric = new VariablesMetric();
		constantsMetric = new ConstantsMetric();

		metrics.add(navigationsMetric);
		metrics.add(filtersMetric);
		metrics.add(variablesMetric);
		metrics.add(constantsMetric);

		for (BenchmarkMetric m : metrics) {
			((Metric) m).initName();
			queryMetrics.put(m.getMetricName(), (QueryMetric) m);
		}
	}

	@Override
	public void resetMetrics() {
		for (Entry<String, QueryMetric> e : queryMetrics.entrySet()) {
			e.getValue().reset();
		}

	}

	public QueryMetric getNavigationsMetric() {
		return queryMetrics.get(navigationsMetric.getMetricName());
	}

	public QueryMetric getFiltersMetric() {
		return queryMetrics.get(filtersMetric.getMetricName());
	}

}
