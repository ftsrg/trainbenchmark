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
package hu.bme.mit.trainbenchmark.benchmark.eclipseocl;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.eclipseocl.checkers.EclipseOCLChecker;
import hu.bme.mit.trainbenchmark.constants.Scenario;
import hu.bme.mit.trainbenchmark.emf.EMFDriver;
import hu.bme.mit.trainbenchmark.emf.benchmarkcases.EMFBenchmarkCase;
import hu.bme.mit.trainbenchmark.emf.transformation.EMFTransformation;
import hu.bme.mit.trainbenchmark.railway.RailwayElement;

import java.io.IOException;

public class EclipseOCLBenchmarkCase<T extends RailwayElement> extends EMFBenchmarkCase {

	@Override
	public void benchmarkInit(final BenchmarkConfig bc) throws IOException {
		super.benchmarkInit(bc);

		// final String modelPath = bc.getModelPathNameWithoutExtension() + ".emf";
		// final EMFDriver emfDriver = new EMFDriver(modelPath);
		// driver = (Driver<T>) emfDriver;
		// container = emfDriver.getContainer();

		final EMFDriver emfDriver = new EMFDriver();
		driver = emfDriver;
		checker = new EclipseOCLChecker(bc);

		if (bc.getScenario() != Scenario.BATCH) {
			transformation = EMFTransformation.newInstance(emfDriver, bc.getQuery(), bc.getScenario());
		}
	}

}
