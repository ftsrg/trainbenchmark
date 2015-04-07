/*******************************************************************************
 * Copyright (c) 2010-2014, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.benchmark.emfincquery.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.EMFIncQueryCommon;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.config.EMFIncQueryBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.driver.EMFIncQueryDriver;
import hu.bme.mit.trainbenchmark.railway.RailwayContainer;
import hu.bme.mit.trainbenchmark.railway.RailwayElement;

import java.io.IOException;
import java.util.Collection;

import org.apache.log4j.Level;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.incquery.runtime.api.AdvancedIncQueryEngine;
import org.eclipse.incquery.runtime.api.IMatchUpdateListener;
import org.eclipse.incquery.runtime.api.IPatternMatch;
import org.eclipse.incquery.runtime.api.IncQueryMatcher;
import org.eclipse.incquery.runtime.emf.EMFScope;
import org.eclipse.incquery.runtime.exception.IncQueryException;
import org.eclipse.incquery.runtime.util.IncQueryLoggingUtil;

public abstract class EMFIncQueryBenchmarkCase<M extends IPatternMatch> extends AbstractBenchmarkCase<M, RailwayElement> {

	protected RailwayContainer container;

	protected AdvancedIncQueryEngine engine;
	protected IncQueryMatcher<M> matcher;

	protected EMFIncQueryBenchmarkConfig getEMFIncQueryBenchmarkConfig() {
		return (EMFIncQueryBenchmarkConfig) bc;
	}

	@Override
	public void init() throws IOException {
		IncQueryLoggingUtil.getDefaultLogger().setLevel(Level.OFF);
	}

	@Override
	public Collection<M> check() {
		return matches;
	}

	@Override
	public void read() throws IOException {
		final String modelPath = bc.getModelPathNameWithoutExtension() + ".emf";
		final EMFIncQueryDriver<M> eiqDriver = new EMFIncQueryDriver<>(modelPath);
		driver = eiqDriver;

		final Resource resource = eiqDriver.getResource();

		try {
			EMFIncQueryCommon.setEIQOptions(getEMFIncQueryBenchmarkConfig());
			final EMFScope emfScope = new EMFScope(resource);
			engine = AdvancedIncQueryEngine.createUnmanagedEngine(emfScope);

			matches = getMatcher().getAllMatches();
			engine.addMatchUpdateListener(getMatcher(), new IMatchUpdateListener<M>() {
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
			throw new RuntimeException(e);
		}
	}

	protected abstract IncQueryMatcher<M> getMatcher() throws IncQueryException;

}
