package hu.bme.mit.trainbenchmark.benchmark.iqdcore.config;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigCore;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigWrapper;

public class IQDConfigWrapper extends BenchmarkConfigWrapper {

	protected int messageSize;

    protected IQDConfigWrapper() {
    }

    public IQDConfigWrapper(final BenchmarkConfigCore config, final int messageSize) {
        super(config);
        this.messageSize = messageSize;
    }

    public int getMessageSize() {
        return messageSize;
    }

	@Override
	public String getToolName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProjectName() {
		// TODO Auto-generated method stub
		return null;
	}
}
