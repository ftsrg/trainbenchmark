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

package hu.bme.mit.trainbenchmark.benchmark.tinkergraph.checkers.core;

import static hu.bme.mit.trainbenchmark.benchmark.tinkergraph.driver.TinkerGraphDriver.TYPE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ENTRY;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.FOLLOWS;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ROUTE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SEMAPHORE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SIGNAL;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCH;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCHPOSITION;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.tinkerpop.gremlin.structure.Direction;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerHelper;

import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.checkers.TinkerGraphChecker;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.driver.TinkerGraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphSwitchSetMatch;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.constants.QueryConstants;
import hu.bme.mit.trainbenchmark.constants.Signal;

public class TinkerGraphSwitchSetChecker extends TinkerGraphChecker<TinkerGraphSwitchSetMatch> {

	public TinkerGraphSwitchSetChecker(final TinkerGraphDriver driver) {
		super(driver);
	}

	@Override
	public Collection<TinkerGraphSwitchSetMatch> check() {
		final Collection<TinkerGraphSwitchSetMatch> matches = new ArrayList<>();

		final TinkerGraph graph = driver.getGraph();		
		final List<? extends Vertex> routes = TinkerHelper.queryVertexIndex(graph, TYPE, ROUTE);
		
		// (route:Route)
		for (final Vertex route : routes) {			
			// (route:Route)-[:entry]->(semaphore:Semaphore)
			final Iterable<Vertex> entryNodes = () -> route.vertices(Direction.OUT, ENTRY);
			
			for (final Vertex semaphore : entryNodes) {
				if (!semaphore.property(TYPE).value().equals(SEMAPHORE)) {
					continue;
				}

				// semaphore.signal = "GO"
				final Object signal = semaphore.property(SIGNAL).value();
				if (!Signal.GO.toString().equals(signal)) {
					continue;
				}
			
				// (route:Route)-[:follows]->(swP:SwitchPosition)
				final Iterable<Vertex> swPs = () -> route.vertices(Direction.OUT, FOLLOWS);
				for (final Vertex swP : swPs) {
					if (!swP.property(TYPE).value().equals(SWITCHPOSITION)) {
						continue;
					}

					// (swP:SwitchPosition)-[:target]->(sw:Switch)
					final Iterator<Vertex> sws = swP.vertices(Direction.OUT, ModelConstants.TARGET);

					if (!sws.hasNext()) {
						continue;
					}

					final Vertex sw = sws.next();
					if (!sw.property(TYPE).value().equals(SWITCH)) {
						continue;
					}

					final Object currentPosition = sw.property(ModelConstants.CURRENTPOSITION).value();
					final Object position = swP.property(ModelConstants.POSITION).value();

					if (!currentPosition.equals(position)) {
						final Map<String, Object> match = new HashMap<>();
						match.put(QueryConstants.VAR_SEMAPHORE, semaphore);
						match.put(QueryConstants.VAR_ROUTE, route);
						match.put(QueryConstants.VAR_SWP, swP);
						match.put(QueryConstants.VAR_SW, sw);
						match.put(QueryConstants.VAR_CURRENTPOSITION, currentPosition);
						match.put(QueryConstants.VAR_POSITION, position);
						matches.add(new TinkerGraphSwitchSetMatch(match));
					}
				}
			}
		}

		return matches;
	}
}
