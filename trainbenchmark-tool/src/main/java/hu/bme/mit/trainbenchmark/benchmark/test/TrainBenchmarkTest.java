package hu.bme.mit.trainbenchmark.benchmark.test;

import java.util.Collection;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ListMultimap;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigCore;
import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkResult;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public abstract class TrainBenchmarkTest {

	@Rule
	public ErrorCollector collector = new ErrorCollector();

	protected abstract BenchmarkResult runTest(BenchmarkConfigCore bc) throws Exception;

	@Test
	public void batchTest() throws Exception {
		// Arrange
		final String xms = "1G";
		final String xmx = "1G";
		final long timeout = 120;
		final int runs = 1;
		final int queryTransformationCount = 0;
		final String modelFilename = "railway-batch-1";
		final Collection<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.CONNECTEDSEGMENTS, //
				RailwayOperation.POSLENGTH, //
				RailwayOperation.ROUTESENSOR, //
				RailwayOperation.SEMAPHORENEIGHBOR, //
				RailwayOperation.SWITCHMONITORED, //
				RailwayOperation.SWITCHSET //
		);
		final String workload = "BatchTest";
		final BenchmarkConfigCore bc = new BenchmarkConfigCore(xms, xmx, timeout, runs, queryTransformationCount, modelFilename, operations,
				workload);

		// Act
		final BenchmarkResult result = runTest(bc);

		// Assert
		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		collector.checkThat(allMatches.get(RailwayQuery.CONNECTEDSEGMENTS).get(0), Matchers.equalTo(0));
		collector.checkThat(allMatches.get(RailwayQuery.POSLENGTH).get(0), Matchers.equalTo(0));
		collector.checkThat(allMatches.get(RailwayQuery.ROUTESENSOR).get(0), Matchers.equalTo(0));
		collector.checkThat(allMatches.get(RailwayQuery.SEMAPHORENEIGHBOR).get(0), Matchers.equalTo(0));
		collector.checkThat(allMatches.get(RailwayQuery.SWITCHMONITORED).get(0), Matchers.equalTo(0));
		collector.checkThat(allMatches.get(RailwayQuery.SWITCHSET).get(0), Matchers.equalTo(0));
	}

	@Test
	public void repairTest() throws Exception {
		// Arrange
		final String xms = "1G";
		final String xmx = "1G";
		final long timeout = 120;
		final int runs = 1;
		final int queryTransformationCount = 1;
		final String modelFilename = "railway-repair-1";
		final Collection<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.CONNECTEDSEGMENTS_REPAIR, //
				RailwayOperation.POSLENGTH_REPAIR, //
				RailwayOperation.ROUTESENSOR_REPAIR, //
				RailwayOperation.SEMAPHORENEIGHBOR_REPAIR, //
				RailwayOperation.SWITCHMONITORED_REPAIR, //
				RailwayOperation.SWITCHSET_REPAIR //
		);
		final String workload = "RepairTest";
		final BenchmarkConfigCore bc = new BenchmarkConfigCore(xms, xmx, timeout, runs, queryTransformationCount, modelFilename, operations,
				workload);

		// Act
		final BenchmarkResult result = runTest(bc);

		// Assert
		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		collector.checkThat(allMatches.get(RailwayQuery.CONNECTEDSEGMENTS).get(0), Matchers.equalTo(8));
		collector.checkThat(allMatches.get(RailwayQuery.CONNECTEDSEGMENTS).get(1), Matchers.equalTo(0));
		collector.checkThat(allMatches.get(RailwayQuery.POSLENGTH).get(0), Matchers.equalTo(95));
		collector.checkThat(allMatches.get(RailwayQuery.POSLENGTH).get(1), Matchers.equalTo(85));
		collector.checkThat(allMatches.get(RailwayQuery.ROUTESENSOR).get(0), Matchers.equalTo(18));
		collector.checkThat(allMatches.get(RailwayQuery.ROUTESENSOR).get(1), Matchers.equalTo(8));
		collector.checkThat(allMatches.get(RailwayQuery.SEMAPHORENEIGHBOR).get(0), Matchers.equalTo(3));
		collector.checkThat(allMatches.get(RailwayQuery.SEMAPHORENEIGHBOR).get(1), Matchers.equalTo(0));
		collector.checkThat(allMatches.get(RailwayQuery.SWITCHMONITORED).get(0), Matchers.equalTo(0));
		collector.checkThat(allMatches.get(RailwayQuery.SWITCHMONITORED).get(1), Matchers.equalTo(0));
		collector.checkThat(allMatches.get(RailwayQuery.SWITCHSET).get(0), Matchers.equalTo(5));
		collector.checkThat(allMatches.get(RailwayQuery.SWITCHSET).get(1), Matchers.equalTo(0));
	}

	// TODO the inject transformations should be checked separately as they interfere with each other
	@Test
	public void injectTest() throws Exception {
		// Arrange
		final String xms = "1G";
		final String xmx = "1G";
		final long timeout = 120;
		final int runs = 1;
		final int queryTransformationCount = 1;
		final String modelFilename = "railway-inject-1";
		final Collection<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.CONNECTEDSEGMENTS, //
				RailwayOperation.CONNECTEDSEGMENTS_INJECT, //
				RailwayOperation.POSLENGTH, //
				RailwayOperation.POSLENGTH_INJECT, //
				RailwayOperation.ROUTESENSOR, //
				RailwayOperation.ROUTESENSOR_INJECT, //
				RailwayOperation.SEMAPHORENEIGHBOR, //
				RailwayOperation.SEMAPHORENEIGHBOR_INJECT, //
				RailwayOperation.SWITCHMONITORED, //
				RailwayOperation.SWITCHMONITORED_INJECT, //
				RailwayOperation.SWITCHSET, //
				RailwayOperation.SWITCHSET_INJECT //
		);
		final String workload = "InjectTest";
		final BenchmarkConfigCore bc = new BenchmarkConfigCore(xms, xmx, timeout, runs, queryTransformationCount, modelFilename, operations,
				workload);

		// Act
		final BenchmarkResult result = runTest(bc);

		// Assert
		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		collector.checkThat(allMatches.get(RailwayQuery.CONNECTEDSEGMENTS).get(0), Matchers.equalTo(8));
		collector.checkThat(allMatches.get(RailwayQuery.CONNECTEDSEGMENTS).get(1), Matchers.equalTo(18));
		collector.checkThat(allMatches.get(RailwayQuery.POSLENGTH).get(0), Matchers.equalTo(18));
		collector.checkThat(allMatches.get(RailwayQuery.POSLENGTH).get(1), Matchers.equalTo(28));
		collector.checkThat(allMatches.get(RailwayQuery.ROUTESENSOR).get(0), Matchers.equalTo(11));
		collector.checkThat(allMatches.get(RailwayQuery.ROUTESENSOR).get(1), Matchers.equalTo(176));
		collector.checkThat(allMatches.get(RailwayQuery.SEMAPHORENEIGHBOR).get(0), Matchers.equalTo(6));
		collector.checkThat(allMatches.get(RailwayQuery.SEMAPHORENEIGHBOR).get(1), Matchers.equalTo(0));
		collector.checkThat(allMatches.get(RailwayQuery.SWITCHMONITORED).get(0), Matchers.equalTo(0));
		collector.checkThat(allMatches.get(RailwayQuery.SWITCHMONITORED).get(1), Matchers.equalTo(10));
		collector.checkThat(allMatches.get(RailwayQuery.SWITCHSET).get(0), Matchers.equalTo(3));
		collector.checkThat(allMatches.get(RailwayQuery.SWITCHSET).get(1), Matchers.equalTo(0));
	}

}
