package hu.bme.mit.trainbenchmark.benchmark.emfincquery.driver;

import org.eclipse.incquery.runtime.api.AdvancedIncQueryEngine;
import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;

import hu.bme.mit.trainbenchmark.emf.EMFDriver;

public abstract class EMFIncQueryBaseDriver<TMatch extends BasePatternMatch> extends EMFDriver {

	protected AdvancedIncQueryEngine engine;

	public AdvancedIncQueryEngine getEngine() {
		return engine;
	}

}
