package hu.bme.mit.trainbenchmark.benchmark.viatra;

import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;

import hu.bme.mit.trainbenchmark.benchmark.phases.BenchmarkScenario;
import hu.bme.mit.trainbenchmark.benchmark.viatra.comparators.ViatraMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.viatra.config.ViatraBenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.viatra.driver.ViatraDriver;
import hu.bme.mit.trainbenchmark.benchmark.viatra.driver.ViatraDriverFactory;
import hu.bme.mit.trainbenchmark.benchmark.viatra.operations.ViatraModelOperationFactory;

public class ViatraBenchmarkScenario
		extends BenchmarkScenario<BasePatternMatch, ViatraDriver, ViatraBenchmarkConfigWrapper> {

	public ViatraBenchmarkScenario(final ViatraBenchmarkConfigWrapper bcw) throws Exception {
		super(new ViatraDriverFactory(bcw.getBackend()), new ViatraModelOperationFactory(), new ViatraMatchComparator(), bcw);
	}

}
