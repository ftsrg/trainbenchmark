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

package hu.bme.mit.trainbenchmark.benchmark.pojo.drools.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.Transformation;
import hu.bme.mit.trainbenchmark.benchmark.util.BenchmarkResult;
import hu.bme.mit.trainbenchmark.pojo.Graph;
import hu.bme.mit.trainbenchmark.pojo.Route;
import hu.bme.mit.trainbenchmark.pojo.Segment;
import hu.bme.mit.trainbenchmark.pojo.Sensor;
import hu.bme.mit.trainbenchmark.pojo.Switch;

import java.util.ArrayList;
import java.util.List;

public class Modifications {

	public static void modifyModelPosLength(final Graph graph, final BenchmarkResult bmr, final long nElemToModify) {
		bmr.addModifyParams(nElemToModify);
		// modify
		final long start = System.nanoTime();
		final List<Segment> segments = new ArrayList<Segment>();
		segments.addAll(graph.getSegments());

		final List<Segment> itemsToModify = Transformation.pickRandom(nElemToModify, segments);

		// edit
		final long startEdit = System.nanoTime();
		for (final Segment segment : itemsToModify) {
			segment.setLength(0);
		}
		final long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}

	public static void modifyModelPosLengthRepair(final BenchmarkResult bmr, final long nElemToModify, final List<Segment> invalids) {
		bmr.addModifyParams(nElemToModify);
		// modify
		final long start = System.nanoTime();

		final List<Segment> itemsToModify = Transformation.pickRandom(nElemToModify, invalids);

		// edit
		final long startEdit = System.nanoTime();
		for (final Segment segment : itemsToModify) {
			segment.setLength(-1 * (segment.getLength() - 1));
		}
		final long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}

	public static void modifyModelRouteSensor(final Graph graph, final BenchmarkResult bmr, final long nElemToModify) {
		bmr.addModifyParams(nElemToModify);
		// modify
		final long start = System.nanoTime();
		final List<Route> routes = new ArrayList<Route>();
		routes.addAll(graph.getRoutes());

		final List<Route> itemsToModify = Transformation.pickRandom(nElemToModify, routes);

		// edit
		final long startEdit = System.nanoTime();
		for (final Route route : itemsToModify) {
			if (!route.getRouteDefinition().isEmpty()) {
				route.removeRouteDefinition(0);
			}
		}
		final long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}

	public static void modifyModelRouteSensorRepair(final BenchmarkResult bmr, final long nElemToModify, final List<Sensor> invalids) {
		bmr.addModifyParams(nElemToModify);
		// modify
		final long start = System.nanoTime();

		final List<Sensor> itemsToModify = Transformation.pickRandom(nElemToModify, invalids);

		// edit
		final long startEdit = System.nanoTime();
		for (final Sensor sensor : itemsToModify) {
			sensor.clearTrackElements();
		}
		final long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}

	public static void modifyModelSwitchSensor(final Graph graph, final BenchmarkResult bmr, final long nElemToModify) {
		bmr.addModifyParams(nElemToModify);
		// modify
		final long start = System.nanoTime();
		final List<Switch> switches = new ArrayList<Switch>();
		switches.addAll(graph.getSwitches());
		
		final List<Switch> itemsToModify = Transformation.pickRandom(nElemToModify, switches);

		// edit
		final long startEdit = System.nanoTime();
		for (final Switch aSwitch : itemsToModify) {
			if (aSwitch.hasSensors() && !aSwitch.getSensors().isEmpty()) {
				aSwitch.clearSensors();
			}
		}
		final long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}

	public static void modifyModelSwitchSensorRepair(final Graph graph, final BenchmarkResult bmr, final long nElemToModify, final List<Switch> invalids) {
		bmr.addModifyParams(nElemToModify);
		// modify
		final long start = System.nanoTime();

		final List<Switch> itemsToModify = Transformation.pickRandom(nElemToModify, invalids);
		
		// edit
		final long startEdit = System.nanoTime();
		for (final Switch aSwitch : itemsToModify) {
			final Sensor sensor = new Sensor();
			graph.add(sensor);
			aSwitch.addSensorBidirectionallyAndFire(sensor);
		}
		final long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}

	public static void modifyModelSignalNeighbor(final Graph graph, final BenchmarkResult bmr, final long nElemToModify) {
		bmr.addModifyParams(nElemToModify);
		// modify
		final long start = System.nanoTime();
		final List<Route> routes = new ArrayList<Route>();
		routes.addAll(graph.getRoutes());

		final List<Route> itemsToModify = Transformation.pickRandom(nElemToModify, routes);

		// edit
		final long startEdit = System.nanoTime();
		for (final Route route : itemsToModify) {
			route.setEntry(null);
		}
		final long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}

	public static void modifyModelSignalNeighborRepair(final BenchmarkResult bmr, final long nElemToModify, final List<Route> matchedRoutes) {
		bmr.addModifyParams(nElemToModify);
		// modify
		final long start = System.nanoTime();

		final List<Route> itemsToModify = Transformation.pickRandom(nElemToModify, matchedRoutes);

		// edit
		final long startEdit = System.nanoTime();
		for (final Route route : itemsToModify) {
			route.setExit(null);
		}
		final long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}
}
