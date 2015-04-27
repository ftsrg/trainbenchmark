package hu.bme.mit.trainbenchmark.benchmark.emfincquery.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.PosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.PosLengthMatcher;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.driver.EMFIncQueryDriver;

import org.eclipse.incquery.runtime.api.IncQueryMatcher;
import org.eclipse.incquery.runtime.exception.IncQueryException;

public class EMFIncQueryPosLengthChecker extends EMFIncQueryChecker<PosLengthMatch> {

	public EMFIncQueryPosLengthChecker(final EMFIncQueryDriver<PosLengthMatch> eiqDriver) {
		super(eiqDriver);
	}

	@Override
	public IncQueryMatcher<PosLengthMatch> getMatcher() throws IncQueryException {
		return PosLengthMatcher.on(eiqDriver.getEngine());
	}

}
