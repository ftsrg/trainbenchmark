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

import java.util.*;

import eu.mondo.sam.core.metrics.ScalarMetric;
import eu.mondo.sam.core.metrics.TimeMetric;
import eu.mondo.sam.core.results.BenchmarkResult;
import eu.mondo.sam.core.results.PhaseResult;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;
import hu.bme.mit.trainbenchmark.constants.Scenario;

public abstract class TransformationLogic<M, T, O> {

	// M: matches
	// T: elements in the match set
	// O: transformation object

	public static TransformationLogic newInstance(final Scenario scenario, final Comparator comparator) {
		switch (scenario) {
		case REPAIR:
			return new RepairTransformationLogic<>(comparator);
		case INJECT:
			return new InjectTransformationLogic<>(comparator);
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

	public void initialize(final BenchmarkConfig bc, final Driver<T> driver, final Random random) {
		this.bc = bc;
		this.driver = driver;
		this.random = random;
	}

	protected abstract void lhs(final Collection<M> currentMatches) throws Exception;

	public void performTransformation(final PhaseResult phaseResult, final Collection<M> currentMatches) throws Exception {
		final TimeMetric transformationMetric = new TimeMetric("Time");

		final ScalarMetric modified = new ScalarMetric("Modified");
		nObjectsToModify = Util.calcModify(bc, currentMatches.size());
		modified.setValue(nObjectsToModify);

		transformationMetric.startMeasure();
		driver.beginTransaction();
		lhs(currentMatches);
		transformationMetric.stopMeasure();

		// we do not measure this in the benchmark results
		final List<O> candidatesList = copyAndSort();
		objectsToModify = pickRandom(nObjectsToModify, candidatesList);

		transformationMetric.continueMeasure();
		transformation.rhs(objectsToModify);
		driver.finishTransaction();
		transformationMetric.stopMeasure();

		phaseResult.addMetrics(transformationMetric, modified);
	}

	protected abstract List<O> copyAndSort();

	private List<O> pickRandom(long nMatchesToModify, final List<O> matches) {
		final int size = matches.size();
		if (size < nMatchesToModify) {
			nMatchesToModify = size;
		}
		Collections.shuffle(matches, random);
		final List<O> objects = new ArrayList<>();
		for (int i = 0; i < nMatchesToModify; i++) {
			final O object = matches.get(i);
			objects.add(object);
		}
		return objects;
	}

	public void setTransformation(final Transformation<?> transformation) {
		this.transformation = (Transformation<O>) transformation;
	}

}
