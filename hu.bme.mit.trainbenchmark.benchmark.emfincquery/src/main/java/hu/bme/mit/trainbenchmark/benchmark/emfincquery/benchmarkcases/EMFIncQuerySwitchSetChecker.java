package hu.bme.mit.trainbenchmark.benchmark.emfincquery.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SwitchSetMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SwitchSetMatcher;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.driver.EMFIncQueryDriver;

import org.eclipse.incquery.runtime.api.IncQueryMatcher;
import org.eclipse.incquery.runtime.exception.IncQueryException;

public class EMFIncQuerySwitchSetChecker extends EMFIncQueryChecker<SwitchSetMatch> {

	public EMFIncQuerySwitchSetChecker(final EMFIncQueryDriver<SwitchSetMatch> eiqDriver) {
		super(eiqDriver);
	}

	@Override
	public IncQueryMatcher<SwitchSetMatch> getMatcher() throws IncQueryException {
		return SwitchSetMatcher.on(eiqDriver.getEngine());
	}

}
