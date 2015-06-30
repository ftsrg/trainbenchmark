/*******************************************************************************
 * Copyright (c) 2010-2015, Gabor Szarnyas, Benedek Izso, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.benchmark.emfincquery.driver;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.checker.EMFIncQueryChecker;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.config.EMFIncQueryBenchmarkConfig;
import hu.bme.mit.trainbenchmark.emf.EMFDriver;

import java.io.IOException;
import java.util.Collection;
import java.util.Map.Entry;

import org.eclipse.incquery.runtime.api.AdvancedIncQueryEngine;
import org.eclipse.incquery.runtime.api.IMatchUpdateListener;
import org.eclipse.incquery.runtime.api.IncQueryEngine;
import org.eclipse.incquery.runtime.api.IncQueryMatcher;
import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;
import org.eclipse.incquery.runtime.emf.EMFScope;
import org.eclipse.incquery.runtime.exception.IncQueryException;
import org.eclipse.incquery.runtime.extensibility.QueryBackendRegistry;
import org.eclipse.incquery.runtime.localsearch.matcher.integration.LocalSearchBackend;
import org.eclipse.incquery.runtime.localsearch.matcher.integration.LocalSearchBackendFactory;
import org.eclipse.incquery.runtime.matchers.backend.IQueryBackend;
import org.eclipse.incquery.runtime.matchers.backend.IQueryBackendFactory;

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
			if (eiqbc.isLocalSearch()) {
				// When running local search, make sure the factory is registered

				final Iterable<Entry<Class<? extends IQueryBackend>, IQueryBackendFactory>> factories = QueryBackendRegistry.getInstance()
						.getAllKnownFactories();
				boolean registered = false;
				for (final Entry<Class<? extends IQueryBackend>, IQueryBackendFactory> entry : factories) {
					if (entry.getKey().equals(LocalSearchBackend.class)) {
						registered = true;
					}
				}
				if (!registered) {
					QueryBackendRegistry.getInstance().registerQueryBackendFactory(LocalSearchBackend.class,
							new LocalSearchBackendFactory());
				}

			}

			final EMFScope emfScope = new EMFScope(resource);
			engine = AdvancedIncQueryEngine.from(IncQueryEngine.on(emfScope));

			final IncQueryMatcher<M> matcher = checker.getMatcher();
			final Collection<M> matches = matcher.getAllMatches();
			checker.setMatches(matches);
			if (!eiqbc.isLocalSearch()) {
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
