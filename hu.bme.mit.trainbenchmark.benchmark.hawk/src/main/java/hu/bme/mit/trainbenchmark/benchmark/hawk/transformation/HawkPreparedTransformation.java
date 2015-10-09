package hu.bme.mit.trainbenchmark.benchmark.hawk.transformation;

import java.util.Collection;

import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;

import hu.bme.mit.trainbenchmark.benchmark.hawk.driver.HawkDriver;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.constants.Scenario;

public class HawkPreparedTransformation extends HawkTransformation<BasePatternMatch> {

	public HawkPreparedTransformation(HawkDriver<?> driver, Query query, Scenario scenario) {
		super(driver);
		
		
		
	}

	@Override
	public void rhs(Collection<BasePatternMatch> objects) throws Exception {
		// use the prepared files to perform the transformation
		
		
	}

}
