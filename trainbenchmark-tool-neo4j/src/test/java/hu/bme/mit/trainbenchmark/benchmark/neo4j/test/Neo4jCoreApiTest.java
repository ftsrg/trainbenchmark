package hu.bme.mit.trainbenchmark.benchmark.neo4j.test;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigCore;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.config.Neo4jBenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.config.Neo4jEngine;

public class Neo4jCoreApiTest extends Neo4jTest {

	@Override
	protected Neo4jBenchmarkConfigWrapper createNeo4jBenchmarkConfigWrapper(final BenchmarkConfigCore bc) {
		return new Neo4jBenchmarkConfigWrapper(bc, Neo4jEngine.COREAPI);
	}

}
