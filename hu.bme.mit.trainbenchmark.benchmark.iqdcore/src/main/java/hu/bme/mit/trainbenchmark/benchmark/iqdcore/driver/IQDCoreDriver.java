package hu.bme.mit.trainbenchmark.benchmark.iqdcore.driver;

import hu.bme.mit.incqueryds.WildcardInput;
import hu.bme.mit.incqueryds.trainbenchmark.TrainbenchmarkReader;
import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.benchmarkcases.IQDCoreChecker;
import hu.bme.mit.trainbenchmark.benchmark.rdf.RDFBenchmarkConfig;
import hu.bme.mit.trainbenchmark.rdf.RDFHelper;

import java.util.Comparator;
import java.util.List;

public class IQDCoreDriver extends Driver<Long> {

	TrainbenchmarkReader reader;
	ResourceComparator comparator;
	private final WildcardInput input;
	private final IQDCoreChecker query;

	public IQDCoreDriver(final RDFBenchmarkConfig rdfbc, final WildcardInput input, final IQDCoreChecker query) {
		super();
		this.input = input;
		this.query = query;
		reader = new TrainbenchmarkReader(input);
		comparator = new ResourceComparator();
	}

	@Override
	public void read(final String modelPathWithoutExtension) throws Exception {
		reader.read(modelPathWithoutExtension + RDFHelper.getPostfix(false));
		query.getQuery().getResults();
	}

	@Override
	public List<Long> collectVertices(final String type) throws Exception {
		return (List<Long>) input.multiValueAttributes().apply(type).keysIterator().toList();
	}

	@Override
	public Comparator<Long> getElementComparator() {
		return comparator;
	}

	@Override
	public String getPostfix() {
		return ".ttl";
	}

	@Override
	public void destroy() {
		query.shutdown();
	}
}
