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

import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import eu.mondo.sam.core.metrics.BenchmarkMetric;

public abstract class ConcreteMetric<D extends Driver<?>> extends
		BenchmarkMetric {

	protected D driver;

	public ConcreteMetric(D driver) {
		this.driver = driver;
	}

	public ConcreteMetric(D driver, String identifier) {
		super(identifier);
		this.driver = driver;
	}

	public abstract void calculate(final MetricToken token);

	public D getDriver() {
		return driver;
	}

	public void setDriver(D driver) {
		this.driver = driver;
	}
}
