package hu.bme.mit.trainbenchmark.benchmark.tinkergraph;

import hu.bme.mit.trainbenchmark.benchmark.phases.BenchmarkScenario;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.comparators.TinkerGraphMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.config.TinkerGraphBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.driver.GraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.driver.TinkerGraphDriverFactory;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphMatch;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.operations.TinkerGraphModelOperationFactory;

public class TinkerGraphBenchmarkScenario
		extends BenchmarkScenario<TinkerGraphMatch, GraphDriver, TinkerGraphBenchmarkConfig> {

	public TinkerGraphBenchmarkScenario(final TinkerGraphBenchmarkConfig bc) throws Exception {
		super(new TinkerGraphDriverFactory(), new TinkerGraphModelOperationFactory<>(bc.getEngine()), new TinkerGraphMatchComparator(), bc);
	}

}
