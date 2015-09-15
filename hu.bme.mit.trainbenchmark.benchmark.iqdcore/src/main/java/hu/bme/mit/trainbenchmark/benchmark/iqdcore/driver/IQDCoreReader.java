package hu.bme.mit.trainbenchmark.benchmark.iqdcore.driver;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import hu.bme.mit.IQDcore.WildcardInput;
import hu.bme.mit.IQDcore.trainbenchmark.TrainbenchmarkReader;
import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.rdf.RDFBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.rdf.RDFDriver;
import hu.bme.mit.trainbenchmark.constants.Query;

public class IQDCoreReader extends Driver<Long> {
	TrainbenchmarkReader reader;
	ResourceComparator comparator;
	private WildcardInput input;
	public IQDCoreReader(RDFBenchmarkConfig rdfbc, WildcardInput input) {
		super();
		this.input = input;
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
}
