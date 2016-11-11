package hu.bme.mit.trainbenchmark.benchmark.config;

import java.util.List;

import com.google.common.base.Preconditions;

import hu.bme.mit.trainbenchmark.constants.RailwayOperation;

public final class BenchmarkConfigBaseBuilder {
	private Long timeout;
	private Integer runs;
	private Integer queryTransformationCount;
	private String modelFilename;
	private List<RailwayOperation> railwayOperations;
	private String workload;
	private TransformationChangeSetStrategy transformationChangeSetStrategy;
	private Integer transformationConstant;

	public BenchmarkConfigBaseBuilder setTimeout(final long timeout) {
		this.timeout = timeout;
		return this;
	}

	public BenchmarkConfigBaseBuilder setRuns(final int runs) {
		this.runs = runs;
		return this;
	}

	public BenchmarkConfigBaseBuilder setQueryTransformationCount(final int queryTransformationCount) {
		this.queryTransformationCount = queryTransformationCount;
		return this;
	}

	public BenchmarkConfigBaseBuilder setModelFilename(final String modelFilename) {
		this.modelFilename = modelFilename;
		return this;
	}

	public BenchmarkConfigBaseBuilder setRailwayOperations(final List<RailwayOperation> railwayOperations) {
		this.railwayOperations = railwayOperations;
		return this;
	}

	public BenchmarkConfigBaseBuilder setWorkload(final String workload) {
		this.workload = workload;
		return this;
	}

	public BenchmarkConfigBaseBuilder setTransformationChangeSetStrategy(final TransformationChangeSetStrategy transformationChangeSetStrategy) {
		this.transformationChangeSetStrategy = transformationChangeSetStrategy;
		return this;
	}

	public BenchmarkConfigBaseBuilder setTransformationConstant(final int transformationConstant) {
		this.transformationConstant = transformationConstant;
		return this;
	}

	public BenchmarkConfigBase createConfigBase() {
		Preconditions.checkNotNull(timeout);
		Preconditions.checkNotNull(runs);
		Preconditions.checkNotNull(queryTransformationCount);
		Preconditions.checkNotNull(modelFilename);
		Preconditions.checkNotNull(railwayOperations);
		Preconditions.checkNotNull(workload);
		Preconditions.checkNotNull(transformationChangeSetStrategy);
		Preconditions.checkNotNull(transformationConstant);
		return new BenchmarkConfigBase(timeout, runs, queryTransformationCount, modelFilename, railwayOperations, workload, transformationChangeSetStrategy, transformationConstant);
	}
}
