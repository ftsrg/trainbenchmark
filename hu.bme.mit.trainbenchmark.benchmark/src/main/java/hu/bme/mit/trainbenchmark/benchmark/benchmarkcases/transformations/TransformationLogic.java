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

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.util.BenchmarkResult;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;
import hu.bme.mit.trainbenchmark.constants.Scenario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public abstract class TransformationLogic<M, T, O> {

	// M: matches
	// T: elements in the match set
	// O: transformation object

	public static TransformationLogic newInstance(final Scenario scenario, final Comparator comparator) {
		switch (scenario) {
		case REPAIR:
			return new RepairTransformationLogic<>(comparator);
		case USER:
			return new UserTransformationLogic<>(comparator);
		default:
			throw new UnsupportedOperationException("Scenario " + scenario + " not supported");
		}
	}

	protected TransformationLogic(final Comparator comparator) {
		super();
		this.comparator = comparator;
	}

	protected BenchmarkConfig bc;
	protected BenchmarkResult br;
	protected Driver<T> driver;
	protected Random random;

	protected Collection<O> candidatesToModify;
	protected List<O> objectsToModify;
	protected Comparator<O> comparator;

	protected long nObjectsToModify;
	protected long start;
	protected long startEdit;
	protected long end;
	protected Transformation<O> transformation;

	public void initialize(final BenchmarkConfig bc, final BenchmarkResult br, final Driver<T> driver, final Random random) {
		this.bc = bc;
		this.br = br;
		this.driver = driver;
		this.random = random;
	}

	protected abstract void lhs(final Collection<M> currentMatches) throws IOException;

	public void performTransformation(final Collection<M> currentMatches) throws IOException {
		nObjectsToModify = Util.calcModify(br);
		br.addModifiedMatchCount(nObjectsToModify);

		br.restartClock();
		driver.beginTransaction();
		lhs(currentMatches);
		br.addLhsTime();

		// we do not measure this in the benchmark results
		final List<O> candidatesList = copyAndSort();
		objectsToModify = pickRandom(nObjectsToModify, candidatesList);

		br.restartClock();
		transformation.rhs(objectsToModify);
		driver.finishTransaction();
		br.addRhsTime();
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

	public void setTransformation(final Transformation<?> transformation) {
		this.transformation = (Transformation<O>) transformation;
	}

}
