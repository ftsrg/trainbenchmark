package hu.bme.mit.trainbenchmark.benchmark.iqdcore.config;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigCore;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigWrapper;

public class IQDBenchmarkConfigWrapper extends BenchmarkConfigWrapper {

	protected int messageSize;
	protected String queryVariant;

    protected IQDBenchmarkConfigWrapper() {
    }

    public IQDBenchmarkConfigWrapper(final BenchmarkConfigCore config, final int messageSize, final String queryVariant) {
        super(config);
        this.messageSize = messageSize;
		this.queryVariant = queryVariant;
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
	
	@Override
	public String getDescription() {
		return queryVariant;
	}
	
}
