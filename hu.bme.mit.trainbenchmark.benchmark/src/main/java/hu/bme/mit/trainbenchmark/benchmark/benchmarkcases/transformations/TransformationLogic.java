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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import eu.mondo.sam.core.metrics.ScalarMetric;
import eu.mondo.sam.core.metrics.TimeMetric;
import eu.mondo.sam.core.results.BenchmarkResult;
import eu.mondo.sam.core.results.PhaseResult;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;
import hu.bme.mit.trainbenchmark.constants.Scenario;

// TMatch: matches
// TElement: elements in the match set
// TTransformationObject: transformation object
// TBenchmarkConfig: benchmark configuration
public abstract class TransformationLogic<TMatch, TElement, TTransformationObject, TBenchmarkConfig extends BenchmarkConfig> {

	public static TransformationLogic<?, ?, ?, ?> newInstance(final Scenario scenario, final Comparator<?> comparator) {
		switch (scenario) {
		case REPAIR:
			return new RepairTransformationLogic<>(comparator);
		case INJECT:
			return new InjectTransformationLogic<>(comparator);
		default:
			throw new UnsupportedOperationException("Scenario " + scenario + " not supported");
		}
	}

	protected TransformationLogic(final Comparator<TTransformationObject> comparator) {
		super();
		this.comparator = comparator;
	}

	protected TBenchmarkConfig benchmarkConfig;
	protected BenchmarkResult benchmarkResult;
	protected Driver<TElement, TBenchmarkConfig> driver;
	protected Random random;

	protected Collection<TTransformationObject> candidatesToModify;
	protected List<TTransformationObject> objectsToModify;
	protected Comparator<TTransformationObject> comparator;

	protected long numberOfObjectsToModify;
	protected long start;
	protected long startEdit;
	protected long end;
	protected Transformation<TTransformationObject, Driver<TElement, TBenchmarkConfig>> transformation;

	public void initialize(final TBenchmarkConfig benchmarkConfig, final Driver<TElement, TBenchmarkConfig> driver, final Random random) {
		this.benchmarkConfig = benchmarkConfig;
		this.driver = driver;
		this.random = random;
	}

	protected abstract void lhs(final Collection<TMatch> currentMatches) throws Exception;

	public void performTransformation(final PhaseResult phaseResult, final Collection<TMatch> currentMatches) throws Exception {
		final TimeMetric transformationMetric = new TimeMetric("Time");

		final ScalarMetric modified = new ScalarMetric("Modified");
		numberOfObjectsToModify = Util.calcNumberOfObjectsToModify(benchmarkConfig, currentMatches.size());
		modified.setValue(numberOfObjectsToModify);

		driver.beginTransaction();
		lhs(currentMatches);

		// we do not measure this in the benchmark results
		final List<TTransformationObject> candidatesList = copyAndSort();
		objectsToModify = pickRandom(numberOfObjectsToModify, candidatesList);

		transformationMetric.startMeasure();
		transformation.rhs(objectsToModify);
		driver.finishTransaction();
		transformationMetric.stopMeasure();

		phaseResult.addMetrics(transformationMetric, modified);
	}

	protected abstract List<TTransformationObject> copyAndSort();

	private List<TTransformationObject> pickRandom(long nMatchesToModify, final List<TTransformationObject> matches) {
		final int size = matches.size();
		if (size < nMatchesToModify) {
			nMatchesToModify = size;
		}
		Collections.shuffle(matches, random);
		final List<TTransformationObject> objects = new ArrayList<>();
		for (int i = 0; i < nMatchesToModify; i++) {
			final TTransformationObject object = matches.get(i);
			objects.add(object);
		}
		return objects;
	}

	public void setTransformation(final Transformation<?, ?> transformation) {
		this.transformation = (Transformation<TTransformationObject, Driver<TElement, TBenchmarkConfig>>) transformation;
	}

}
