package hu.bme.mit.trainbenchmark.benchmark.emfincquery.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.ConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.ConnectedSegmentsMatcher;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.driver.EMFIncQueryDriver;

import org.eclipse.incquery.runtime.api.IncQueryMatcher;
import org.eclipse.incquery.runtime.exception.IncQueryException;

public class EMFIncQueryConnectedSegments extends EMFIncQueryChecker {

	public EMFIncQueryConnectedSegments(final EMFIncQueryDriver<ConnectedSegmentsMatch> eiqDriver) {
		super(eiqDriver);
	}

	@Override
	public IncQueryMatcher<ConnectedSegmentsMatch> getMatcher() throws IncQueryException {
		return ConnectedSegmentsMatcher.on(eiqDriver.getEngine());
	}

}
