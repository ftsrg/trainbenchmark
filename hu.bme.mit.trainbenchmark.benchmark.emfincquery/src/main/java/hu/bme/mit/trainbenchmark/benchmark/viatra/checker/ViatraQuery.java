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
package hu.bme.mit.trainbenchmark.benchmark.viatra.checker;

import java.util.Collection;

import org.eclipse.viatra.query.runtime.api.AdvancedViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.IMatchUpdateListener;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.ViatraQueryMatcher;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedEMFQuerySpecification;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;

import hu.bme.mit.trainbenchmark.benchmark.operations.ModelQuery;
import hu.bme.mit.trainbenchmark.benchmark.viatra.config.ViatraBackend;
import hu.bme.mit.trainbenchmark.benchmark.viatra.config.ViatraBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.viatra.driver.ViatraBaseDriver;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.railway.RailwayPackage;

public abstract class ViatraQuery<TMatch extends BasePatternMatch> extends ModelQuery<TMatch, ViatraBaseDriver<TMatch>> {

	protected Collection<TMatch> matches;
	
	protected final ViatraBackend backend;
	protected AdvancedViatraQueryEngine engine;
	
	public ViatraQuery(final ViatraBackend backend, final ViatraBaseDriver<TMatch> driver) {
		super(driver);
		this.backend = backend;

		RailwayPackage.eINSTANCE.eClass();

		try {
			engine = AdvancedViatraQueryEngine.from(driver.getEngine());

			switch (backend) {
//			case LOCALSEARCH:
//				// when running local search, make sure the factory is registered
//
//				final Iterable<Entry<Class<? extends IQueryBackend>, IQueryBackendFactory>> factories = QueryBackendRegistry
//						.getInstance().getAllKnownFactories();
//				boolean registered = false;
//				for (final Entry<Class<? extends IQueryBackend>, IQueryBackendFactory> entry : factories) {
//					if (entry.getKey().equals(LocalSearchBackend.class)) {
//						registered = true;
//					}
//				}
////				if (!registered) {
////					QueryBackendRegistry.getInstance().registerQueryBackendFactory(LocalSearchBackend.class,
////							new LocalSearchBackendFactory());
////				}
//				break;
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
		} catch (final ViatraQueryException e) {
			throw new RuntimeException(e);
		}
	}

	public static ViatraQuery<?> newInstance(final ViatraBenchmarkConfig benchmarkConfig,
			final ViatraBaseDriver driver, final RailwayQuery query) {
		ViatraBackend backend = benchmarkConfig.getBackend();
		switch (query) {
		case CONNECTEDSEGMENTS:
			return new ViatraQueryConnectedSegments(backend, driver);
		case POSLENGTH:
			return new ViatraQueryPosLength(backend, driver);
		case ROUTESENSOR:
			return new ViatraQueryRouteSensor(backend, driver);
		case SEMAPHORENEIGHBOR:
			return new ViatraQuerySemaphoreNeighbor(backend, driver);
		case SWITCHMONITORED:
			return new ViatraQuerySwitchMonitored(backend, driver);
		case SWITCHSET:
			return new ViatraQuerySwitchSet(backend, driver);
		default:
			throw new UnsupportedOperationException("Query " + query + " not supported");
		}
	}

	@Override
	public Collection<TMatch> check() throws ViatraQueryException {
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

	public abstract ViatraQueryMatcher<TMatch> getMatcher() throws ViatraQueryException;

	protected ViatraQueryMatcher<? extends IPatternMatch> getLSMatcher(
			final BaseGeneratedEMFQuerySpecification<?> specificationInstance) throws ViatraQueryException {
//		final HashMap<String, Object> mapForHint = Maps.<String, Object> newHashMap();
//		return engine.getMatcher(specificationInstance, new QueryEvaluationHint(LocalSearchBackend.class, mapForHint));
		throw new UnsupportedOperationException("LS not supported");
	}

}
