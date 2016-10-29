package hu.bme.mit.trainbenchmark.benchmark.test.queryspecific;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ListMultimap;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBase;
import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkResult;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.List;

public abstract class ActiveRouteTest extends QueryTest {

	@Test
	public void testActiveRoute() throws Exception {
		final String xms = "1G";
		final String xmx = "1G";
		final long timeout = 120;
		final int runs = 2;
		final int queryTransformationCount = 0;
		final String modelFilename = "railway-repair-1";
		final List<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.ACTIVEROUTE //
		);
		final String workload = "ActiveRouteTest";
		final BenchmarkConfigBase bcc = new BenchmarkConfigBase(xms, xmx, timeout, runs, queryTransformationCount, modelFilename, operations,
				workload);
		
		final BenchmarkResult result = runTest(bcc);
		System.out.println(result);
		System.out.println(result.csvMatches());
		System.out.println(result.csvTimes());

		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		collector.checkThat(allMatches.get(RailwayQuery.ACTIVEROUTE).get(0), Matchers.equalTo(3));
	}

}
