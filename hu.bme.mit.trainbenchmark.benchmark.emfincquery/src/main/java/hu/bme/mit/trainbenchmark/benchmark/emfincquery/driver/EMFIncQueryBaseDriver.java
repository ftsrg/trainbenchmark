package hu.bme.mit.trainbenchmark.benchmark.emfincquery.driver;

import java.io.IOException;

import org.eclipse.incquery.runtime.api.AdvancedIncQueryEngine;
import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.checker.EMFIncQueryChecker;
import hu.bme.mit.trainbenchmark.emf.EMFDriver;

public abstract class EMFIncQueryBaseDriver<M extends BasePatternMatch> extends EMFDriver {

	protected EMFIncQueryChecker<M> checker;
	protected AdvancedIncQueryEngine engine;

	public void registerChecker(final EMFIncQueryChecker<M> checker) throws IOException {
		this.checker = checker;
	}

	public AdvancedIncQueryEngine getEngine() {
		return engine;
	}
	
}
