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

import hu.bme.mit.trainbenchmark.benchmark.analyzer.ModelAnalyzer;
import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.Metric;
import hu.bme.mit.trainbenchmark.constants.EdgeDirection;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

public abstract class ModelMetric extends Metric {

	protected static ModelAnalyzer<?> analyzer;

	protected EdgeDirection direction;

	protected boolean withOutgoingDegree;

	public ModelMetric(EdgeDirection direction) {
		this.direction = direction;
		withOutgoingDegree = (direction == EdgeDirection.OUTGOING);
	}

	public abstract void calculate();

	public void loadValue(final JsonNode root) {
		List<JsonNode> nodes = root.get("PhaseResults").findValues("Metrics");
		for (JsonNode metrics : nodes) {
			for (JsonNode metric : metrics) {
				if (metric.get("MetricName").asText().equals(getIdentifier())) {
					metricValue = metric.get("MetricValue").asDouble();
				}

			}
		}

	}

	public static ModelAnalyzer<?> getAnalyzer() {
		return analyzer;
	}

	public static void setAnalyzer(ModelAnalyzer<?> analyzer) {
		ModelMetric.analyzer = analyzer;
	}

}
