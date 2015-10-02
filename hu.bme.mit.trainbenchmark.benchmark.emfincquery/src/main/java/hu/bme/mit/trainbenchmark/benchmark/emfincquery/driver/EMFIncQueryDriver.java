/*******************************************************************************
 * Copyright (c) 2010-2015, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
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

import java.util.Collection;
import java.util.Map.Entry;

import org.apache.log4j.Level;
import org.eclipse.incquery.runtime.api.AdvancedIncQueryEngine;
import org.eclipse.incquery.runtime.api.IMatchUpdateListener;
import org.eclipse.incquery.runtime.api.IncQueryEngine;
import org.eclipse.incquery.runtime.api.IncQueryMatcher;
import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;
import org.eclipse.incquery.runtime.emf.EMFScope;
import org.eclipse.incquery.runtime.extensibility.QueryBackendRegistry;
import org.eclipse.incquery.runtime.localsearch.matcher.integration.LocalSearchBackend;
import org.eclipse.incquery.runtime.localsearch.matcher.integration.LocalSearchBackendFactory;
import org.eclipse.incquery.runtime.matchers.backend.IQueryBackend;
import org.eclipse.incquery.runtime.matchers.backend.IQueryBackendFactory;
import org.eclipse.incquery.runtime.util.IncQueryLoggingUtil;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.config.EMFIncQueryBenchmarkConfig;

public class EMFIncQueryDriver<M extends BasePatternMatch> extends EMFIncQueryBaseDriver<M> {

	protected EMFIncQueryBenchmarkConfig eiqbc;

	public EMFIncQueryDriver(final EMFIncQueryBenchmarkConfig eiqbc) {
		this.eiqbc = eiqbc;
	}

	@Override
	public void initialize() throws Exception {
		super.initialize();
		IncQueryLoggingUtil.getDefaultLogger().setLevel(Level.OFF);
	}

	@Override
	public void read(final String modelPathWithoutExtension) throws Exception {
		super.read(modelPathWithoutExtension);

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
				QueryBackendRegistry.getInstance().registerQueryBackendFactory(LocalSearchBackend.class, new LocalSearchBackendFactory());
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
	}

}
