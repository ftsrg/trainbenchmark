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
package hu.bme.mit.trainbenchmark.benchmark.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.TransformationDefinition;

import java.io.IOException;

public abstract class AbstractTransformationBenchmarkCase<T> extends AbstractBenchmarkCase<T> {

	public void benchmarkModify() throws IOException {
		modify();
	}
	
	protected void modify() throws IOException {
		final String className = "hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations." + bc.getScenario().toLowerCase()
				+ "." + bc.getQuery();
		try {
			final Class<?> clazz = this.getClass().getClassLoader().loadClass(className);
			final TransformationDefinition td = (TransformationDefinition) clazz.newInstance();
			td.initialize(getBenchmarkResult(), driver, results);
			td.performTransformation();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw new UnsupportedOperationException(e);
		}
	}

}
