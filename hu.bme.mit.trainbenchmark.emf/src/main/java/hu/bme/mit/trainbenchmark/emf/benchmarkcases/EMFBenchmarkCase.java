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
package hu.bme.mit.trainbenchmark.emf.benchmarkcases;

import java.util.Comparator;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCaseRunner;
import hu.bme.mit.trainbenchmark.benchmark.checker.Checker;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.emf.EMFDriver;
import hu.bme.mit.trainbenchmark.emf.matches.EMFMatch;
import hu.bme.mit.trainbenchmark.emf.matches.EMFMatchComparator;
import hu.bme.mit.trainbenchmark.railway.RailwayElement;

public abstract class EMFBenchmarkCase<TDriver extends EMFDriver, TBenchmarkConfig extends BenchmarkConfig, TChecker extends Checker<EMFMatch>>
		extends AbstractBenchmarkCaseRunner<EMFMatch, RailwayElement, TDriver, TBenchmarkConfig, TChecker> {

	@Override
	protected Comparator<?> getMatchComparator() {
		return new EMFMatchComparator();
	}

}
