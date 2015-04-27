package hu.bme.mit.trainbenchmark.benchmark.emfincquery.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SwitchSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SwitchSensorMatcher;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.driver.EMFIncQueryDriver;

import org.eclipse.incquery.runtime.api.IncQueryMatcher;
import org.eclipse.incquery.runtime.exception.IncQueryException;

public class EMFIncQuerySwitchSensorChecker extends EMFIncQueryChecker<SwitchSensorMatch> {

	public EMFIncQuerySwitchSensorChecker(final EMFIncQueryDriver<SwitchSensorMatch> eiqDriver) {
		super(eiqDriver);
	}

	@Override
	public IncQueryMatcher<SwitchSensorMatch> getMatcher() throws IncQueryException {
		return SwitchSensorMatcher.on(eiqDriver.getEngine());
	}

}
