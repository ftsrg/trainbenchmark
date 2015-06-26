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
package hu.bme.mit.trainbenchmark.benchmark.eclipseocl.checkers;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.emf.EMFDriver;
import hu.bme.mit.trainbenchmark.emf.matches.EMFPosLengthMatch;
import hu.bme.mit.trainbenchmark.railway.RailwayPackage;
import hu.bme.mit.trainbenchmark.railway.Segment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.eclipse.emf.ecore.EClassifier;

public class EclipseOCLSemaphoreNeighborChecker extends EclipseOCLChecker<EMFPosLengthMatch> {

	public EclipseOCLSemaphoreNeighborChecker(final EMFDriver driver, final BenchmarkConfig bc) throws IOException {
		super(driver, bc);
	}

	@Override
	protected EClassifier getContext() {
		return RailwayPackage.eINSTANCE.getSegment();
	}

	@Override
	public Collection<EMFPosLengthMatch> check() {
		matches = new ArrayList<>();

		final Set<Segment> results = (Set<Segment>) queryEvaluator.evaluate(driver.getContainer());
		for (final Segment segment : results) {
			matches.add(new EMFPosLengthMatch(segment));
		}

		return matches;
	}

}
