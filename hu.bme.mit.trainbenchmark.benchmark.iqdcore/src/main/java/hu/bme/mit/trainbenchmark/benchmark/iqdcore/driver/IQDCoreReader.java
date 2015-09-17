package hu.bme.mit.trainbenchmark.benchmark.iqdcore.driver;

import hu.bme.mit.incquerydcore.WildcardInput;
import hu.bme.mit.incquerydcore.trainbenchmark.TrainbenchmarkQuery;
import hu.bme.mit.incquerydcore.trainbenchmark.TrainbenchmarkReader;
import hu.bme.mit.trainbenchmark.benchmark.checker.Checker;
import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.benchmarkcases.IQDCoreChecker;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.match.IQDCoreMatch;
import hu.bme.mit.trainbenchmark.benchmark.rdf.RDFBenchmarkConfig;
import hu.bme.mit.trainbenchmark.constants.Query;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class IQDCoreReader extends Driver<Long> {
	TrainbenchmarkReader reader;
	ResourceComparator comparator;
	private WildcardInput input;
    private IQDCoreChecker query;

    public IQDCoreReader(RDFBenchmarkConfig rdfbc, WildcardInput input, IQDCoreChecker query) {
		super();
		this.input = input;
        this.query = query;
        reader = new TrainbenchmarkReader(input);
		comparator = new ResourceComparator();
	}

	@Override
	public void read(String modelPathWithoutExtension) throws Exception {
		reader.read(modelPathWithoutExtension + ".ttl");
	}

	@Override
	public List<Long> collectVertices(String type) throws Exception {
		return (List<Long>) input.multiValueAttributes().apply(type).keysIterator().toList();
	}

	@Override
	public Comparator<Long> getElementComparator() {
		return comparator;
	}

	@Override
	public Collection<?> runQuery(Query query, String queryDefinition)
			throws Exception {
		throw new UnsupportedOperationException("");
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
