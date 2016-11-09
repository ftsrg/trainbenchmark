package hu.bme.mit.trainbenchmark.benchmark.test.queryspecific;

import java.util.List;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBaseBuilder;
import org.hamcrest.Matchers;
import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ListMultimap;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBase;
import hu.bme.mit.trainbenchmark.benchmark.config.TransformationChangeSetStrategy;
import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkResult;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public abstract class RouteSensorTest extends QueryTest {

	@Test
	public void testRouteSensor() throws Exception {
		final List<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.ROUTESENSOR_REPAIR //
		);
		final String workload = "RouteSensorTest";
		final BenchmarkConfigBase bcb = new BenchmarkConfigBaseBuilder().setTimeout(timeout).setRuns(runs).setQueryTransformationCount(queryTransformationCount).setModelFilename(modelFilename).setRailwayOperations(operations).setWorkload(workload).setTransformationChangeSetStrategy(TransformationChangeSetStrategy.FIXED).setTransformationConstant(10).createBenchmarkConfigBase();

		final BenchmarkResult result = performBenchmark(bcb);

		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		collector.checkThat(allMatches.get(RailwayQuery.ROUTESENSOR).get(0), Matchers.equalTo(18));
		collector.checkThat(allMatches.get(RailwayQuery.ROUTESENSOR).get(1), Matchers.equalTo(8));
	}

}
