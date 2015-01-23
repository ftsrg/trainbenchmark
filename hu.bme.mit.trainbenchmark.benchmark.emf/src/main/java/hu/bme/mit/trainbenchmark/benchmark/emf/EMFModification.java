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

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.user.UserTransformation;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.xform.XFormTransformation;
import hu.bme.mit.trainbenchmark.benchmark.util.BenchmarkResult;

import java.util.ArrayList;
import java.util.List;

import Concept.ConceptFactory;
import Concept.IndividualContainer;
import Concept.Route;
import Concept.Segment;
import Concept.Sensor;
import Concept.Switch;

public class EMFModification {
	public static void modifyEMFmodelPosLength(final IndividualContainer pack, final BenchmarkResult bmr, final int nElemToModify) {
		bmr.addModifyParams(nElemToModify);

		// modify
		final long start = System.nanoTime();
		final List<Segment> segments = new ArrayList<Segment>();

		for (final Object thing : pack.getContains()) {
			if (thing instanceof Segment) {
				segments.add((Segment) thing);
			}
		}

		final List<Segment> itemsToModify = UserTransformation.pickPosLengthUser(nElemToModify, segments);

		// edit
		final long startEdit = System.nanoTime();
		for (final Segment segment : itemsToModify) {
			segment.setSegment_length(0);
		}
		final long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}

	public static void modifyEMFmodelPosLengthRepair(final IndividualContainer pack, final BenchmarkResult bmr, final int nElemToModify,
			final List<Segment> invalids) {
		bmr.addModifyParams(nElemToModify);

		// modify
		final long start = System.nanoTime();
		final List<Segment> itemsToModify = XFormTransformation.pickPosLengthXForm(nElemToModify, invalids);

		// edit
		final long startEdit = System.nanoTime();
		for (final Segment segment : itemsToModify) {
			segment.setSegment_length(-1 * (segment.getSegment_length() - 1));
		}
		final long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}

	public static void modifyEMFmodelRouteSensor(final IndividualContainer pack, final BenchmarkResult bmr, final int nElemToModify) {
		bmr.addModifyParams(nElemToModify);
		
		// modify
		final long start = System.nanoTime();
		final List<Route> routes = new ArrayList<Route>();
		for (final Object thing : pack.getContains()) {
			if (thing instanceof Route) {
				routes.add((Route) thing);
			}
		}
		final List<Route> itemsToModify = UserTransformation.pickRouteSensorUser(nElemToModify, routes);

		// edit
		final long startEdit = System.nanoTime();
		for (final Route route : itemsToModify) {
			if (route.getRoute_routeDefinition().size() > 0)
				route.getRoute_routeDefinition().remove(0);
		}
		final long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}

	public static void modifyEMFmodelRouteSensorRepair(final IndividualContainer pack, final BenchmarkResult bmr, final int nElemToModify,
			final List<Sensor> invalids) {
		bmr.addModifyParams(nElemToModify);

		// modify
		final long start = System.nanoTime();
		final List<Sensor> itemsToModify = XFormTransformation.pickRouteSensorXForm(nElemToModify, invalids);

		// edit
		final long startEdit = System.nanoTime();
		for (final Sensor sensor : itemsToModify) {
			sensor.getSensor_trackElement().clear();
		}
		final long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}

	public static void modifyEMFmodelSwitchSensor(final IndividualContainer pack, final BenchmarkResult bmr, final int nElemToModify) {
		bmr.addModifyParams(nElemToModify);
		
		// modify
		final long start = System.nanoTime();
		final List<Switch> switches = new ArrayList<Switch>();
		for (final Object thing : pack.getContains()) {
			if (thing instanceof Switch) {
				switches.add((Switch) thing);
			}
		}
		final List<Switch> itemsToModify = UserTransformation.pickSwitchSensorUser(nElemToModify, switches);
		
		// edit
		final long startEdit = System.nanoTime();
		for (final Switch aswitch : itemsToModify) {
			if (aswitch.getTrackElement_sensor().size() > 0)
				aswitch.getTrackElement_sensor().clear();
		}
		final long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}

	public static void modifyEMFmodelSwitchSensorRepair(final IndividualContainer pack, final BenchmarkResult bmr, final int nElemToModify,
			final List<Switch> invalids) {
		bmr.addModifyParams(nElemToModify);

		// modify
		final long start = System.nanoTime();
		final List<Switch> itemsToModify = XFormTransformation.pickRouteSensorXForm(nElemToModify, invalids);

		// edit
		final long startEdit = System.nanoTime();
		final ConceptFactory factory = ConceptFactory.eINSTANCE;
		for (final Switch aSwitch : itemsToModify) {
			final Sensor sensor = factory.createSensor();
			aSwitch.getTrackElement_sensor().add(sensor);
			pack.getContains().add(sensor);
		}
		final long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}

	public static void modifyEMFmodelSignalNeighbor(final IndividualContainer pack, final BenchmarkResult bmr, final int nElemToModify) {
		bmr.addModifyParams(nElemToModify);
		
		// modify
		final long start = System.nanoTime();
		final List<Route> routes = new ArrayList<Route>();
		for (final Object thing : pack.getContains()) {
			if (thing instanceof Route) {
				routes.add((Route) thing);
			}
		}
		final List<Route> itemsToModify = UserTransformation.pickSignalNeighborUser(nElemToModify, routes);

		// edit
		final long startEdit = System.nanoTime();
		for (final Route route : itemsToModify) {
			route.setRoute_entry(null);
		}
		final long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}

	public static void modifyEMFmodelSignalNeighborRepair(final IndividualContainer pack, final BenchmarkResult bmr, final int nElemToModify,
			final List<Route> invalids) {
		bmr.addModifyParams(nElemToModify);

		// modify
		final long start = System.nanoTime();
		final List<Route> itemsToModify = XFormTransformation.pickSignalNeighborXForm(nElemToModify, invalids);

		// edit
		final long startEdit = System.nanoTime();
		for (final Route route : itemsToModify) {
			route.setRoute_exit(null);
		}
		final long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}

}
