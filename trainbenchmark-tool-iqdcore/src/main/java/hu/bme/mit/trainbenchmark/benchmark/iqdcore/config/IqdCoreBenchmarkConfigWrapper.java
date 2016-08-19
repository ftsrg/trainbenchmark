package hu.bme.mit.trainbenchmark.benchmark.iqdcore.config;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigCore;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigWrapper;

public class IqdCoreBenchmarkConfigWrapper extends BenchmarkConfigWrapper {

	protected int messageSize;
	protected String queryVariant;
	protected String memoryMeasurementPath;

	protected IqdCoreBenchmarkConfigWrapper() {
	}

	public IqdCoreBenchmarkConfigWrapper(final BenchmarkConfigCore bcc, final int messageSize, final String queryVariant,
			final String memoryMeasurementPath) {
		super(bcc);
		this.messageSize = messageSize;
		this.queryVariant = queryVariant;
		this.memoryMeasurementPath = memoryMeasurementPath;
	}

	public String getFileName() {
		return bcc.getModelFilename();
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
