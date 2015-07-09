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

package hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics;

public class MetricToken {

	private int numberOfNodes;

	private int numberOfEdges;

	private double averageDegree;

	private double maximumDegree;

	public int getNumberOfNodes() {
		return numberOfNodes;
	}

	public void setNumberOfNodes(int numberOfNodes) {
		this.numberOfNodes = numberOfNodes;
	}

	public int getNumberOfEdges() {
		return numberOfEdges;
	}

	public void setNumberOfEdges(int numberOfEdges) {
		this.numberOfEdges = numberOfEdges;
	}

	public double getAverageDegree() {
		return averageDegree;
	}

	public void setAverageDegree(double averageDegree) {
		this.averageDegree = averageDegree;
	}

	public double getMaximumDegree() {
		return maximumDegree;
	}

	public void setMaximumDegree(double maximumDegree) {
		this.maximumDegree = maximumDegree;
	}

}
