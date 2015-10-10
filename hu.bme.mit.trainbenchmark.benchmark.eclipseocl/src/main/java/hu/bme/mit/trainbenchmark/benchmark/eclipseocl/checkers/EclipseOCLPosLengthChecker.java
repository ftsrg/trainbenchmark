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

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.ocl.util.Bag;
import org.eclipse.ocl.util.Tuple;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.emf.EMFDriver;
import hu.bme.mit.trainbenchmark.emf.matches.EMFPosLengthMatch;
import hu.bme.mit.trainbenchmark.railway.Segment;

public class EclipseOCLPosLengthChecker extends EclipseOCLChecker<EMFPosLengthMatch> {

	public EclipseOCLPosLengthChecker(final EMFDriver driver, final BenchmarkConfig benchmarkConfig) throws Exception {
		super(driver, benchmarkConfig);
	}

	@Override
	public Collection<EMFPosLengthMatch> check() {
		matches = new ArrayList<>();

		final Bag<Tuple<?, ?>> bag = (Bag<Tuple<?, ?>>) queryEvaluator.evaluate(driver.getContainer());
		for (final Tuple<?, ?> tuple : bag) {
			final Segment segment = (Segment) tuple.getValue("segment");
			matches.add(new EMFPosLengthMatch(segment));
		}

		return matches;
	}

}
