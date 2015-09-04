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

import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.models.ModelMetric;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.publisher.AnalysisFilenameFactory;
import hu.bme.mit.trainbenchmark.benchmark.publisher.TrainBenchmarkCaseDescriptor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import eu.mondo.sam.core.metrics.BenchmarkMetric;

public abstract class Analyzer<D extends Driver<?>> {

	protected D driver;

	protected ArrayList<BenchmarkMetric> metrics;

	public Analyzer(D driver) {
		this.driver = driver;
	}

	public void calculateAll(final BenchmarkConfig benchmarkConfig,
			final TrainBenchmarkCaseDescriptor descriptor) {
		AnalysisFilenameFactory factory = new AnalysisFilenameFactory(descriptor);
		File file = new File(benchmarkConfig.getAnalysisPath() + factory.getFilename() + ".json");
		if (file.exists()) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				JsonNode root = mapper.readTree(file);
				for (BenchmarkMetric m : metrics) {
					((ModelMetric) m).loadValue(root);
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		} else {
			calculateMetrics();
			for (BenchmarkMetric m : metrics) {
				((ModelMetric) m).calculate();
			}
		}
	}

	protected abstract void calculateMetrics();

	public abstract void initializeMetrics();

	public abstract void resetMetrics();

	public ArrayList<BenchmarkMetric> getMetrics() {
		return metrics;
	}

	public void setMetrics(ArrayList<BenchmarkMetric> metrics) {
		this.metrics = metrics;
	}

	public D getDriver() {
		return driver;
	}

	public void setDriver(D driver) {
		this.driver = driver;
	}

}
