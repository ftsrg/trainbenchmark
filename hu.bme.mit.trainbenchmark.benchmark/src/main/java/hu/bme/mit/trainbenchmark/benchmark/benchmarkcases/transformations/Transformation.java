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
import hu.bme.mit.trainbenchmark.benchmark.util.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public abstract class Transformation<M, T, O> {

	// M: matches
	// T: elements in the match set
	// O: transformation object

	protected Random random;

	protected Collection<M> currentMatches;
	protected Collection<O> candidatesToModify;
	protected List<O> objectsToModify;

	protected long nMatchesToModify;
	protected long start;
	protected long startEdit;
	protected long end;

	protected BenchmarkResult bmr;
	protected DatabaseDriver<M, T> driver;

	public void initialize(final BenchmarkResult bmr, final DatabaseDriver<M, T> driver, final Collection<M> currentMatches,
			final Random random) {
		this.random = random;
		this.bmr = bmr;
		this.driver = driver;
		this.currentMatches = currentMatches;
	}

	protected abstract void lhs() throws IOException;

	protected abstract void rhs() throws IOException;

	public void performTransformation() throws IOException {
		nMatchesToModify = Util.calcModify(bmr);
		bmr.addModifiedMatchCount(nMatchesToModify);

		bmr.restartClock();
		driver.beginTransaction();
		lhs();
		bmr.addLhsTime();

		// we do not measure this in the benchmark results
		final List<O> candidatesList = copyAndSort();
		objectsToModify = pickRandom(nMatchesToModify, candidatesList);

		bmr.restartClock();
		rhs();
		driver.finishTransaction();
		bmr.addRhsTime();
	}

	protected abstract List<O> copyAndSort();

	private List<O> pickRandom(long nMatchesToModify, final List<O> matches) {
		final int size = matches.size();
		if (size < nMatchesToModify) {
			nMatchesToModify = size;
		}

		final List<O> objects = new ArrayList<>();
		for (int i = 0; i < nMatchesToModify; i++) {
			final int rndTarget = random.nextInt(size);
			final O object = matches.get(rndTarget);
			objects.add(object);
		}
		return objects;
	}

}
