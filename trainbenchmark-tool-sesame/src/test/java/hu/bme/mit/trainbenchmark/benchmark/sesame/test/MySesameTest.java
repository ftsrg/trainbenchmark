package hu.bme.mit.trainbenchmark.benchmark.sesame.test;

import java.util.Collection;

import org.junit.Test;

import com.google.common.collect.ImmutableList;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.executor.BenchmarkResult;
import hu.bme.mit.trainbenchmark.benchmark.phases.BenchmarkScenario;
import hu.bme.mit.trainbenchmark.benchmark.rdf.RdfBenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.sesame.comparators.SesameMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameMatch;
import hu.bme.mit.trainbenchmark.benchmark.sesame.operations.SesameModelOperationFactory;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;

public class MySesameTest {

	@Test
	public void hello() throws Exception {
		final boolean inferencing = true;

		final String xms = "1G";
		final String xmx = "1G";
		final long timeout = 10;
		final int runs = 1;
		final int queryTransformatioCount = 1;
		final String modelPath = "../models/railway-batch-1";
		final Collection<RailwayOperation> railwayOperations = ImmutableList.of(RailwayOperation.POSLENGTH);
		final BenchmarkConfig bc = new BenchmarkConfig(xms, xmx, timeout, runs, queryTransformatioCount, modelPath,
				railwayOperations);
		final RdfBenchmarkConfigWrapper rbcw = new RdfBenchmarkConfigWrapper(bc, inferencing);

		final SesameDriver driver = SesameDriver.create(rbcw.isInferencing());
		final SesameModelOperationFactory factory = SesameModelOperationFactory.create();
		final SesameMatchComparator comparator = SesameMatchComparator.create();

		final BenchmarkScenario<SesameMatch, SesameDriver, BenchmarkConfigWrapper> scenario = new BenchmarkScenario<>(
				driver, factory, comparator, rbcw);
		final BenchmarkResult benchmark = scenario.runBenchmark();
		System.out.println(benchmark);

	}

}
