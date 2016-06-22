package hu.bme.mit.trainbenchmark.benchmark.iqdcore.config;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigCore;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigWrapper;

public class IQDBenchmarkConfigWrapper extends BenchmarkConfigWrapper {

	protected int messageSize;

    protected IQDBenchmarkConfigWrapper() {
    }

    public IQDBenchmarkConfigWrapper(final BenchmarkConfigCore config, final int messageSize) {
        super(config);
        this.messageSize = messageSize;
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
}
