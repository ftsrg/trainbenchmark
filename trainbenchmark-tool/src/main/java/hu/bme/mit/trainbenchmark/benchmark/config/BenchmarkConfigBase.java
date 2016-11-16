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

package hu.bme.mit.trainbenchmark.benchmark.config;

import java.util.Collection;
import java.util.List;

import hu.bme.mit.trainbenchmark.config.AbstractConfigBase;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;

public final class BenchmarkConfigBase extends AbstractConfigBase {

	/**
	 * The id for the benchmark, used for determining the result directory.
	 */
	protected final int benchmarkId;

	/**
	 * The timeout for each measurement run in seconds.
	 */
	protected final long timeout;

	/**
	 * The number of measurement runs.
	 */
	protected final int runs;

	/**
	 * The number of Transformation-Recheck loops.
	 */
	protected final int queryTransformationCount;

	/**
	 * The name of the model file (without extension).
	 */
	protected final String modelFilename;

	/**
	 * The sequence of operations to perform.
	 */
	protected final List<RailwayOperation> railwayOperations;

	/**
	 * A short description of the workload.
	 */
	protected final String workload;

	/**
	 * Transformation strategy to pick matches for transformation, e.g. "a fixed number" or "a proportional amount" of matches
	 */
	protected final TransformationChangeSetStrategy transformationChangeSetStrategy;

	/**
	 * Transformation constant to pick matches for transformations, e.g. "10 matches" or "10% of the matches"
	 */
	protected final int transformationConstant;

	protected BenchmarkConfigBase(final int benchmarkId, final long timeout, final int runs,
			final int queryTransformationCount, final String modelFilename,
			final List<RailwayOperation> railwayOperations, final String workload,
			final TransformationChangeSetStrategy transformationChangeSetStrategy, final int transformationConstant) {
		super();
		this.benchmarkId = benchmarkId;
		this.timeout = timeout;
		this.runs = runs;
		this.queryTransformationCount = queryTransformationCount;
		this.modelFilename = modelFilename;
		this.railwayOperations = railwayOperations;
		this.workload = workload;
		this.transformationChangeSetStrategy = transformationChangeSetStrategy;
		this.transformationConstant = transformationConstant;
	}

	public int getBenchmarkId() {
		return benchmarkId;
	}

	public long getTimeout() {
		return timeout;
	}

	public int getRuns() {
		return runs;
	}

	public int getQueryTransformationCount() {
		return queryTransformationCount;
	}

	public String getModelPath() {
		return getModelDir() + modelFilename;
	}

	public Collection<RailwayOperation> getRailwayOperations() {
		return railwayOperations;
	}

	/**
	 * @return An identifier for the workload. Example: "Query mix, Repair transformation"
	 */
	public String getWorkload() {
		return workload;
	}

	public String getModelFilename() {
		return modelFilename;
	}

	public TransformationChangeSetStrategy getTransformationChangeSetStrategy() {
		return transformationChangeSetStrategy;
	}

	public int getTransformationConstant() {
		return transformationConstant;
	}

}
