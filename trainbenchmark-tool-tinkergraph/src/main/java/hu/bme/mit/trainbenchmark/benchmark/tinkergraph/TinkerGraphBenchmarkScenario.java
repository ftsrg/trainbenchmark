package hu.bme.mit.trainbenchmark.benchmark.tinkergraph;

import hu.bme.mit.trainbenchmark.benchmark.phases.BenchmarkScenario;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.comparators.TinkerGraphMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.config.TinkerGraphBenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.driver.TinkerGraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.driver.TinkerGraphDriverFactory;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphMatch;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.operations.TinkerGraphModelOperationFactory;

public class TinkerGraphBenchmarkScenario
		extends BenchmarkScenario<TinkerGraphMatch, TinkerGraphDriver, TinkerGraphBenchmarkConfigWrapper> {

	public TinkerGraphBenchmarkScenario(final TinkerGraphBenchmarkConfigWrapper bcw) throws Exception {
		super(new TinkerGraphDriverFactory(), new TinkerGraphModelOperationFactory<TinkerGraphDriver>(), new TinkerGraphMatchComparator(), bcw);
	}

}
