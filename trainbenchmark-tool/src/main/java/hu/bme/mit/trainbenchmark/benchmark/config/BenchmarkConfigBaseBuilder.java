package hu.bme.mit.trainbenchmark.benchmark.config;

import java.util.List;

import com.google.common.base.Preconditions;

import hu.bme.mit.trainbenchmark.constants.RailwayOperation;

public final class BenchmarkConfigBaseBuilder {
	private Integer benchmarkId;
	private Long timeout;
	private Integer runs;
	private Integer queryTransformationCount;
	private String modelFilename;
	private List<RailwayOperation> operations;
	private String workload;
	private TransformationChangeSetStrategy transformationChangeSetStrategy;
	private Integer transformationConstant;

	public BenchmarkConfigBaseBuilder setTimeout(final Long timeout) {
		this.timeout = timeout;
		return this;
	}

	public BenchmarkConfigBaseBuilder setRuns(final Integer runs) {
		this.runs = runs;
		return this;
	}

	public BenchmarkConfigBaseBuilder setQueryTransformationCount(final Integer queryTransformationCount) {
		this.queryTransformationCount = queryTransformationCount;
		return this;
	}

	public BenchmarkConfigBaseBuilder setModelFilename(final String modelFilename) {
		this.modelFilename = modelFilename;
		return this;
	}

	public BenchmarkConfigBaseBuilder setOperations(final List<RailwayOperation> operations) {
		this.operations = operations;
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

	public BenchmarkConfigBaseBuilder setTransformationConstant(final Integer transformationConstant) {
		this.transformationConstant = transformationConstant;
		return this;
	}

	public BenchmarkConfigBaseBuilder setBenchmarkId(final Integer benchmarkId) {
		this.benchmarkId = benchmarkId;
		return this;
	}

	public BenchmarkConfigBase createConfigBase() {
		Preconditions.checkNotNull(benchmarkId);
		Preconditions.checkNotNull(timeout);
		Preconditions.checkNotNull(runs);
		Preconditions.checkNotNull(queryTransformationCount);
		Preconditions.checkNotNull(modelFilename);
		Preconditions.checkNotNull(operations);
		Preconditions.checkNotNull(workload);
		Preconditions.checkNotNull(transformationChangeSetStrategy);
		Preconditions.checkNotNull(transformationConstant);
		return new BenchmarkConfigBase(benchmarkId, timeout, runs, queryTransformationCount, modelFilename, operations, workload, transformationChangeSetStrategy, transformationConstant);
	}
}
