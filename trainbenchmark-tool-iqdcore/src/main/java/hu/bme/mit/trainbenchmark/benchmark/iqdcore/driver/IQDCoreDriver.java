package hu.bme.mit.trainbenchmark.benchmark.iqdcore.driver;

import hu.bme.mit.incqueryds.WildcardInput;
import hu.bme.mit.incqueryds.trainbenchmark.TrainbenchmarkReader;

import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.config.IQDConfigWrapper;

import java.util.List;

public class IQDCoreDriver extends Driver<Long> {

	TrainbenchmarkReader reader;
	ResourceComparator comparator;
	private final WildcardInput input;

	public IQDCoreDriver(final IQDConfigWrapper configWrapper, final WildcardInput input) {
		super();
		this.input = input;
		reader = new TrainbenchmarkReader(input);
		comparator = new ResourceComparator();
	}

	@Override
	public void read(final String modelPath) throws Exception {
		reader.read(modelPath);

	}
	@Override
	public List<Long> collectVertices(final String type) throws Exception {
		return null;
	}

	@Override
	public String getPostfix() {
		return ".ttl";
	}

	@Override
	public void destroy() {
		query.shutdown();
	}

	public WildcardInput.Transaction newTransaction() {
		return input.newTransaction();
	}

	public long newKey() {
		return input.newKey();
	}
}
