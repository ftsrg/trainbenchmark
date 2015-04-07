package hu.bme.mit.trainbenchmark.benchmark.emfincquery.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.PosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.PosLengthMatcher;

import org.eclipse.incquery.runtime.exception.IncQueryException;

public class EMFIncQueryPosLength extends EMFIncQueryBenchmarkCase<PosLengthMatch> {

	@Override
	protected PosLengthMatcher getMatcher() throws IncQueryException {
		return PosLengthMatcher.on(engine);
	}

}
