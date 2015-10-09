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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Level;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.incquery.runtime.api.AdvancedIncQueryEngine;
import org.eclipse.incquery.runtime.api.IMatchUpdateListener;
import org.eclipse.incquery.runtime.api.IncQueryEngine;
import org.eclipse.incquery.runtime.api.IncQueryMatcher;
import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;
import org.eclipse.incquery.runtime.base.api.NavigationHelper;
import org.eclipse.incquery.runtime.emf.EMFScope;
import org.eclipse.incquery.runtime.extensibility.QueryBackendRegistry;
import org.eclipse.incquery.runtime.localsearch.matcher.integration.LocalSearchBackend;
import org.eclipse.incquery.runtime.localsearch.matcher.integration.LocalSearchBackendFactory;
import org.eclipse.incquery.runtime.matchers.backend.IQueryBackend;
import org.eclipse.incquery.runtime.matchers.backend.IQueryBackendFactory;
import org.eclipse.incquery.runtime.util.IncQueryLoggingUtil;

import com.google.common.collect.Sets;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.config.EMFIncQueryBenchmarkConfig;
import hu.bme.mit.trainbenchmark.railway.RailwayElement;
import hu.bme.mit.trainbenchmark.railway.RailwayPackage;

public class EMFIncQueryDriver<TMatch extends BasePatternMatch> extends EMFIncQueryBaseDriver<TMatch> {

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

		final IncQueryMatcher<TMatch> matcher = checker.getMatcher();
		final Collection<TMatch> matches = matcher.getAllMatches();
		checker.setMatches(matches);
		if (!eiqbc.isLocalSearch()) {
			engine.addMatchUpdateListener(matcher, new IMatchUpdateListener<TMatch>() {
				@Override
				public void notifyAppearance(final TMatch match) {
					matches.add(match);
				}

				@Override
				public void notifyDisappearance(final TMatch match) {
					matches.remove(match);
				}
			}, false);
		}
	}

	@Override
	public List<RailwayElement> collectVertices(final String type) throws Exception {
		final EClass clazz = (EClass) RailwayPackage.eINSTANCE.getEClassifier(type);
		final NavigationHelper navigationHelper = EMFScope.extractUnderlyingEMFIndex(engine);

		// register the class (won't register it twice)
		navigationHelper.registerEClasses(Sets.newHashSet(clazz));

		final Set<EObject> instances = navigationHelper.getAllInstances(clazz);
		final List<RailwayElement> vertices = new ArrayList<>();
		for (final EObject instance : instances) {
			vertices.add((RailwayElement) instance);
		}

		return vertices;
	}

}
