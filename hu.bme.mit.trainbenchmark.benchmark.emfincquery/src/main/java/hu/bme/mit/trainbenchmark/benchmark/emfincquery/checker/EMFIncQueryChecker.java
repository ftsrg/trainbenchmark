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
package hu.bme.mit.trainbenchmark.benchmark.emfincquery.checker;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;

import org.eclipse.incquery.runtime.api.AdvancedIncQueryEngine;
import org.eclipse.incquery.runtime.api.IMatchUpdateListener;
import org.eclipse.incquery.runtime.api.IPatternMatch;
import org.eclipse.incquery.runtime.api.IncQueryMatcher;
import org.eclipse.incquery.runtime.api.impl.BaseGeneratedEMFQuerySpecification;
import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;
import org.eclipse.incquery.runtime.exception.IncQueryException;
import org.eclipse.incquery.runtime.extensibility.QueryBackendRegistry;
import org.eclipse.incquery.runtime.localsearch.matcher.integration.LocalSearchBackend;
import org.eclipse.incquery.runtime.matchers.backend.IQueryBackend;
import org.eclipse.incquery.runtime.matchers.backend.IQueryBackendFactory;
import org.eclipse.incquery.runtime.matchers.backend.QueryEvaluationHint;

import com.google.common.collect.Maps;

import hu.bme.mit.trainbenchmark.benchmark.checker.Checker;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.config.EMFIncQueryBackend;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.config.EMFIncQueryBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.driver.EMFIncQueryBaseDriver;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.railway.RailwayPackage;

public abstract class EMFIncQueryChecker<TMatch extends BasePatternMatch> extends Checker<TMatch> {

	protected Collection<TMatch> matches;
	protected final EMFIncQueryBaseDriver<TMatch> driver;
	
	protected final EMFIncQueryBackend backend;
	protected AdvancedIncQueryEngine engine;
	
	public EMFIncQueryChecker(final EMFIncQueryBackend backend, final EMFIncQueryBaseDriver<TMatch> driver) {
		super();
		this.backend = backend;
		this.driver = driver;

		RailwayPackage.eINSTANCE.eClass();

		try {
			engine = AdvancedIncQueryEngine.from(driver.getEngine());

			switch (backend) {
			case LOCALSEARCH:
				// when running local search, make sure the factory is registered

				final Iterable<Entry<Class<? extends IQueryBackend>, IQueryBackendFactory>> factories = QueryBackendRegistry
						.getInstance().getAllKnownFactories();
				boolean registered = false;
				for (final Entry<Class<? extends IQueryBackend>, IQueryBackendFactory> entry : factories) {
					if (entry.getKey().equals(LocalSearchBackend.class)) {
						registered = true;
					}
				}
//				if (!registered) {
//					QueryBackendRegistry.getInstance().registerQueryBackendFactory(LocalSearchBackend.class,
//							new LocalSearchBackendFactory());
//				}
				break;
			case INCREMENTAL:
				matches = getMatcher().getAllMatches();
				driver.getEngine().addMatchUpdateListener(getMatcher(), new IMatchUpdateListener<TMatch>() {
					@Override
					public void notifyAppearance(final TMatch match) {
						matches.add(match);
					}

					@Override
					public void notifyDisappearance(final TMatch match) {
						matches.remove(match);
					}
				}, false);
				break;
			default:
				throw new UnsupportedOperationException("Backend: " + backend + " not supported");
			}
		} catch (final IncQueryException e) {
			throw new RuntimeException(e);
		}
	}

	public static EMFIncQueryChecker<?> newInstance(final EMFIncQueryBenchmarkConfig benchmarkConfig,
			final EMFIncQueryBaseDriver driver, final Query query) {
		EMFIncQueryBackend backend = benchmarkConfig.getBackend();
		switch (query) {
		case CONNECTEDSEGMENTS:
			return new EMFIncQueryConnectedSegmentsChecker(backend, driver);
		case POSLENGTH:
			return new EMFIncQueryPosLengthChecker(backend, driver);
		case ROUTESENSOR:
			return new EMFIncQueryRouteSensorChecker(backend, driver);
		case SEMAPHORENEIGHBOR:
			return new EMFIncQuerySemaphoreNeighborChecker(backend, driver);
		case SWITCHSENSOR:
			return new EMFIncQuerySwitchSensorChecker(backend, driver);
		case SWITCHSET:
			return new EMFIncQuerySwitchSetChecker(backend, driver);
		default:
			throw new UnsupportedOperationException("Query " + query + " not supported");
		}
	}

	@Override
	public Collection<TMatch> check() throws IncQueryException {
		switch (backend) {
		case INCREMENTAL:
			break;
		case LOCALSEARCH:
			matches = getMatcher().getAllMatches();
			break;
		default:
			throw new UnsupportedOperationException("Backend: " + backend + " not supported");
		}
		return matches;
	}

	public void setMatches(final Collection<TMatch> matches) {
		this.matches = matches;
	}

	public abstract IncQueryMatcher<TMatch> getMatcher() throws IncQueryException;

	protected IncQueryMatcher<? extends IPatternMatch> getLSMatcher(
			final BaseGeneratedEMFQuerySpecification<?> specificationInstance) throws IncQueryException {
		final HashMap<String, Object> mapForHint = Maps.<String, Object> newHashMap();
		return engine.getMatcher(specificationInstance, new QueryEvaluationHint(LocalSearchBackend.class, mapForHint));
	}

}
