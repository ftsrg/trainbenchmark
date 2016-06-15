package hu.bme.mit.trainbenchmark.benchmark.test;

import java.util.Collection;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import com.google.common.collect.ImmutableList;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.executor.BenchmarkResult;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public abstract class BaseTest {

	@Rule
	public ErrorCollector collector = new ErrorCollector();
	
	protected static BenchmarkConfig bc;

	@BeforeClass
	public static void init() {
		final String xms = "1G";
		final String xmx = "1G";
		final long timeout = 100;
		final int runs = 1;
		final int queryTransformatioCount = 0;
		final String modelFilename = "railway-repair-1";
		final Collection<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.CONNECTEDSEGMENTS, //
				RailwayOperation.POSLENGTH, //
				RailwayOperation.ROUTESENSOR, //
				RailwayOperation.SEMAPHORENEIGHBOR, //
				RailwayOperation.SWITCHMONITORED, //
				RailwayOperation.SWITCHSET);
		bc = new BenchmarkConfig(xms, xmx, timeout, runs, queryTransformatioCount, modelFilename, operations);
	}

	@Test
	public void checkResult() throws Exception {
		final BenchmarkResult result = runTest();

		Assert.assertEquals(8, (int) result.getAllMatches().get(RailwayQuery.CONNECTEDSEGMENTS).get(0));
		Assert.assertEquals(78, (int) result.getAllMatches().get(RailwayQuery.POSLENGTH).get(0));
		Assert.assertEquals(12, (int) result.getAllMatches().get(RailwayQuery.ROUTESENSOR).get(0));
		Assert.assertEquals(9, (int) result.getAllMatches().get(RailwayQuery.SEMAPHORENEIGHBOR).get(0));
		Assert.assertEquals(1, (int) result.getAllMatches().get(RailwayQuery.SWITCHMONITORED).get(0));
		Assert.assertEquals(2, (int) result.getAllMatches().get(RailwayQuery.SWITCHSET).get(0));
	}

	protected abstract BenchmarkResult runTest() throws Exception;

}
