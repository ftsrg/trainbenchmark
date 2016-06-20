package hu.bme.mit.trainbenchmark.benchmark.neo4j;

import hu.bme.mit.trainbenchmark.benchmark.neo4j.comparators.Neo4jMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.config.Neo4jBenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.operations.Neo4jModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.phases.BenchmarkScenario;

public class Neo4jBenchmarkScenario
		extends BenchmarkScenario<Neo4jMatch, Neo4jDriver, Neo4jBenchmarkConfigWrapper> {

	public Neo4jBenchmarkScenario(final Neo4jBenchmarkConfigWrapper dbcw) throws Exception {
		super(Neo4jDriver.create(dbcw.getBenchmarkConfig().getModelDir()), Neo4jModelOperationFactory.create(), Neo4jMatchComparator.create(), dbcw);
	}

}
