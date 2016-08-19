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

package hu.bme.mit.trainbenchmark.benchmark.drools.queries;

import java.util.HashSet;
import java.util.Set;

import org.kie.api.runtime.rule.Row;
import org.kie.api.runtime.rule.ViewChangedEventListener;

import hu.bme.mit.trainbenchmark.benchmark.drools.matches.DroolsConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.drools.matches.DroolsPosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.drools.matches.DroolsRouteSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.drools.matches.DroolsSemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.drools.matches.DroolsSwitchMonitoredMatch;
import hu.bme.mit.trainbenchmark.benchmark.drools.matches.DroolsSwitchSetMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class DroolsResultListener implements ViewChangedEventListener {

	protected RailwayQuery query;
	protected final Set<EmfMatch> matches = new HashSet<>();

	public DroolsResultListener(final RailwayQuery query) {
		this.query = query;
	}

	@Override
	public void rowInserted(final Row row) {
		matches.add(createMatch(query, row));
	}

	@Override
	public void rowDeleted(final Row row) {
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
			return new DroolsConnectedSegmentsMatch(row);
		case POSLENGTH:
			return new DroolsPosLengthMatch(row);
		case ROUTESENSOR:
			return new DroolsRouteSensorMatch(row);
		case SEMAPHORENEIGHBOR:
			return new DroolsSemaphoreNeighborMatch(row);
		case SWITCHMONITORED:
			return new DroolsSwitchMonitoredMatch(row);
		case SWITCHSET:
			return new DroolsSwitchSetMatch(row);
		default:
			throw new UnsupportedOperationException("Query not supported: " + query);
		}
	}

}
