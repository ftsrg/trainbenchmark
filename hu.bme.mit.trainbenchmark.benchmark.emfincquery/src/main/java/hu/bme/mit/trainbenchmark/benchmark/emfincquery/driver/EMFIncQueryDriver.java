package hu.bme.mit.trainbenchmark.benchmark.emfincquery.driver;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.EMFIncQueryCommon;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.benchmarkcases.EMFIncQueryChecker;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.config.EMFIncQueryBenchmarkConfig;
import hu.bme.mit.trainbenchmark.emf.EMFDriver;

import java.io.IOException;
import java.util.Collection;

import org.eclipse.incquery.runtime.api.AdvancedIncQueryEngine;
import org.eclipse.incquery.runtime.api.IMatchUpdateListener;
import org.eclipse.incquery.runtime.api.IncQueryMatcher;
import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;
import org.eclipse.incquery.runtime.emf.EMFScope;
import org.eclipse.incquery.runtime.exception.IncQueryException;

public class EMFIncQueryDriver<M extends BasePatternMatch> extends EMFDriver {

	protected EMFIncQueryBenchmarkConfig eiqbc;
	protected AdvancedIncQueryEngine engine;
	protected EMFIncQueryChecker<M> checker;

	public EMFIncQueryDriver(final EMFIncQueryBenchmarkConfig eiqbc) {
		this.eiqbc = eiqbc;
	}

	@Override
	public void read(final String modelPathWithoutExtension) throws IOException {
		super.read(modelPathWithoutExtension);

		try {
			EMFIncQueryCommon.setEIQOptions(eiqbc);
			final EMFScope emfScope = new EMFScope(resource);
			engine = AdvancedIncQueryEngine.createUnmanagedEngine(emfScope);

			try {
				final IncQueryMatcher<M> matcher = checker.getMatcher();
				final Collection<M> matches = matcher.getAllMatches();
				checker.setMatches(matches);
				engine.addMatchUpdateListener(matcher, new IMatchUpdateListener<M>() {
					@Override
					public void notifyAppearance(final M match) {
						matches.add(match);
					}

					@Override
					public void notifyDisappearance(final M match) {
						matches.remove(match);
					}
				}, false);
			} catch (final IncQueryException e) {
				throw new IOException(e);
			}

		} catch (final IncQueryException e) {
			throw new RuntimeException(e);
		}
	}

	public void registerChecker(final EMFIncQueryChecker<M> checker) throws IOException {
		this.checker = checker;
	}

	public AdvancedIncQueryEngine getEngine() {
		return engine;
	}

}
