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
package hu.bme.mit.trainbenchmark.benchmark.eclipseocl;

import java.io.IOException;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.Transformation;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.eclipseocl.checkers.EclipseOCLChecker;
import hu.bme.mit.trainbenchmark.emf.EMFDriver;
import hu.bme.mit.trainbenchmark.emf.benchmarkcases.EMFBenchmarkCase;
import hu.bme.mit.trainbenchmark.emf.matches.EMFMatch;
import hu.bme.mit.trainbenchmark.emf.transformation.EMFTransformation;
import hu.bme.mit.trainbenchmark.railway.RailwayElement;

public class EclipseOCLBenchmarkCase<T extends RailwayElement>
		extends EMFBenchmarkCase<EMFDriver<BenchmarkConfig>, BenchmarkConfig, EclipseOCLChecker<EMFMatch>> {

	@Override
	public EMFDriver createDriver(final BenchmarkConfig benchmarkConfig) throws Exception {
		return new EMFDriver(benchmarkConfig);
	}

	@Override
	public EclipseOCLChecker<EMFMatch> createChecker(final BenchmarkConfig benchmarkConfig, final EMFDriver driver) throws Exception {
		return (EclipseOCLChecker<EMFMatch>) EclipseOCLChecker.newInstance(driver, benchmarkConfig);
	}

	@Override
	public Transformation<?, ?> createTransformation(final BenchmarkConfig benchmarkConfig, final EMFDriver driver) throws IOException {
		return EMFTransformation.newInstance(driver, benchmarkConfig.getQuery(), benchmarkConfig.getScenario());
	}

}
