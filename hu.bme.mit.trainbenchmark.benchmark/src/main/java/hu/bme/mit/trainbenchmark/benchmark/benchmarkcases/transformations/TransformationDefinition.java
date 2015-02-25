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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class TransformationDefinition {

	public void initialize(final BenchmarkResult bmr, final DatabaseDriver driver, final List<? extends Object> invalids) {
		this.bmr = bmr;
		this.driver = driver;
		this.invalids = invalids;
	}
	
	protected List<? extends Object> invalids;
	protected List<? extends Object> itemsToModify;
	
	protected long nElemToModify;
	protected long start;
	protected long startEdit;
	protected long end;
	
	protected BenchmarkResult bmr;
	protected DatabaseDriver driver;
	
	protected abstract void lhs() throws IOException;
	protected abstract void rhs() throws IOException;	
	
	public void performTransformation() throws IOException {
		nElemToModify = Util.calcModify(bmr);
		bmr.addModifiedElementsSize(nElemToModify);

		bmr.restartClock();
		driver.beginTransaction();
		lhs();
		bmr.addLhsTime();
		
		bmr.restartClock();
		rhs();
		driver.finishTransaction();
		bmr.addRhsTime();
	}
	
	// utils
	
	public static Random getRandom() {
		return new UniqRandom(TrainBenchmarkConstants.RANDOM_SEED);
	}

	public static <T> List<T> pickRandom(long nElemToModify, final List<T> elements) {
		final Random random = getRandom();
		final int size = elements.size();
		final List<T> itemsToModify = new ArrayList<>();

		if (size < nElemToModify) {
			nElemToModify = size;
		}

		for (int i = 0; i < nElemToModify; i++) {
			final int rndTarget = random.nextInt(size);
			final T segment = new ArrayList<>(elements).get(rndTarget);
			itemsToModify.add(segment);
		}
		return itemsToModify;
	}

}
