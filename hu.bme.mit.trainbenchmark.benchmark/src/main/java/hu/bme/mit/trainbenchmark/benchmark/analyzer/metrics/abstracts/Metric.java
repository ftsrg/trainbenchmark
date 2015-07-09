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

package hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.abstracts;

import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.ConcreteMetric;

public abstract class Metric {

	private ConcreteMetric<?> concreteMetric;

	private String identifier;

	protected Metric(String identifier) {
		this.identifier = identifier;
	}

	public ConcreteMetric<?> getConcreteMetric() {
		if (concreteMetric == null) {
			throw new NullPointerException(
					"ConcreteMetric in not initialized!");
		}
		return concreteMetric;
	}

	public void attachConcreteMetric(final ConcreteMetric<?> concreteMetric) {
		this.concreteMetric = concreteMetric;
		this.concreteMetric.setMetricName(identifier);
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

}
