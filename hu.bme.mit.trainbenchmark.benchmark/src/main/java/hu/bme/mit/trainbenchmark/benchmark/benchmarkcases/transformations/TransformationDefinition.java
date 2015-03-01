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
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public abstract class TransformationDefinition<T> {

	protected Collection<T> currentResults;
	protected Collection<T> elementCandidates;
	protected List<T> elementsToModify;

	protected long nElementsToModify;
	protected long start;
	protected long startEdit;
	protected long end;

	protected BenchmarkResult bmr;
	protected DatabaseDriver<T> driver;

	public void initialize(final BenchmarkResult bmr, final DatabaseDriver<T> driver, final Collection<T> currentResults) {
		this.bmr = bmr;
		this.driver = driver;
		this.currentResults = currentResults;
	}

	protected abstract void lhs() throws IOException;

	protected abstract void rhs() throws IOException;

	public void performTransformation() throws IOException {
		nElementsToModify = Util.calcModify(bmr);
		bmr.addModifiedElementsSize(nElementsToModify);

		bmr.restartClock();
		driver.beginTransaction();
		lhs();
		bmr.addLhsTime();

		// we do not measure this in the benchmark results
		final List<T> candidatesList = new ArrayList<>(elementCandidates);
		Collections.sort(candidatesList, driver.getComparator());
		elementsToModify = pickRandom(nElementsToModify, candidatesList);

		bmr.restartClock();
		rhs();
		driver.finishTransaction();
		bmr.addRhsTime();
	}

	private List<T> pickRandom(long nElementsToModify, final List<T> elements) {
		final Random random = getRandom();
		final int size = elements.size();
		if (size < nElementsToModify) {
			nElementsToModify = size;
		}

		final List<T> elementsToModify = new ArrayList<>();
		for (int i = 0; i < nElementsToModify; i++) {
			final int rndTarget = random.nextInt(size);
			final T element = elements.get(rndTarget);
			elementsToModify.add(element);
		}
		return elementsToModify;
	}

	public static Random getRandom() {
		return new UniqRandom(TrainBenchmarkConstants.RANDOM_SEED);
	}

}
