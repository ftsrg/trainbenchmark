package hu.bme.mit.trainbenchmark.benchmark.orientdb;

import hu.bme.mit.trainbenchmark.benchmark.orientdb.config.OrientDbConfig;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.driver.OrientDbDriver;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.driver.OrientDbDriverFactory;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.matches.OrientDbMatch;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.matches.OrientDbMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.operations.OrientDbModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.phases.BenchmarkScenario;

public class OrientDbBenchmarkScenario
		extends BenchmarkScenario<Tinker, OrientDbDriver, OrientDbConfig> {

	public OrientDbBenchmarkScenario(final OrientDbConfig bc) throws Exception {
		super(new OrientDbDriverFactory(), new OrientDbModelOperationFactory(), new OrientDbMatchComparator(), bc);
	}

}
