package hu.bme.mit.trainbenchmark.benchmark.config;

import com.google.api.client.repackaged.com.google.common.base.Preconditions;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;

import java.util.List;

public class BenchmarkConfigBaseBuilder {
	private Long timeout;
	private Integer runs;
	private Integer queryTransformationCount;
	private String modelFilename;
	private List<RailwayOperation> railwayOperations;
	private String workload;
	private TransformationChangeSetStrategy transformationChangeSetStrategy;
	private Integer transformationConstant;

	public BenchmarkConfigBaseBuilder setTimeout(long timeout) {
		this.timeout = timeout;
		return this;
	}

	public BenchmarkConfigBaseBuilder setRuns(int runs) {
		this.runs = runs;
		return this;
	}

	public BenchmarkConfigBaseBuilder setQueryTransformationCount(int queryTransformationCount) {
		this.queryTransformationCount = queryTransformationCount;
		return this;
	}

	public BenchmarkConfigBaseBuilder setModelFilename(String modelFilename) {
		this.modelFilename = modelFilename;
		return this;
	}

	public BenchmarkConfigBaseBuilder setRailwayOperations(List<RailwayOperation> railwayOperations) {
		this.railwayOperations = railwayOperations;
		return this;
	}

	public BenchmarkConfigBaseBuilder setWorkload(String workload) {
		this.workload = workload;
		return this;
	}

	public BenchmarkConfigBaseBuilder setTransformationChangeSetStrategy(TransformationChangeSetStrategy transformationChangeSetStrategy) {
		this.transformationChangeSetStrategy = transformationChangeSetStrategy;
		return this;
	}

	public BenchmarkConfigBaseBuilder setTransformationConstant(int transformationConstant) {
		this.transformationConstant = transformationConstant;
		return this;
	}

	public BenchmarkConfigBase createBenchmarkConfigBase() {
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
