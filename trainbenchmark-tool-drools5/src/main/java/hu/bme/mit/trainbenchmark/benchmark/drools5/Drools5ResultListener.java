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

package hu.bme.mit.trainbenchmark.benchmark.drools5;

import java.util.HashSet;
import java.util.Set;

import org.drools.runtime.rule.Row;
import org.drools.runtime.rule.ViewChangedEventListener;

import hu.bme.mit.trainbenchmark.benchmark.drools5.matches.Drools5ConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.drools5.matches.Drools5PosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.drools5.matches.Drools5RouteSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.drools5.matches.Drools5SemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.drools5.matches.Drools5SwitchMonitoredMatch;
import hu.bme.mit.trainbenchmark.benchmark.drools5.matches.Drools5SwitchSetMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class Drools5ResultListener implements ViewChangedEventListener {

	protected RailwayQuery query;
	protected final Set<EmfMatch> matches = new HashSet<>();

	public Drools5ResultListener(final RailwayQuery query) {
		this.query = query;
	}

	@Override
	public void rowAdded(final Row row) {
		matches.add(createMatch(query, row));
	}

	@Override
	public void rowRemoved(final Row row) {
		matches.remove(createMatch(query, row));
	}

	@Override
	public void rowUpdated(final Row row) {
		matches.add(createMatch(query, row));
	}

	public Set<EmfMatch> getMatches() {
		return matches;
	}

	private EmfMatch createMatch(final RailwayQuery query, final Row row) {
		switch (query) {
		case CONNECTEDSEGMENTS:
			return new Drools5ConnectedSegmentsMatch(row);
		case POSLENGTH:
			return new Drools5PosLengthMatch(row);
		case ROUTESENSOR:
			return new Drools5RouteSensorMatch(row);
		case SEMAPHORENEIGHBOR:
			return new Drools5SemaphoreNeighborMatch(row);
		case SWITCHMONITORED:
			return new Drools5SwitchMonitoredMatch(row);
		case SWITCHSET:
			return new Drools5SwitchSetMatch(row);
		default:
			throw new UnsupportedOperationException("Query not supported: " + query);
		}
	}

}
