package hu.bme.mit.trainbenchmark.benchmark.emfincquery.benchmarkcases;

import java.util.Set;

import org.eclipse.incquery.runtime.api.IncQueryMatcher;
import org.eclipse.incquery.runtime.exception.IncQueryException;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.ConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.ConnectedSegmentsMatcher;
import hu.bme.mit.trainbenchmark.railway.Segment;

public class EMFIncQueryConnectedSegments extends EMFIncQueryBenchmarkCase<Segment, ConnectedSegmentsMatch>{

	@Override
	protected Set<Segment> getResultSet() throws IncQueryException {
		return ConnectedSegmentsMatcher.on(engine).getAllValuesOfsegment();
	}

	@Override
	protected IncQueryMatcher<ConnectedSegmentsMatch> getMatcher()
			throws IncQueryException {
		return ConnectedSegmentsMatcher.on(engine);
	}

	@Override
	protected Segment extract(ConnectedSegmentsMatch match) {
		return match.getSegment();
	}

}
