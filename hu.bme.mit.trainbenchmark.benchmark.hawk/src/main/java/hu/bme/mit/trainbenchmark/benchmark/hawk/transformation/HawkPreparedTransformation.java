package hu.bme.mit.trainbenchmark.benchmark.hawk.transformation;

import java.util.Collection;

import hu.bme.mit.trainbenchmark.benchmark.hawk.config.HawkBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.hawk.driver.HawkDriver;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.constants.Scenario;

public class HawkPreparedTransformation extends HawkTransformation<Object> {

	public HawkPreparedTransformation(HawkDriver<?> driver, Query query, Scenario scenario, HawkBenchmarkConfig hbc) {
		super(driver);
		
		hbc.getWorkspacePath()
		
	}

	@Override
	public void rhs(Collection<Object> objects) throws Exception {
		// use the prepared files to perform the transformation
		
		
	}

}
