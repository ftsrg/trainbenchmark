package hu.bme.mit.trainbenchmark.benchmark.sesame.test;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

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
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class MySesameTest {

	@Rule
	public ErrorCollector collector = new ErrorCollector();

	@Test
	public void batchTest() throws Exception {
		final boolean inferencing = true;

		final String xms = "1G";
		final String xmx = "1G";
		final long timeout = 10;
		final int runs = 1;
		final int queryTransformatioCount = 0;
		final String modelPath = "../models/railway-repair-1";
		final Collection<RailwayOperation> railwayOperations = ImmutableList.of(//
				RailwayOperation.CONNECTEDSEGMENTS, //
				RailwayOperation.POSLENGTH, //
				RailwayOperation.ROUTESENSOR, //
				RailwayOperation.SEMAPHORENEIGHBOR, //
				RailwayOperation.SWITCHMONITORED, //
				RailwayOperation.SWITCHSET);
		final BenchmarkConfig bc = new BenchmarkConfig(xms, xmx, timeout, runs, queryTransformatioCount, modelPath,
				railwayOperations);
		final RdfBenchmarkConfigWrapper rbcw = new RdfBenchmarkConfigWrapper(bc, inferencing);

		final SesameDriver driver = SesameDriver.create(rbcw.isInferencing());
		final SesameModelOperationFactory factory = SesameModelOperationFactory.create();
		final SesameMatchComparator comparator = SesameMatchComparator.create();

		final BenchmarkScenario<SesameMatch, SesameDriver, BenchmarkConfigWrapper> scenario = new BenchmarkScenario<>(
				driver, factory, comparator, rbcw);
		final BenchmarkResult result = scenario.runBenchmark();

		Assert.assertEquals(8, (int) result.getAllMatches().get(RailwayQuery.CONNECTEDSEGMENTS).get(0));
		Assert.assertEquals(78, (int) result.getAllMatches().get(RailwayQuery.POSLENGTH).get(0));
		Assert.assertEquals(12, (int) result.getAllMatches().get(RailwayQuery.ROUTESENSOR).get(0));
		Assert.assertEquals(9, (int) result.getAllMatches().get(RailwayQuery.SEMAPHORENEIGHBOR).get(0));
		Assert.assertEquals(1, (int) result.getAllMatches().get(RailwayQuery.SWITCHMONITORED).get(0));
		Assert.assertEquals(2, (int) result.getAllMatches().get(RailwayQuery.SWITCHSET).get(0));
	}

}
