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

import hu.bme.mit.trainbenchmark.benchmark.operations.ModelQuery;
import hu.bme.mit.trainbenchmark.benchmark.viatra.driver.ViatraDriver;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.railway.RailwayPackage;

public abstract class ViatraQuery<TMatch extends BasePatternMatch> extends ModelQuery<TMatch, ViatraDriver> {

	protected Collection<TMatch> matches;
	protected AdvancedViatraQueryEngine engine;

	public ViatraQuery(final RailwayQuery query, final ViatraDriver driver) {
		super(query, driver);

		RailwayPackage.eINSTANCE.eClass();

		try {
			engine = AdvancedViatraQueryEngine.from(driver.getEngine());

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

	@Override
	public Collection<TMatch> evaluate() throws ViatraQueryException {
		return matches;
	}

	public void setMatches(final Collection<TMatch> matches) {
		this.matches = matches;
	}

	public abstract ViatraQueryMatcher<TMatch> getMatcher() throws ViatraQueryException;

}
