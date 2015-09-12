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

public class IQDCoreReader extends RDFDriver<Long> {
	TrainbenchmarkReader reader;
	ResourceComparator comparator;
	public IQDCoreReader(RDFBenchmarkConfig rdfbc, WildcardInput input) {
		super(rdfbc);
		reader = new TrainbenchmarkReader(input);
		comparator = new ResourceComparator();
	}

	@Override
	protected boolean ask(String askQuery) throws Exception {
		return false;
	}

	@Override
	public void read(String modelPathWithoutExtension) throws Exception {
		reader.read(modelPathWithoutExtension + ".ttl");
	}

	@Override
	public List<Long> collectVertices(String type) throws Exception {
		return null;
	}

	@Override
	public Comparator<Long> getElementComparator() {
		return comparator;
	}

	@Override
	public Collection<?> runQuery(Query query, String queryDefinition)
			throws Exception {
		return null;
	}
}
