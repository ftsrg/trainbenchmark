package hu.bme.mit.trainbenchmark.benchmark.emfincquery.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.ConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.ConnectedSegmentsMatcher;

import org.eclipse.incquery.runtime.api.IncQueryMatcher;
import org.eclipse.incquery.runtime.exception.IncQueryException;

public class EMFIncQueryConnectedSegments extends EMFIncQueryBenchmarkCase<ConnectedSegmentsMatch> {

	@Override
	protected IncQueryMatcher<ConnectedSegmentsMatch> getMatcher() throws IncQueryException {
		return ConnectedSegmentsMatcher.on(engine);
	}

}
