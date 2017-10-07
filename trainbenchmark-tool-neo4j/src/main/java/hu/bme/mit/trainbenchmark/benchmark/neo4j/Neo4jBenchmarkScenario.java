package hu.bme.mit.trainbenchmark.benchmark.neo4j;

import hu.bme.mit.trainbenchmark.benchmark.neo4j.comparators.Neo4jMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.config.Neo4jBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriverFactory;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.operations.Neo4jModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.phases.BenchmarkScenario;

public class Neo4jBenchmarkScenario extends BenchmarkScenario<Neo4jMatch, Neo4jDriver, Neo4jBenchmarkConfig> {

	public Neo4jBenchmarkScenario(final Neo4jBenchmarkConfig bc) throws Exception {
		super(
			new Neo4jDriverFactory(
				bc.getConfigBase().getModelDir(),
				bc.getDeployment(),
				bc.getGraphFormat()
			),
			new Neo4jModelOperationFactory(bc.getEngine()),
			new Neo4jMatchComparator(),
			bc
		);
	}

}
