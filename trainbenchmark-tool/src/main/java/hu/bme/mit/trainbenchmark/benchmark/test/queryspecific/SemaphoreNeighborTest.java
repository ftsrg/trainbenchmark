package hu.bme.mit.trainbenchmark.benchmark.test.queryspecific;

import org.hamcrest.Matchers;
import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ListMultimap;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBase;
import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkResult;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public abstract class SemaphoreNeighborTest extends QueryTest {

	@Test
	public void testSemaphoreNeighbor() throws Exception {
		bcbb.setWorkload("SemaphoreNeighborTest").setRailwayOperations(ImmutableList.of(RailwayOperation.SEMAPHORENEIGHBOR_REPAIR));
		final BenchmarkConfigBase bcb = bcbb.createConfigBase();
		final BenchmarkResult result = performBenchmark(bcb);

		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		collector.checkThat(allMatches.get(RailwayQuery.SEMAPHORENEIGHBOR).get(0), Matchers.equalTo(3));
		collector.checkThat(allMatches.get(RailwayQuery.SEMAPHORENEIGHBOR).get(1), Matchers.equalTo(0));
	}

}
