package hu.bme.mit.trainbenchmark.benchmark.emfincquery.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SwitchSetMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SwitchSetMatcher;

import org.eclipse.incquery.runtime.api.IncQueryMatcher;
import org.eclipse.incquery.runtime.exception.IncQueryException;

public class EMFIncQuerySwitchSet extends EMFIncQueryBenchmarkCase<SwitchSetMatch> {

	@Override
	protected IncQueryMatcher<SwitchSetMatch> getMatcher() throws IncQueryException {
		return SwitchSetMatcher.on(engine);
	}

}
