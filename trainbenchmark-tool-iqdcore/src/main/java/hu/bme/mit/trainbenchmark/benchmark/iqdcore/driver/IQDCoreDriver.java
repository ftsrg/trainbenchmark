package hu.bme.mit.trainbenchmark.benchmark.iqdcore.driver;

import java.util.List;

import hu.bme.mit.incqueryds.WildcardInput;
import hu.bme.mit.incqueryds.trainbenchmark.TrainbenchmarkQuery;
import hu.bme.mit.incqueryds.trainbenchmark.TrainbenchmarkReader;
import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;

public class IQDCoreDriver extends Driver<Long> {

	protected final WildcardInput input;
	protected final TrainbenchmarkReader reader;
	protected final String variant;
	private TrainbenchmarkQuery query;

	public IQDCoreDriver(final String variant, final WildcardInput input) {
		super();
		this.input = input;
		this.reader = new TrainbenchmarkReader(input);
		this.variant = variant;
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
		return "-inferred.ttl";
	}

	public void setQuery(TrainbenchmarkQuery query) {
		this.query = query;
	}
	@Override
	public void destroy() {
		query.shutdown();
	}

	public WildcardInput.BatchTransaction newTransaction() {
		return input.newContinousTransaction();
	}

	public long newKey() {
		return input.newKey();
	}
	
	public String getVariant() {
		return variant;
	}
	
}
