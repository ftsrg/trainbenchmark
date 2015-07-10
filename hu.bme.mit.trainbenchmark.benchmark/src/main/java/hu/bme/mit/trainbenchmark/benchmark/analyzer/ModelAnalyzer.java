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

import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.AverageDegreeDistributionMetric;
import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.AverageDegreeMetric;
import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.HigherDegreeDistributionMetric;
import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.MaximumDegreeDistributionMetric;
import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.MaximumDegreeMetric;
import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.Metric;
import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.NumberOfEdgesMetric;
import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.NumberOfNodesMetric;
import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.constants.EdgeDirection;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public abstract class ModelAnalyzer<D extends Driver<?>> extends Analyzer<D> {

	protected double numberOfNodes;

	protected double numberOfEdges;

	protected double numberOfOutgoingEdges;

	protected double maximumDegree;

	protected double averageDegree;

	protected double numberOfMaximumDegree;

	protected double numberOfAverageDegree;

	protected double maximumOutgoingDegree;

	protected double averageOutgoingDegree;

	protected double numberOfMaximumOutgoingDegree;

	protected double numberOfAverageOutgoingDegree;

	protected double numberOfHigherDegree;

	protected double numberOfHigherOutgoingDegree;

	public ModelAnalyzer(D driver) {
		super(driver);
	}

	@Override
	public void initializeMetrics() {
		if (metrics == null) {
			metrics = new ArrayList<Metric>();
		}
		Metric.setAnalyzer(this);
		metrics.add(new NumberOfNodesMetric("NumOfNodes"));
		metrics.add(new NumberOfEdgesMetric("NumOfEdges",
				EdgeDirection.BOTH));
		metrics.add(new AverageDegreeMetric("AvgDegree",
				EdgeDirection.BOTH));
		metrics.add(new MaximumDegreeMetric("MaxDegree",
				EdgeDirection.BOTH));
		metrics.add(new AverageDegreeDistributionMetric(
				"AvgDegreeDist", EdgeDirection.BOTH));
		metrics.add(new MaximumDegreeDistributionMetric(
				"MaxDegreeDist", EdgeDirection.BOTH));
		metrics.add(new NumberOfEdgesMetric("NumOfOutgoingEdges",
				EdgeDirection.OUTGOING));
		metrics.add(new AverageDegreeMetric("AvgOutgoingDegree",
				EdgeDirection.OUTGOING));
		metrics.add(new MaximumDegreeMetric("MaxOutgoingDegree",
				EdgeDirection.OUTGOING));
		metrics.add(new AverageDegreeDistributionMetric(
				"AvgOutgoingDegreeDist", EdgeDirection.OUTGOING));
		metrics.add(new MaximumDegreeDistributionMetric(
				"MaxOutgoingDegreeDist", EdgeDirection.OUTGOING));
		metrics.add(new HigherDegreeDistributionMetric("HigherDegree",
				EdgeDirection.BOTH));
		metrics.add(new HigherDegreeDistributionMetric(
				"HigherOutgoingDegree", EdgeDirection.OUTGOING));
	}

	protected void calculateAverageDegree(final EdgeDirection direction) {
		switch (direction) {
		case BOTH:
			averageDegree = numberOfEdges * 2 / numberOfNodes;
			break;
		case OUTGOING:
			averageOutgoingDegree = numberOfOutgoingEdges
					/ numberOfNodes;
			break;
		default:
			throw new UnsupportedOperationException();
		}
	}

	public double getNumberOfNodes() {
		return numberOfNodes;
	}

	public double getNumberOfEdges(final EdgeDirection direction) {
		switch (direction) {
		case BOTH:
			return numberOfEdges;
		case OUTGOING:
			return numberOfOutgoingEdges;
		default:
			throw new UnsupportedOperationException();
		}

	}

	protected void increaseNumberOfEdges(final EdgeDirection direction,
			final double value) {
		switch (direction) {
		case BOTH:
			numberOfEdges += value;
			break;
		case OUTGOING:
			numberOfOutgoingEdges += value;
			break;
		default:
			throw new UnsupportedOperationException();
		}
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

	protected void changeMaximumDegree(final EdgeDirection direction,
			final double degree) {
		switch (direction) {
		case BOTH:
			maximumDegree = degree > maximumDegree ? degree
					: maximumDegree;
			break;
		case OUTGOING:
			maximumOutgoingDegree = degree > maximumOutgoingDegree ? degree
					: maximumOutgoingDegree;
			break;
		default:
			throw new UnsupportedOperationException();
		}
	}

	protected void changeNumberOfDegrees(final EdgeDirection direction,
			final double value, final int roundedAverage) {
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

	protected int roundAverageDegree(final EdgeDirection direction) {
		switch (direction) {
		case BOTH: {
			BigDecimal roundedDegree = new BigDecimal(
					getAverageDegree(EdgeDirection.BOTH));
			roundedDegree = roundedDegree.setScale(0,
					RoundingMode.HALF_UP);
			return roundedDegree.intValue();
		}

		case OUTGOING: {
			BigDecimal roundedOutgoingDegree = new BigDecimal(
					getAverageDegree(EdgeDirection.OUTGOING));
			roundedOutgoingDegree = roundedOutgoingDegree.setScale(
					0, RoundingMode.HALF_UP);
			return roundedOutgoingDegree.intValue();
		}
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

}
