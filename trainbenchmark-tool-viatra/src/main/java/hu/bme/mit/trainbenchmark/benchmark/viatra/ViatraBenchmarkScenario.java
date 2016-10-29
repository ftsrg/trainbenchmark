package hu.bme.mit.trainbenchmark.benchmark.viatra;

import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;

import hu.bme.mit.trainbenchmark.benchmark.phases.BenchmarkScenario;
import hu.bme.mit.trainbenchmark.benchmark.viatra.comparators.ViatraMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.viatra.config.ViatraBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.viatra.driver.ViatraDriver;
import hu.bme.mit.trainbenchmark.benchmark.viatra.driver.ViatraDriverFactory;
import hu.bme.mit.trainbenchmark.benchmark.viatra.operations.ViatraModelOperationFactory;

public class ViatraBenchmarkScenario
		extends BenchmarkScenario<BasePatternMatch, ViatraDriver, ViatraBenchmarkConfig> {

	public ViatraBenchmarkScenario(final ViatraBenchmarkConfig bc) throws Exception {
		super(new ViatraDriverFactory(bc.getBackend()), new ViatraModelOperationFactory(), new ViatraMatchComparator(), bc);
	}

}
