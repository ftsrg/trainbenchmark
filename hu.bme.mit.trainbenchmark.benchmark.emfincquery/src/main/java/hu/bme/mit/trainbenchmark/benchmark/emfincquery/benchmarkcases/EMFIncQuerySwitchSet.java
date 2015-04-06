package hu.bme.mit.trainbenchmark.benchmark.emfincquery.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SwitchSetMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SwitchSetMatcher;
import hu.bme.mit.trainbenchmark.railway.SwitchPosition;

import java.util.Set;

import org.eclipse.incquery.runtime.api.IncQueryMatcher;
import org.eclipse.incquery.runtime.exception.IncQueryException;

public class EMFIncQuerySwitchSet extends EMFIncQueryBenchmarkCase<SwitchPosition, SwitchSetMatch> {

	@Override
	protected Set<SwitchPosition> getResultSet() throws IncQueryException {
		return SwitchSetMatcher.on(engine).getAllValuesOfswitchPosition();
	}

	@Override
	protected IncQueryMatcher<SwitchSetMatch> getMatcher() throws IncQueryException {
		return SwitchSetMatcher.on(engine);
	}

	@Override
	protected SwitchPosition extract(final SwitchSetMatch match) {
		return match.getSwitchPosition();
	}
	
}
