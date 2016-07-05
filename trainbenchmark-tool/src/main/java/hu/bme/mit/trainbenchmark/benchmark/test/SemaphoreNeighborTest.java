package hu.bme.mit.trainbenchmark.benchmark.test;

import java.util.Collection;

import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ListMultimap;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigCore;
import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkResult;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public abstract class SemaphoreNeighborTest {

	@Rule
	public ErrorCollector collector = new ErrorCollector();

	protected static BenchmarkConfigCore bc;

	@BeforeClass
	public static void init() {
		final String xms = "1G";
		final String xmx = "1G";
		final long timeout = 120;
		final int runs = 2;
		final int queryTransformationCount = 1;
		final String modelFilename = "railway-repair-1";
		final Collection<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.SEMAPHORENEIGHBOR_REPAIR //
		);
		final String workload = "SemaphoreNeighborTest";
		bc = new BenchmarkConfigCore(xms, xmx, timeout, runs, queryTransformationCount, modelFilename, operations,
				workload);
	}

	@Test
	public void checkResult() throws Exception {
		final BenchmarkResult result = runTest();
		System.out.println(result);
		System.out.println(result.csvMatches());
		System.out.println(result.csvTimes());

		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		collector.checkThat(allMatches.get(RailwayQuery.SEMAPHORENEIGHBOR).get(0), Matchers.equalTo(3));
		collector.checkThat(allMatches.get(RailwayQuery.SEMAPHORENEIGHBOR).get(1), Matchers.equalTo(0));
	}

	protected abstract BenchmarkResult runTest() throws Exception;

}
