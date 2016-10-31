package hu.bme.mit.trainbenchmark.benchmark.ingraph.config;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBase;

public class IngraphBenchmarkConfig extends BenchmarkConfig {

	protected int messageSize;
	protected String queryVariant;
	protected String memoryMeasurementPath;

	protected IngraphBenchmarkConfig() {
	}

	public IngraphBenchmarkConfig(final BenchmarkConfigBase bcb, final int messageSize, final String queryVariant,
			final String memoryMeasurementPath) {
		super(bcb);
		this.messageSize = messageSize;
		this.queryVariant = queryVariant;
		this.memoryMeasurementPath = memoryMeasurementPath;
	}

	public String getFileName() {
		return getConfigBase().getModelFilename();
	}

	public int getMessageSize() {
		return messageSize;
	}

	@Override
	public String getToolName() {
		return "Ingraph";
	}

	@Override
	public String getProjectName() {
		return "ingraph";
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
