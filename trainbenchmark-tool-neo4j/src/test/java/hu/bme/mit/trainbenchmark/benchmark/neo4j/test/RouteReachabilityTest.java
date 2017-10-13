package hu.bme.mit.trainbenchmark.benchmark.neo4j.test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ListMultimap;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBase;
import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkResult;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.List;

public class RouteReachabilityTest extends Neo4jTest {

	@Test
	public void routeReachabilityTest() throws Exception {
		// Arrange
		final String workload = "RouteReachabilityTest";
		final String modelFilename = "railway-repair-" + largeSize;
		final List<RailwayOperation> operations = ImmutableList.of(//
			RailwayOperation.ROUTEREACHABILITY //
		);
		final BenchmarkConfigBase bcb = bcbbTransformation.setModelFilename(modelFilename).setOperations(operations)
			.setWorkload(workload).createConfigBase();

		// Act
		final BenchmarkResult result = runTest(bcb);

		// Assert
		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		collector.checkThat(allMatches.get(RailwayQuery.ROUTEREACHABILITY).get(0), Matchers.equalTo(0));
	}

}
