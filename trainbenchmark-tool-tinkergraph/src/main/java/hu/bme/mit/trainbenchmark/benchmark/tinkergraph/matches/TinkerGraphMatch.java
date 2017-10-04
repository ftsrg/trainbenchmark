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
package hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches;

import hu.bme.mit.trainbenchmark.benchmark.matches.BaseMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import org.apache.tinkerpop.gremlin.structure.Vertex;

import java.util.Arrays;
import java.util.Map;

import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_CURRENTPOSITION;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_LENGTH;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_POSITION;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_ROUTE;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_ROUTE1;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_ROUTE2;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT1;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT2;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT3;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT4;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT5;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT6;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEMAPHORE;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SENSOR;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SENSOR1;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SENSOR2;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SW;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SWP;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_TE1;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_TE2;

public abstract class TinkerGraphMatch extends BaseMatch {

	@Override
	public String toString() {
		return "TinkerGraphMatch [match=" + Arrays.toString(toArray()) + "]";
	}

	public static TinkerGraphMatch createMatchFromGremlinResult(final RailwayQuery query, final Object match) {
		switch (query) {
			case CONNECTEDSEGMENTS:
				final Map<String, Object> cs = (Map<String, Object>) match;
				return new TinkerGraphConnectedSegmentsMatch(
					(Vertex) cs.get(VAR_SENSOR),
					(Vertex) cs.get(VAR_SEGMENT1),
					(Vertex) cs.get(VAR_SEGMENT2),
					(Vertex) cs.get(VAR_SEGMENT3),
					(Vertex) cs.get(VAR_SEGMENT4),
					(Vertex) cs.get(VAR_SEGMENT5),
					(Vertex) cs.get(VAR_SEGMENT6)
				);
			case CONNECTEDSEGMENTS_INJECT:
				final Map<String, Object> csi = (Map<String, Object>) match;
				return new TinkerGraphConnectedSegmentsInjectMatch(
					(Vertex) csi.get(VAR_SENSOR),
					(Vertex) csi.get(VAR_SEGMENT1),
					(Vertex) csi.get(VAR_SEGMENT3)
				);
			case POSLENGTH:
				final Map<String, Object> pl = (Map<String, Object>) match;
				return new TinkerGraphPosLengthMatch(
					(Vertex) pl.get(VAR_SEGMENT),
					(int) pl.get(VAR_LENGTH)
				);
			case POSLENGTH_INJECT:
				final Vertex pli = (Vertex) match;
				return new TinkerGraphPosLengthInjectMatch(pli);
			case ROUTESENSOR:
				final Map<String, Object> rs = (Map<String, Object>) match;
				return new TinkerGraphRouteSensorMatch(
					(Vertex) rs.get(VAR_ROUTE),
					(Vertex) rs.get(VAR_SENSOR),
					(Vertex) rs.get(VAR_SWP),
					(Vertex) rs.get(VAR_SW)
				);
			case ROUTESENSOR_INJECT: {
				final Map<String, Object> rsi = (Map<String, Object>) match;
				return new TinkerGraphRouteSensorInjectMatch(
					(Vertex) rsi.get(VAR_ROUTE),
					(Vertex) rsi.get(VAR_SENSOR)
				);
			}
			case SEMAPHORENEIGHBOR:
				final Map<String, Object> sn = (Map<String, Object>) match;
				return new TinkerGraphSemaphoreNeighborMatch(
					(Vertex) sn.get(VAR_SEMAPHORE),
					(Vertex) sn.get(VAR_ROUTE1),
					(Vertex) sn.get(VAR_ROUTE2),
					(Vertex) sn.get(VAR_SENSOR1),
					(Vertex) sn.get(VAR_SENSOR2),
					(Vertex) sn.get(VAR_TE1),
					(Vertex) sn.get(VAR_TE2)
				);
			case SEMAPHORENEIGHBOR_INJECT:
				final Map<String, Object> sni = (Map<String, Object>) match;
				return new TinkerGraphSemaphoreNeighborInjectMatch(
					(Vertex) sni.get(VAR_ROUTE),
					(Vertex) sni.get(VAR_SEMAPHORE)
				);
			case SWITCHMONITORED:
				final Vertex sm = (Vertex) match;
				return new TinkerGraphSwitchMonitoredMatch(sm);
			case SWITCHMONITORED_INJECT:
				final Vertex smi = (Vertex) match;
				return new TinkerGraphSwitchMonitoredInjectMatch(smi);
			case SWITCHSET:
				final Map<String, Object> ss = (Map<String, Object>) match;
				return new TinkerGraphSwitchSetMatch(
					(Vertex) ss.get(VAR_SEMAPHORE),
					(Vertex) ss.get(VAR_ROUTE),
					(Vertex) ss.get(VAR_SWP),
					(Vertex) ss.get(VAR_SW),
					(String) ss.get(VAR_POSITION),
					(String) ss.get(VAR_CURRENTPOSITION)
				);
			case SWITCHSET_INJECT:
				Vertex ssi = (Vertex) match;
				return new TinkerGraphSwitchSetInjectMatch(ssi);
			default:
				throw new UnsupportedOperationException("Query not supported: " + query);
		}
	}

}
