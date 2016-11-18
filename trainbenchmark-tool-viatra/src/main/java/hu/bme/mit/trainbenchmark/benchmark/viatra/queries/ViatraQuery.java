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
package hu.bme.mit.trainbenchmark.benchmark.viatra.queries;

import java.util.Collection;

import org.eclipse.viatra.query.runtime.api.AdvancedViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.IMatchUpdateListener;
import org.eclipse.viatra.query.runtime.api.ViatraQueryMatcher;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;

import com.google.common.base.Preconditions;

import hu.bme.mit.trainbenchmark.benchmark.operations.ModelQuery;
import hu.bme.mit.trainbenchmark.benchmark.viatra.config.ViatraBackend;
import hu.bme.mit.trainbenchmark.benchmark.viatra.driver.ViatraDriver;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.railway.RailwayPackage;

public abstract class ViatraQuery<TMatch extends BasePatternMatch> extends ModelQuery<TMatch, ViatraDriver> {

	protected final AdvancedViatraQueryEngine engine;
	protected final ViatraBackend backend;
	protected Collection<TMatch> matches;

	public ViatraQuery(final RailwayQuery query, final ViatraDriver driver) {
		super(query, driver);

		RailwayPackage.eINSTANCE.eClass();

		engine = driver.getEngine();
		backend = driver.getBackend();
		Preconditions.checkArgument(backend == ViatraBackend.INCREMENTAL || backend == ViatraBackend.LOCAL_SEARCH,
				String.format("Backend %s not supported.", backend));

		if (backend == ViatraBackend.INCREMENTAL) {
			try {
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
			} catch (final ViatraQueryException e) {
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	public Collection<TMatch> evaluate() throws ViatraQueryException {
		if (backend == ViatraBackend.LOCAL_SEARCH) {
			matches = getMatcher().getAllMatches();
		}
		return matches;
	}

	public abstract ViatraQueryMatcher<TMatch> getMatcher() throws ViatraQueryException;

}
