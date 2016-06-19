package hu.bme.mit.trainbenchmark.benchmark.test;

import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import com.google.common.collect.ImmutableList;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigCore;
import hu.bme.mit.trainbenchmark.benchmark.executor.BenchmarkResult;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;

public abstract class BaseTest {

	@Rule
	public ErrorCollector collector = new ErrorCollector();
	
	protected static BenchmarkConfigCore bc;

	@BeforeClass
	public static void init() {
		final String xms = "1G";
		final String xmx = "1G";
		final long timeout = 100;
		final int runs = 2;
		final int queryTransformatioCount = 2;
		final String modelFilename = "railway-repair-1";
		final Collection<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.CONNECTEDSEGMENTS, //
				RailwayOperation.POSLENGTH, //
				RailwayOperation.POSLENGTH_REPAIR, //
				RailwayOperation.ROUTESENSOR, //
				RailwayOperation.SEMAPHORENEIGHBOR, //
				RailwayOperation.SWITCHMONITORED, //
				RailwayOperation.SWITCHSET //
				);
		final String workload = "BatchTest";
		bc = new BenchmarkConfigCore(xms, xmx, timeout, runs, queryTransformatioCount, modelFilename, operations, workload);
	}

	@Test
	public void checkResult() throws Exception {
		final BenchmarkResult result = runTest();
		System.out.println(result);
		System.out.println(result.csvMatches());
		System.out.println(result.csvTimes());
		
//		final ListMultimap<RailwayQuery, Integer> allMatches = result.getAllMatches();
//		collector.checkThat(allMatches.get(RailwayQuery.CONNECTEDSEGMENTS).get(0), Matchers.equalTo(8));
//		collector.checkThat(allMatches.get(RailwayQuery.POSLENGTH).get(0), Matchers.equalTo(78));
//		collector.checkThat(allMatches.get(RailwayQuery.ROUTESENSOR).get(0), Matchers.equalTo(12));
//		collector.checkThat(allMatches.get(RailwayQuery.SEMAPHORENEIGHBOR).get(0), Matchers.equalTo(9));
//		collector.checkThat(allMatches.get(RailwayQuery.SWITCHMONITORED).get(0), Matchers.equalTo(1));
//		collector.checkThat(allMatches.get(RailwayQuery.SWITCHSET).get(0), Matchers.equalTo(2));
	}

	protected abstract BenchmarkResult runTest() throws Exception;

}
