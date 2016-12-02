package hu.bme.mit.trainbenchmark.benchmark.config;

import java.util.List;
import java.util.Optional;

import com.google.common.base.Preconditions;

import hu.bme.mit.trainbenchmark.constants.RailwayOperation;

public final class BenchmarkConfigBaseBuilder {
	private Integer benchmarkId;
	private Long timeout;
	private Integer runs;
	private String modelFilename;
	private List<RailwayOperation> operations;
	private String workload;
	private TransformationChangeSetStrategy transformationChangeSetStrategy = TransformationChangeSetStrategy.NONE;
	private Optional<Integer> queryTransformationCount = Optional.empty();
	private Optional<Integer> transformationConstant = Optional.empty();

	public BenchmarkConfigBaseBuilder setTimeout(final Long timeout) {
		this.timeout = timeout;
		return this;
	}

	public BenchmarkConfigBaseBuilder setRuns(final Integer runs) {
		this.runs = runs;
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

	public BenchmarkConfigBaseBuilder setTransformationChangeSetStrategy(
			final TransformationChangeSetStrategy transformationChangeSetStrategy) {
		if (transformationChangeSetStrategy != null) {
			this.transformationChangeSetStrategy = transformationChangeSetStrategy;
		}
		return this;
	}

	public BenchmarkConfigBaseBuilder setQueryTransformationCount(final Integer queryTransformationCount) {
		if (queryTransformationCount != null) {
			this.queryTransformationCount = Optional.of(queryTransformationCount);
		}
		return this;
	}

	public BenchmarkConfigBaseBuilder setTransformationConstant(final Integer transformationConstant) {
		if (transformationConstant != null) {
			this.transformationConstant = Optional.of(transformationConstant);
		}
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
		Preconditions.checkNotNull(modelFilename);
		Preconditions.checkNotNull(operations);
		Preconditions.checkNotNull(workload);
		Preconditions.checkNotNull(transformationChangeSetStrategy);
		Preconditions.checkNotNull(queryTransformationCount);
		Preconditions.checkNotNull(transformationConstant);
		return new BenchmarkConfigBase(benchmarkId, timeout, runs, modelFilename, operations, workload,
				transformationChangeSetStrategy, queryTransformationCount, transformationConstant);
	}
}
