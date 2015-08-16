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

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.emf.EMFDriver;
import hu.bme.mit.trainbenchmark.emf.matches.EMFMatch;
import hu.bme.mit.trainbenchmark.emf.matches.EMFMatchComparator;
import hu.bme.mit.trainbenchmark.railway.RailwayElement;

import java.util.Comparator;

public abstract class EMFBenchmarkCase extends AbstractBenchmarkCase<EMFMatch, RailwayElement, EMFDriver> {

	@Override
	protected Comparator<?> getMatchComparator() {
		return new EMFMatchComparator();
	}

	@Override
	protected void initDescription() {
		// TODO Auto-generated method stub

	}

}
