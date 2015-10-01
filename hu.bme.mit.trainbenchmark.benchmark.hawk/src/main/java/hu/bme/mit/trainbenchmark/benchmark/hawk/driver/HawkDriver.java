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
package hu.bme.mit.trainbenchmark.benchmark.hawk.driver;

import java.util.Collection;

import org.eclipse.incquery.runtime.api.AdvancedIncQueryEngine;
import org.eclipse.incquery.runtime.api.IMatchUpdateListener;
import org.eclipse.incquery.runtime.api.IncQueryEngine;
import org.eclipse.incquery.runtime.api.IncQueryMatcher;
import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;
import org.eclipse.incquery.runtime.emf.EMFScope;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.driver.EMFIncQueryBaseDriver;
import hu.bme.mit.trainbenchmark.benchmark.hawk.config.HawkBenchmarkConfig;

public class HawkDriver<M extends BasePatternMatch> extends EMFIncQueryBaseDriver<M> {

	protected HawkBenchmarkConfig hbc;

	public HawkDriver(final HawkBenchmarkConfig hbc) {
		this.hbc = hbc;
	}

	@Override
	public void read(final String modelPathWithoutExtension) throws Exception {
		super.read(modelPathWithoutExtension);

		final EMFScope emfScope = new EMFScope(resource);
		engine = AdvancedIncQueryEngine.from(IncQueryEngine.on(emfScope));

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

	}

}
