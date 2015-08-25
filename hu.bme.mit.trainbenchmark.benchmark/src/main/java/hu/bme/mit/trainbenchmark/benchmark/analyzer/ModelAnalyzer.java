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

import static hu.bme.mit.trainbenchmark.constants.EdgeDirection.BOTH;
import static hu.bme.mit.trainbenchmark.constants.EdgeDirection.OUTGOING;
import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.AverageClusteringCoefficientMetric;
import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.AverageDegreeDistributionMetric;
import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.AverageDegreeMetric;
import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.AverageShortestPathMetric;
import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.DensityMetric;
import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.HigherDegreeDistributionMetric;
import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.MaximumDegreeMetric;
import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.Metric;
import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.NumberOfEdgesMetric;
import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.NumberOfNodesMetric;
import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.constants.EdgeDirection;
import hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.mondo.sam.core.metrics.BenchmarkMetric;

public abstract class ModelAnalyzer<D extends Driver<?>> extends Analyzer<D> {

	protected double numberOfNodes;

	protected double numberOfEdges;

	protected double numberOfNodesWithOutgoingDegrees;

	protected double maximumDegree;

	private double averageDegree;

	private double numberOfMaximumDegree;

	private double numberOfAverageDegree;

	private double maximumOutgoingDegree;

	private double averageOutgoingDegree;

	private double numberOfMaximumOutgoingDegree;

	private double numberOfAverageOutgoingDegree;

	private double numberOfHigherDegree;

	private double numberOfHigherOutgoingDegree;

	protected AverageShortestPathMetric shortestPathMetric;

	private Map<String, List<Double>> clusteringCoefficients;

	private String ALL = "All";

	public ModelAnalyzer(D driver) {
		super(driver);
	}

	@Override
	public void initializeMetrics() {
		if (metrics == null) {
			metrics = new ArrayList<BenchmarkMetric>();
		}
		Metric.setAnalyzer(this);
		metrics.add(new NumberOfNodesMetric());
		metrics.add(new NumberOfEdgesMetric());
		metrics.add(new AverageDegreeMetric(BOTH));
		metrics.add(new MaximumDegreeMetric(BOTH));
		metrics.add(new AverageDegreeDistributionMetric(BOTH));

		metrics.add(new AverageDegreeMetric(OUTGOING));
		metrics.add(new MaximumDegreeMetric(OUTGOING));
		metrics.add(new AverageDegreeDistributionMetric(OUTGOING));

		metrics.add(new HigherDegreeDistributionMetric(BOTH));
		metrics.add(new HigherDegreeDistributionMetric(OUTGOING));

		metrics.add(new DensityMetric(BOTH));
		metrics.add(new DensityMetric(OUTGOING));

		metrics.add(new AverageClusteringCoefficientMetric());
		metrics.add(new AverageClusteringCoefficientMetric(ScheduleModelConstants.STATION));
		clusteringCoefficients = new HashMap<String, List<Double>>();

		shortestPathMetric = new AverageShortestPathMetric();
		metrics.add(shortestPathMetric);

		for (BenchmarkMetric m : metrics) {
			((Metric) m).initName();
		}
	}

	@Override
	public void resetMetrics() {
		numberOfNodes = 0;
		numberOfEdges = 0;
		numberOfNodesWithOutgoingDegrees = 0;

		maximumDegree = 0;
		numberOfMaximumDegree = 0;
		numberOfAverageDegree = 0;

		numberOfMaximumOutgoingDegree = 0;
		numberOfAverageOutgoingDegree = 0;
		numberOfHigherDegree = 0;
		numberOfHigherOutgoingDegree = 0;

		clusteringCoefficients.clear();

	}

	protected void calculateAverageDegree(final EdgeDirection direction) {
		switch (direction) {
		case BOTH:
			averageDegree = numberOfEdges / numberOfNodes;
			break;
		case OUTGOING:
			averageOutgoingDegree = numberOfEdges / numberOfNodesWithOutgoingDegrees;
			break;
		default:
			throw new UnsupportedOperationException();
		}
	}

	protected void changeMaximumDegree(final EdgeDirection direction, final double degree) {
		switch (direction) {
		case BOTH:
			maximumDegree = degree > maximumDegree ? degree : maximumDegree;
			break;
		case OUTGOING:
			maximumOutgoingDegree = degree > maximumOutgoingDegree ? degree
					: maximumOutgoingDegree;
			break;
		default:
			throw new UnsupportedOperationException();
		}
	}

	protected void changeNumberOfDegrees(final EdgeDirection direction, final double value,
			final int roundedAverage) {
		switch (direction) {
		case BOTH:
			if (value == roundedAverage) {
				numberOfAverageDegree++;
			} else if (value == maximumDegree) {
				numberOfMaximumDegree++;
			}

			if (value >= roundedAverage) {
				numberOfHigherDegree++;
			}
			break;
		case OUTGOING:
			if (value == roundedAverage) {
				numberOfAverageOutgoingDegree++;
			} else if (value == maximumOutgoingDegree) {
				numberOfMaximumOutgoingDegree++;
			}

			if (value >= roundedAverage) {
				numberOfHigherOutgoingDegree++;
			}
			break;
		default:
			throw new UnsupportedOperationException();
		}

	}

	protected int roundAverageDegree(final EdgeDirection direction) {
		BigDecimal roundedDegree = new BigDecimal(getAverageDegree(direction));
		roundedDegree = roundedDegree.setScale(0, RoundingMode.HALF_UP);
		return roundedDegree.intValue();
	}

	protected void addClusteringCoefficient(final int connectedNeighbors, final int numberOfNeighbors,
			final String type) {
		if (!clusteringCoefficients.containsKey(type)) {
			clusteringCoefficients.put(type, new ArrayList<Double>());
		}
		if (numberOfNeighbors == 1) {
			return;
		}
		double coef = connectedNeighbors / numberOfNeighbors;
		coef /= (numberOfNeighbors - 1);
		clusteringCoefficients.get(type).add(coef);

		if (!type.equals(ALL)) {
			addClusteringCoefficient(connectedNeighbors, numberOfNeighbors, ALL);
		}

	}

	protected void addClusteringCoefficient(final int connectedNeighbors, final int numberOfNeighbors) {
		addClusteringCoefficient(connectedNeighbors, numberOfNeighbors, ALL);
	}

	public double getNumberOfNodes(final boolean withOutgoingDegree) {
		return withOutgoingDegree ? numberOfNodesWithOutgoingDegrees : numberOfNodes;
	}

	public double getNumberOfNodes() {
		return numberOfNodes;
	}

	public double getNumberOfEdges() {
		return numberOfEdges;
	}

	public double getMaximumDegree(final EdgeDirection direction) {
		switch (direction) {
		case BOTH:
			return maximumDegree;
		case OUTGOING:
			return maximumOutgoingDegree;
		default:
			throw new UnsupportedOperationException();
		}
	}

	public double getAverageDegree(final EdgeDirection direction) {
		switch (direction) {
		case BOTH:
			return averageDegree;
		case OUTGOING:
			return averageOutgoingDegree;
		default:
			throw new UnsupportedOperationException();
		}
	}

	public double getNumberOfMaximumDegree(final EdgeDirection direction) {
		switch (direction) {
		case BOTH:
			return numberOfMaximumDegree;
		case OUTGOING:
			return numberOfMaximumOutgoingDegree;
		default:
			throw new UnsupportedOperationException();
		}
	}

	public double getNumberOfAverageDegree(final EdgeDirection direction) {
		switch (direction) {
		case BOTH:
			return numberOfAverageDegree;
		case OUTGOING:
			return numberOfAverageOutgoingDegree;
		default:
			throw new UnsupportedOperationException();
		}
	}

	public double getNumberOfHigherDegree(final EdgeDirection direction) {
		switch (direction) {
		case BOTH:
			return numberOfHigherDegree;
		case OUTGOING:
			return numberOfHigherOutgoingDegree;
		default:
			throw new UnsupportedOperationException();
		}
	}

	public List<Double> getClusteringCoefficients() {
		return getClusteringCoefficients(ALL);
	}

	public List<Double> getClusteringCoefficients(final String type) {
		return clusteringCoefficients.get(type);
	}
}
