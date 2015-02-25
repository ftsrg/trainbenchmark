package hu.bme.mit.trainbenchmark.benchmark.emfincquery.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.PosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.PosLengthMatcher;
import hu.bme.mit.trainbenchmark.railway.Segment;

import java.util.Set;

import org.eclipse.incquery.runtime.exception.IncQueryException;

public class PosLength extends EMFIncQueryBenchmarkCase<Segment, PosLengthMatch> {

	@Override
	protected Set<Segment> getResultSet() throws IncQueryException {
		return getMatcher().getAllValuesOfsegment();
	}
	
	@Override
	protected PosLengthMatcher getMatcher() throws IncQueryException {
		return PosLengthMatcher.on(engine);
	}

	@Override
	protected Segment extract(final PosLengthMatch match) {
		return match.getSegment();
	}
	
}
