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

import hu.bme.mit.trainbenchmark.benchmark.checker.Checker;
import hu.bme.mit.trainbenchmark.benchmark.eclipseocl.checkers.EclipseOCLChecker;
import hu.bme.mit.trainbenchmark.emf.EMFDriver;
import hu.bme.mit.trainbenchmark.emf.benchmarkcases.EMFBenchmarkCase;
import hu.bme.mit.trainbenchmark.emf.matches.EMFMatch;
import hu.bme.mit.trainbenchmark.emf.transformation.EMFTransformation;
import hu.bme.mit.trainbenchmark.railway.RailwayElement;

public class EclipseOCLBenchmarkCase<T extends RailwayElement> extends EMFBenchmarkCase {

	@Override
	public void initialize() throws Exception {
		final EMFDriver emfDriver = new EMFDriver();
		driver = emfDriver;
		checker = (Checker<EMFMatch>) EclipseOCLChecker.newInstance(emfDriver, bc);

		if (bc.getScenario().hasTranformation()) {
			transformation = EMFTransformation.newInstance(emfDriver, bc.getQuery(), bc.getScenario());
		}
	}

}
