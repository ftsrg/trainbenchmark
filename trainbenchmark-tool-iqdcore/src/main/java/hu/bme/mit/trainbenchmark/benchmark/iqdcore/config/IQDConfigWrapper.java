package hu.bme.mit.trainbenchmark.benchmark.iqdcore.config;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigWrapper;

public class IQDConfigWrapper extends BenchmarkConfigWrapper {
    protected int messageSize;

    protected IQDConfigWrapper() {
    }

    public IQDConfigWrapper(final BenchmarkConfig config, final int messageSize) {
        super(config);
        this.messageSize = messageSize;
    }

    public int getMessageSize() {
        return messageSize;
    }
}
