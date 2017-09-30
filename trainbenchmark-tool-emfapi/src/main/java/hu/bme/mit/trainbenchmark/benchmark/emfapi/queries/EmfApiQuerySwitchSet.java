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

package hu.bme.mit.trainbenchmark.benchmark.emfapi.queries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.EList;

import hu.bme.mit.trainbenchmark.benchmark.emf.driver.EmfDriver;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfSwitchSetMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.query.EmfApiQuery;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Semaphore;
import hu.bme.mit.trainbenchmark.railway.Signal;
import hu.bme.mit.trainbenchmark.railway.Switch;
import hu.bme.mit.trainbenchmark.railway.SwitchPosition;

public class EmfApiQuerySwitchSet<TDriver extends EmfDriver> extends EmfApiQuery<EmfSwitchSetMatch, TDriver> {

	public EmfApiQuerySwitchSet(final TDriver driver) {
		super(RailwayQuery.SWITCHSET, driver);
	}

	@Override
	public Collection<EmfSwitchSetMatch> evaluate() {
		final List<EmfSwitchSetMatch> matches = new ArrayList<>();

		final EList<Route> routes = driver.getContainer().getRoutes();
		for (final Route route : routes) {
			if (!route.isActive()) {
				continue;
			}

			// (route:Route)-[:entry]->(semaphore:Semaphore)
			final Semaphore semaphore = route.getEntry();
			if (semaphore == null) {
				continue;
			}
			// semaphore.signal == GO
			if (semaphore.getSignal() == Signal.GO) {
				// (route:Route)-[:follows]->(swP:SwitchPosition)
				for (final SwitchPosition switchPosition : route.getFollows()) {
					// (swP:SwitchPosition)-[:target]->(sw:Switch)
					final Switch sw = switchPosition.getTarget();
					// sw.currentPosition != swP.position
					if (sw.getCurrentPosition() != switchPosition.getPosition()) {
						matches.add(new EmfSwitchSetMatch(semaphore, route, switchPosition, sw));
					}
				}
			}
		}

		return matches;
	}
}
