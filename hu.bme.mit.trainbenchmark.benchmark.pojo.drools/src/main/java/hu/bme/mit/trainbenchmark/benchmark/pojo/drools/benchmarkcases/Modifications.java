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

import hu.bme.mit.trainbenchmark.benchmark.util.BenchmarkResult;
import hu.bme.mit.trainbenchmark.pojo.Graph;
import hu.bme.mit.trainbenchmark.pojo.Route;
import hu.bme.mit.trainbenchmark.pojo.Segment;
import hu.bme.mit.trainbenchmark.pojo.Sensor;
import hu.bme.mit.trainbenchmark.pojo.Switch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Modifications {

	public static void modifyModelPosLength(Graph graph, BenchmarkResult bmr, int nElemToModify) {
		bmr.addModifyParams(nElemToModify);
		// modify
		long start = System.nanoTime();
		List<Segment> segments = new ArrayList<Segment>();
		segments.addAll(graph.getSegments());
		int size = segments.size();

		List<Segment> itemsToModify = getItemsToModify(bmr, nElemToModify, segments, size);

		// edit
		long startEdit = System.nanoTime();
		for (Segment segment : itemsToModify) {
			segment.setLength(0);
		}
		long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}

	public static void modifyModelPosLengthRepair(BenchmarkResult bmr, int nElemToModify, List<Segment> invalids) {
		bmr.addModifyParams(nElemToModify);
		// modify
		long start = System.nanoTime();
		int size = invalids.size();
		if (size < nElemToModify) {
			nElemToModify = size;
		}

		List<Segment> itemsToModify = getItemsToModify(bmr, nElemToModify, invalids, size);

		// edit
		long startEdit = System.nanoTime();
		for (Segment segment : itemsToModify) {
			segment.setLength(-1 * (segment.getLength() - 1));
		}
		long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}

	private static <T> List<T> getItemsToModify(BenchmarkResult bmr, int nElemToModify, List<T> items, int size) {
		Random random = bmr.getRandom();
		List<T> itemsToModify = new ArrayList<>();
		for (int i = 0; i < nElemToModify; i++) {
			int rndTarget = random.nextInt(size);
			T segment = items.get(rndTarget);
			itemsToModify.add(segment);
		}
		return itemsToModify;
	}

	public static void modifyModelRouteSensor(Graph graph, BenchmarkResult bmr, int nElemToModify) {
		bmr.addModifyParams(nElemToModify);
		// modify
		long start = System.nanoTime();
		List<Route> routes = new ArrayList<Route>();
		routes.addAll(graph.getRoutes());
		int size = routes.size();

		List<Route> itemsToModify = getItemsToModify(bmr, nElemToModify, routes, size);

		// edit
		long startEdit = System.nanoTime();
		for (Route route : itemsToModify) {
			if (!route.getRouteDefinition().isEmpty()) {
				route.removeRouteDefinition(0);
			}
		}
		long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}

	public static void modifyModelRouteSensorRepair(BenchmarkResult bmr, int nElemToModify, List<Sensor> invalids) {
		bmr.addModifyParams(nElemToModify);
		// modify
		long start = System.nanoTime();
		int size = invalids.size();
		if (size < nElemToModify) {
			nElemToModify = size;
		}

		List<Sensor> itemsToModify = getItemsToModify(bmr, nElemToModify, invalids, size);

		// edit
		long startEdit = System.nanoTime();
		for (Sensor sensor : itemsToModify) {
			sensor.clearTrackElements();
		}
		long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}

	public static void modifyModelSwitchSensor(Graph graph, BenchmarkResult bmr, int nElemToModify) {
		bmr.addModifyParams(nElemToModify);
		// modify
		long start = System.nanoTime();
		List<Switch> switches = new ArrayList<Switch>();
		switches.addAll(graph.getSwitches());
		int size = switches.size();

		List<Switch> itemsToModify = getItemsToModify(bmr, nElemToModify, switches, size);

		// edit
		long startEdit = System.nanoTime();
		for (Switch aSwitch : itemsToModify) {
			if (aSwitch.hasSensors() && !aSwitch.getSensors().isEmpty()) {
				aSwitch.clearSensors();
			}
		}
		long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}

	public static void modifyModelSwitchSensorRepair(Graph graph, BenchmarkResult bmr, int nElemToModify, List<Switch> invalids) {
		bmr.addModifyParams(nElemToModify);
		// modify
		long start = System.nanoTime();
		int size = invalids.size();
		if (size < nElemToModify) {
			nElemToModify = size;
		}

		List<Switch> itemsToModify = getItemsToModify(bmr, nElemToModify, invalids, size);

		// edit
		long startEdit = System.nanoTime();
		for (Switch aSwitch : itemsToModify) {
			Sensor sensor = new Sensor();
			graph.add(sensor);
			aSwitch.addSensorBidirectionallyAndFire(sensor);
		}
		long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}

	public static void modifyModelSignalNeighbor(Graph graph, BenchmarkResult bmr, int nElemToModify) {
		bmr.addModifyParams(nElemToModify);
		// modify
		long start = System.nanoTime();
		List<Route> routes = new ArrayList<Route>();
		routes.addAll(graph.getRoutes());
		int size = routes.size();

		List<Route> itemsToModify = getItemsToModify(bmr, nElemToModify, routes, size);

		// edit
		long startEdit = System.nanoTime();
		for (Route route : itemsToModify) {
			route.setEntry(null);
		}
		long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}

	public static void modifyModelSignalNeighborRepair(BenchmarkResult bmr, int nElemToModify, List<Route> matchedRoutes) {
		bmr.addModifyParams(nElemToModify);
		// modify
		long start = System.nanoTime();
		int size = matchedRoutes.size();
		if (size < nElemToModify) {
			nElemToModify = size;
		}

		List<Route> itemsToModify = getItemsToModify(bmr, nElemToModify, matchedRoutes, size);

		// edit
		long startEdit = System.nanoTime();
		for (Route route : itemsToModify) {
			route.setExit(null);
		}
		long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}
}
