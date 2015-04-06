package hu.bme.mit.trainbenchmark.benchmark.emfincquery.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SwitchSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SwitchSensorMatcher;
import hu.bme.mit.trainbenchmark.railway.Switch;

import java.util.Set;

import org.eclipse.incquery.runtime.api.IncQueryMatcher;
import org.eclipse.incquery.runtime.exception.IncQueryException;

public class EMFIncQuerySwitchSensor extends EMFIncQueryBenchmarkCase<Switch, SwitchSensorMatch> {

	@Override
	protected Set<Switch> getResultSet() throws IncQueryException {
		return SwitchSensorMatcher.on(engine).getAllValuesOfsw();
	}

	@Override
	protected IncQueryMatcher<SwitchSensorMatch> getMatcher() throws IncQueryException {
		return SwitchSensorMatcher.on(engine);
	}

	@Override
	protected Switch extract(final SwitchSensorMatch match) {
		return match.getSw();
	}
	
}
