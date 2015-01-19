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

package hu.bme.mit.trainbenchmark.benchmark.emf;

import hu.bme.mit.trainbenchmark.benchmark.util.BenchmarkResult;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import Concept.ConceptFactory;
import Concept.IndividualContainer;
import Concept.Route;
import Concept.Segment;
import Concept.Sensor;
import Concept.Switch;
import Concept.SwitchStateKind;

public class EMFModification {
	public static void modifyEMFmodelPosLength(IndividualContainer pack, BenchmarkResult bmr, int nElemToModify) {
		bmr.addModifyParams(nElemToModify);
		// modify
		long start = System.nanoTime();
		List<Segment> segments = new ArrayList<Segment>();

		for (Object thing : pack.getContains()) {
			if (thing instanceof Segment) {
				segments.add((Segment) thing);
			}
		}

		Random random = bmr.getRandom();
		int size = segments.size();
		List<Segment> itemsToModify = new ArrayList<Segment>();
		for (int i = 0; i < nElemToModify; i++) {
			int rndTarget = random.nextInt(size);
			Segment segment = segments.get(rndTarget);
			itemsToModify.add(segment);
		}

		// edit
		long startEdit = System.nanoTime();
		for (Segment segment : itemsToModify) {
			segment.setSegment_length(0);
		}
		long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}

	public static void modifyEMFmodelPosLengthRepair(IndividualContainer pack, BenchmarkResult bmr, int nElemToModify,
			List<Segment> invalids) {
		bmr.addModifyParams(nElemToModify);
		// modify
		long start = System.nanoTime();
		Random random = bmr.getRandom();
		int size = invalids.size();
		List<Segment> itemsToModify = new ArrayList<Segment>();
		if (size < nElemToModify)
			nElemToModify = size;
		for (int i = 0; i < nElemToModify; i++) {
			int rndTarget = random.nextInt(size);
			Segment segment = invalids.get(rndTarget);
			itemsToModify.add(segment);
		}

		// edit
		long startEdit = System.nanoTime();
		for (Segment segment : itemsToModify) {
			segment.setSegment_length(-1 * (segment.getSegment_length() - 1));
		}
		long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}

	public static void modifyEMFmodelRouteSensor(IndividualContainer pack, BenchmarkResult bmr, int nElemToModify) {
		bmr.addModifyParams(nElemToModify);
		// modify
		long start = System.nanoTime();
		List<Route> routes = new ArrayList<Route>();
		for (Object thing : pack.getContains()) {
			if (thing instanceof Route) {
				routes.add((Route) thing);
			}
		}
		Random random = bmr.getRandom();
		int size = routes.size();
		List<Route> itemsToModify = new ArrayList<Route>();
		for (int i = 0; i < nElemToModify; i++) {
			int rndTarget = random.nextInt(size);
			Route route = routes.get(rndTarget);
			itemsToModify.add(route);
		}

		// edit
		long startEdit = System.nanoTime();
		for (Route route : itemsToModify) {
			if (route.getRoute_routeDefinition().size() > 0)
				route.getRoute_routeDefinition().remove(0);
		}
		long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}

	public static void modifyEMFmodelRouteSensorRepair(IndividualContainer pack, BenchmarkResult bmr, int nElemToModify,
			List<Sensor> invalids) {
		bmr.addModifyParams(nElemToModify);
		// modify
		long start = System.nanoTime();
		Random random = bmr.getRandom();
		int size = invalids.size();
		List<Sensor> itemsToModify = new ArrayList<Sensor>();
		if (size < nElemToModify)
			nElemToModify = size;
		for (int i = 0; i < nElemToModify; i++) {
			int rndTarget = random.nextInt(size);
			Sensor sensor = invalids.get(rndTarget);
			itemsToModify.add(sensor);
		}

		// edit
		long startEdit = System.nanoTime();
		for (Sensor sensor : itemsToModify) {
			sensor.getSensor_trackElement().clear();
		}
		long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}

	public static void modifyEMFmodelSwitchSensor(IndividualContainer pack, BenchmarkResult bmr, int nElemToModify) {
		bmr.addModifyParams(nElemToModify);
		// modify
		long start = System.nanoTime();
		List<Switch> switches = new ArrayList<Switch>();
		for (Object thing : pack.getContains()) {
			if (thing instanceof Switch) {
				switches.add((Switch) thing);
			}
		}
		Random random = bmr.getRandom();
		int size = switches.size();
		List<Switch> itemsToModify = new ArrayList<Switch>();
		for (int i = 0; i < nElemToModify; i++) {
			int rndTarget = random.nextInt(size);
			Switch aswitch = switches.get(rndTarget);
			itemsToModify.add(aswitch);
		}

		// edit
		long startEdit = System.nanoTime();
		for (Switch aswitch : itemsToModify) {
			if (aswitch.getTrackElement_sensor().size() > 0)
				aswitch.getTrackElement_sensor().clear();
		}
		long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}

	public static void modifyEMFmodelSwitchSensorRepair(IndividualContainer pack, BenchmarkResult bmr, int nElemToModify,
			List<Switch> invalids) {
		bmr.addModifyParams(nElemToModify);
		// modify
		long start = System.nanoTime();
		Random random = bmr.getRandom();
		int size = invalids.size();
		List<Switch> itemsToModify = new ArrayList<Switch>();
		if (size < nElemToModify)
			nElemToModify = size;
		for (int i = 0; i < nElemToModify; i++) {
			int rndTarget = random.nextInt(size);
			Switch aSwitch = invalids.get(rndTarget);
			itemsToModify.add(aSwitch);
		}

		// edit
		long startEdit = System.nanoTime();
		ConceptFactory factory = ConceptFactory.eINSTANCE;
		for (Switch aSwitch : itemsToModify) {
			Sensor sensor = factory.createSensor();
			aSwitch.getTrackElement_sensor().add(sensor);
			pack.getContains().add(sensor);
		}
		long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}

	public static void modifyEMFmodelSwitchSet(IndividualContainer pack, BenchmarkResult bmr, int nElemToModify) {
		bmr.addModifyParams(nElemToModify);
		// modify
		long start = System.nanoTime();
		List<Switch> switches = new ArrayList<Switch>();
		for (Object thing : pack.getContains()) {
			if (thing instanceof Switch) {
				switches.add((Switch) thing);
			}
		}
		Random random = bmr.getRandom();
		int size = switches.size();
		List<Entry<Switch, SwitchStateKind>> itemsToModify = new ArrayList<Map.Entry<Switch, SwitchStateKind>>();
		for (int i = 0; i < nElemToModify; i++) {
			int rndTarget = random.nextInt(size);
			Switch aswitch = switches.get(rndTarget);

			int oldValue = aswitch.getSwitch_actualState().getValue();
			int newValue = (oldValue + 1) % 4;
			SwitchStateKind newSSK = SwitchStateKind.get(newValue);
			itemsToModify.add(new AbstractMap.SimpleEntry<Switch, SwitchStateKind>(aswitch, newSSK));
		}

		// edit
		long startEdit = System.nanoTime();
		for (Entry<Switch, SwitchStateKind> switchNewSSKPair : itemsToModify) {
			Switch aswitch = switchNewSSKPair.getKey();
			SwitchStateKind newSSK = switchNewSSKPair.getValue();
			aswitch.setSwitch_actualState(newSSK);
		}
		long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}

	public static void modifyEMFmodelSignalNeighbor(IndividualContainer pack, BenchmarkResult bmr, int nElemToModify) {
		bmr.addModifyParams(nElemToModify);
		// modify
		long start = System.nanoTime();
		List<Route> routes = new ArrayList<Route>();
		for (Object thing : pack.getContains()) {
			if (thing instanceof Route) {
				routes.add((Route) thing);
			}
		}
		Random random = bmr.getRandom();
		int size = routes.size();
		List<Route> itemsToModify = new ArrayList<Route>();
		for (int i = 0; i < nElemToModify; i++) {
			int rndTarget = random.nextInt(size);
			Route aroute = routes.get(rndTarget);
			itemsToModify.add(aroute);
		}

		// edit
		long startEdit = System.nanoTime();
		for (Route route : itemsToModify) {
			route.setRoute_entry(null);
		}
		long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}

	public static void modifyEMFmodelSignalNeighborRepair(IndividualContainer pack, BenchmarkResult bmr, int nElemToModify,
			List<Route> matchedRoutes) {
		bmr.addModifyParams(nElemToModify);
		// modify
		long start = System.nanoTime();
		Random random = bmr.getRandom();
		int size = matchedRoutes.size();
		List<Route> itemsToModify = new ArrayList<Route>();
		if (size < nElemToModify)
			nElemToModify = size;
		for (int i = 0; i < nElemToModify; i++) {
			int rndTarget = random.nextInt(size);
			Route route = matchedRoutes.get(rndTarget);
			itemsToModify.add(route);
		}

		// edit
		long startEdit = System.nanoTime();
		for (Route route : itemsToModify) {
			route.setRoute_exit(null);
		}
		long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}

}
