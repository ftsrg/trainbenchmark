package hu.bme.mit.trainbenchmark.benchmark.test.queryspecific;

import org.junit.Rule;
import org.junit.rules.ErrorCollector;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBase;
import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkResult;

public abstract class QueryTest {

	@Rule
	public ErrorCollector collector = new ErrorCollector();

	protected abstract BenchmarkResult runTest(BenchmarkConfigBase bcc) throws Exception;
	
}
