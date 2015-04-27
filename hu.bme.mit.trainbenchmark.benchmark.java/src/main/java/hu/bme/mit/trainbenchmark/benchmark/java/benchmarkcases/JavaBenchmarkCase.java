/*******************************************************************************
 * Copyright (c) 2010-2014, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/

package hu.bme.mit.trainbenchmark.benchmark.java.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.java.matches.JavaMatch;
import hu.bme.mit.trainbenchmark.benchmark.java.matches.JavaMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.java.transformations.JavaTransformation;
import hu.bme.mit.trainbenchmark.constants.Scenario;
import hu.bme.mit.trainbenchmark.emf.EMFDriver;
import hu.bme.mit.trainbenchmark.railway.RailwayElement;

import java.io.IOException;
import java.util.Comparator;

public class JavaBenchmarkCase extends AbstractBenchmarkCase<JavaMatch, RailwayElement> {

	@Override
	public void benchmarkInit(final BenchmarkConfig bc) throws IOException {
		super.benchmarkInit(bc);

		final EMFDriver emfDriver = new EMFDriver();
		driver = emfDriver;
		checker = JavaChecker.newInstance(emfDriver, bc.getQuery());
		if (bc.getScenario() != Scenario.BATCH) {
			transformation = JavaTransformation.newInstance(emfDriver, bc.getQuery(), bc.getScenario());
		}
	}

	@Override
	protected Comparator<?> getComparator() {
		switch (bc.getScenario()) {
		case BATCH:
		case USER:
			return driver.getElementComparator();
		case REPAIR:
			return new JavaMatchComparator();
		default:
			return null;
		}
	}
}
