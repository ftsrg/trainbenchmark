package hu.bme.mit.trainbenchmark.benchmark.emfincquery.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SemaphoreNeighborMatcher;

import org.eclipse.incquery.runtime.api.IncQueryMatcher;
import org.eclipse.incquery.runtime.exception.IncQueryException;

public class EMFIncQuerySemaphoreNeighbor extends EMFIncQueryBenchmarkCase<SemaphoreNeighborMatch> {

	@Override
	protected IncQueryMatcher<SemaphoreNeighborMatch> getMatcher() throws IncQueryException {
		return SemaphoreNeighborMatcher.on(engine);
	}

}
