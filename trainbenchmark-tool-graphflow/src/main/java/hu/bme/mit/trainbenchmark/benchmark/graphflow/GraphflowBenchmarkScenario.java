package hu.bme.mit.trainbenchmark.benchmark.graphflow;

import hu.bme.mit.trainbenchmark.benchmark.graphflow.comparators.GraphflowMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.config.GraphflowBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.driver.GraphflowDriver;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.driver.GraphflowDriverFactory;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.matches.GraphflowMatch;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.operations.GraphflowModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.phases.BenchmarkScenario;

public class GraphflowBenchmarkScenario extends BenchmarkScenario<GraphflowMatch, GraphflowDriver, GraphflowBenchmarkConfig> {

	public GraphflowBenchmarkScenario(final GraphflowBenchmarkConfig bc) throws Exception {
		super( //
			new GraphflowDriverFactory(bc.getConfigBase().getModelDir()), //
			new GraphflowModelOperationFactory(), //
			new GraphflowMatchComparator(), //
			bc //
		);
	}

}
