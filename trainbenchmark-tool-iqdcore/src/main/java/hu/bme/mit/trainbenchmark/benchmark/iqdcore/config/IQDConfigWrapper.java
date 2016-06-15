package hu.bme.mit.trainbenchmark.benchmark.iqdcore.config;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigWrapper;

/**
 * Created by wafle on 6/10/2016.
 */
public class IQDConfigWrapper extends BenchmarkConfigWrapper {
    protected int messageSize;

    protected IQDConfigWrapper() {
    }

    public IQDConfigWrapper(final BenchmarkConfig config, final int messageSize) {
        super(config);
        this.messageSize = messageSize;
    }
}
