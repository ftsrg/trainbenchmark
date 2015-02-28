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
package hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations;

import hu.bme.mit.trainbenchmark.benchmark.driver.DatabaseDriver;
import hu.bme.mit.trainbenchmark.benchmark.util.BenchmarkResult;
import hu.bme.mit.trainbenchmark.benchmark.util.UniqRandom;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;
import hu.bme.mit.trainbenchmark.constants.TrainBenchmarkConstants;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public abstract class TransformationDefinition<T> {

	protected List<T> currentResults;
	protected List<T> elementsToModify;
	
	protected long nElementsToModify;
	protected long start;
	protected long startEdit;
	protected long end;
	
	protected BenchmarkResult bmr;
	protected DatabaseDriver<T> driver;
	
	public void initialize(final BenchmarkResult bmr, final DatabaseDriver<T> driver, final List<T> currentResults) {
		this.bmr = bmr;
		this.driver = driver;
		this.currentResults = currentResults;
	}
	
	protected abstract void lhs() throws IOException;
	protected abstract void rhs() throws IOException;	
	
	public void performTransformation() throws IOException {
		nElementsToModify = Util.calcModify(bmr);
		bmr.addModifiedElementsSize(nElementsToModify);

		Collections.sort(currentResults, driver.getComparator());
		
		System.out.println("current results:");
		System.out.println(currentResults);
		System.out.println("-----");
		
		bmr.restartClock();
		driver.beginTransaction();
		lhs();
		bmr.addLhsTime();
				
		bmr.restartClock();
		rhs();
		driver.finishTransaction();
		bmr.addRhsTime();
	}
	
	public static Random getRandom() {
		return new UniqRandom(TrainBenchmarkConstants.RANDOM_SEED);
	}

}
