package hu.bme.mit.trainbenchmark.benchmark.janusgraph;

import hu.bme.mit.trainbenchmark.benchmark.janusgraph.config.JanusGraphBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.janusgraph.driver.JanusGraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.janusgraph.driver.JanusGraphDriverFactory;
import hu.bme.mit.trainbenchmark.benchmark.phases.BenchmarkScenario;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.comparators.TinkerGraphMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.config.TinkerGraphEngine;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphMatch;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.operations.TinkerGraphModelOperationFactory;

public class JanusGraphBenchmarkScenario
		extends BenchmarkScenario<TinkerGraphMatch, JanusGraphDriver, JanusGraphBenchmarkConfig> {

	public JanusGraphBenchmarkScenario(final JanusGraphBenchmarkConfig bc) throws Exception {
		super(new JanusGraphDriverFactory(), new TinkerGraphModelOperationFactory<>(TinkerGraphEngine.CORE_API), new TinkerGraphMatchComparator(), bc);
	}

}
