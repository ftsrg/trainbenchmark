package hu.bme.mit.trainbenchmark.benchmark.iqdcore.config;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigCore;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigWrapper;

public class IQDCoreBenchmarkConfigWrapper extends BenchmarkConfigWrapper {

	protected int messageSize;
	protected String queryVariant;
	protected String memoryMeasurementPath;

	protected IQDCoreBenchmarkConfigWrapper() {
	}

	public IQDCoreBenchmarkConfigWrapper(final BenchmarkConfigCore config, final int messageSize, final String queryVariant,
			final String memoryMeasurementPath) {
		super(config);
		this.messageSize = messageSize;
		this.queryVariant = queryVariant;
		this.memoryMeasurementPath = memoryMeasurementPath;
	}

	public String getFileName() {
		return benchmarkConfig.getModelFilename();
	}

	public int getMessageSize() {
		return messageSize;
	}

	@Override
	public String getToolName() {
		return "IncQuery-DS";
	}

	@Override
	public String getProjectName() {
		return "iqdcore";
	}

	public String getQueryVariant() {
		return queryVariant;
	}

	public String getMemoryMeasurementPath() {
		return memoryMeasurementPath;
	}

	@Override
	public String getDescription() {
		return queryVariant;
	}

}
