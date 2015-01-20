package hu.bme.mit.trainbenchmark.benchmark.fourstore.benchmarkcases;

import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.rdf.RDFConstants;

import java.io.IOException;

import com.google.common.collect.Multimap;

public class SegmentLength extends FourStoreJavaBenchmarkCase {

	@Override
	public String getName() {
		return "SegmentLength";
	}

	@Override
	public void check() throws IOException {
		bmr.startStopper();

		Multimap<Long, Object> properties = driver.collectProperties(RDFConstants.BASE_PREFIX + ModelConstants.SEGMENT_LENGTH);

		bmr.addInvalid(properties.size());
		bmr.addCheckTime();
	}
}
