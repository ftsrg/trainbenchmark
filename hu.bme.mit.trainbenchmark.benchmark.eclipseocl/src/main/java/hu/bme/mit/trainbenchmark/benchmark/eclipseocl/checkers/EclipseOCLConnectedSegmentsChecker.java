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
import hu.bme.mit.trainbenchmark.emf.matches.EMFConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.railway.RailwayPackage;
import hu.bme.mit.trainbenchmark.railway.Segment;
import hu.bme.mit.trainbenchmark.railway.Sensor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.ocl.util.Bag;
import org.eclipse.ocl.util.Tuple;

public class EclipseOCLConnectedSegmentsChecker extends EclipseOCLChecker<EMFConnectedSegmentsMatch> {

	public EclipseOCLConnectedSegmentsChecker(final EMFDriver driver, final BenchmarkConfig bc) throws IOException {
		super(driver, bc);
	}

	@Override
	protected EClassifier getContext() {
		return RailwayPackage.eINSTANCE.getSensor();
	}

	@Override
	public Collection<EMFConnectedSegmentsMatch> check() {
		matches = new ArrayList<>();

		final Bag<Tuple<?, ?>> bag = (Bag<Tuple<?, ?>>) queryEvaluator.evaluate(driver.getContainer());
		for (final Tuple<?, ?> tuple : bag) {
			final Sensor sensor = (Sensor) tuple.getValue("sensor");
			final Segment segment1 = (Segment) tuple.getValue("segment1");
			final Segment segment2 = (Segment) tuple.getValue("segment2");
			final Segment segment3 = (Segment) tuple.getValue("segment3");
			final Segment segment4 = (Segment) tuple.getValue("segment4");
			final Segment segment5 = (Segment) tuple.getValue("segment5");
			final Segment segment6 = (Segment) tuple.getValue("segment6");
			final EMFConnectedSegmentsMatch match = new EMFConnectedSegmentsMatch(sensor, segment1, segment2, segment3, segment4, segment5, segment6);
			matches.add(match);
		}

		return matches;
	}

}
