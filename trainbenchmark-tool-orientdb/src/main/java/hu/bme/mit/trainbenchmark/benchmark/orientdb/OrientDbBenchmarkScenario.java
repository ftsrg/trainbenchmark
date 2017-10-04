package hu.bme.mit.trainbenchmark.benchmark.orientdb;

import hu.bme.mit.trainbenchmark.benchmark.orientdb.config.OrientDbBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.driver.OrientDbDriver;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.driver.OrientDbDriverFactory;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.operations.OrientDbModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.phases.BenchmarkScenario;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.comparators.TinkerGraphMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphMatch;

public class OrientDbBenchmarkScenario
		extends BenchmarkScenario<TinkerGraphMatch, OrientDbDriver, OrientDbBenchmarkConfig> {

	public OrientDbBenchmarkScenario(final OrientDbBenchmarkConfig bc) throws Exception {
		super(new OrientDbDriverFactory(), new OrientDbModelOperationFactory(bc.getEngine()), new TinkerGraphMatchComparator(), bc);
	}

}
