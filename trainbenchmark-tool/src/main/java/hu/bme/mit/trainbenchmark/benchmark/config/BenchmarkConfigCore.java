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

import hu.bme.mit.trainbenchmark.config.AbstractConfig;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;

public final class BenchmarkConfigCore extends AbstractConfig {

	/**
	 * The timeout for each measurement run in seconds.
	 */
	protected long timeout;

	/**
	 * The number of measurement runs.
	 */
	protected int runs;

	/**
	 * The number of Transformation-Recheck loops.
	 */
	protected int queryTransformationCount;

	/**
	 * The name of the model file (without extension).
	 */
	protected String modelFilename;

	/**
	 * The sequence of operations to perform.
	 */
	protected List<RailwayOperation> railwayOperations;

	/**
	 * A short description of the workload.
	 */
	protected String workload;

	/**
	 * Non-arg constructor for Kryo
	 */
	protected BenchmarkConfigCore() {
	}

	public BenchmarkConfigCore(final String xms, final String xmx, final long timeout, final int runs,
			final int queryTransformationCount, final String modelFilename,
			final List<RailwayOperation> railwayOperations, final String workload) {
		super(xms, xmx);
		this.timeout = timeout;
		this.runs = runs;
		this.queryTransformationCount = queryTransformationCount;
		this.modelFilename = modelFilename;
		this.railwayOperations = railwayOperations;
		this.workload = workload;
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

}
