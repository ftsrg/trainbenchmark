package hu.bme.mit.trainbenchmark.benchmark.viatra.driver;

import org.eclipse.viatra.query.runtime.api.AdvancedViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;

import hu.bme.mit.trainbenchmark.benchmark.emf.driver.EmfDriver;

public abstract class ViatraBaseDriver<TMatch extends BasePatternMatch> extends EmfDriver {

	protected AdvancedViatraQueryEngine engine;

	public AdvancedViatraQueryEngine getEngine() {
		return engine;
	}

}
