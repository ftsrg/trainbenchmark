package hu.bme.mit.trainbenchmark.benchmark.fourstore.benchmarkcases;

import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.rdf.RDFConstants;

import java.io.IOException;
import java.util.Set;

import com.google.common.collect.Sets;

public class SwitchNodes extends FourStoreBenchmarkCase {

	@Override
	public String getName() {
		return "SwitchNodes";
	}

	@Override
	public void check() throws IOException {
		bmr.startStopper();

		Set<Long> switches = Sets.newHashSet(driver.collectVertices(RDFConstants.BASE_PREFIX + ModelConstants.SWITCH));

		bmr.addInvalid(switches.size());
		bmr.addCheckTime();
	}

}
