///*******************************************************************************
// * Copyright (c) 2010-2014, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
// * All rights reserved. This program and the accompanying materials
// * are made available under the terms of the Eclipse Public License v1.0
// * which accompanies this distribution, and is available at
// * http://www.eclipse.org/legal/epl-v10.html
// *
// * Contributors:
// *   Benedek Izso - initial API and implementation
// *   Gabor Szarnyas - initial API and implementation
// *******************************************************************************/
//
//package hu.bme.mit.trainbenchmark.benchmark.emf;
//
//import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.Transformation;
//import hu.bme.mit.trainbenchmark.benchmark.util.BenchmarkResult;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import hu.bme.mit.trainbenchmark.ConceptFactory;
//import hu.bme.mit.trainbenchmark.IndividualContainer;
//import hu.bme.mit.trainbenchmark.Route;
//import hu.bme.mit.trainbenchmark.Segment;
//import hu.bme.mit.trainbenchmark.Sensor;
//import hu.bme.mit.trainbenchmark.Switch;
//
//public class EMFModification {
//
//	// ------------------------------------------------------------------------------------------
//	// User
//	// ------------------------------------------------------------------------------------------
//
//	public static void modifyEMFmodelPosLength(final IndividualContainer pack, final BenchmarkResult bmr, final long nElemToModify) {
//		// modify
//		final List<Segment> segments = new ArrayList<Segment>();
//		for (final Object thing : pack.getContains()) {
//			if (thing instanceof Segment) {
//				segments.add((Segment) thing);
//			}
//		}
//		final List<Segment> itemsToModify = Transformation.pickRandom(nElemToModify, segments);
//
//		// edit
//		for (final Segment segment : itemsToModify) {
//			segment.setSegment_length(0);
//		}
//	}
//
//	public static void modifyEMFmodelRouteSensor(final IndividualContainer pack, final BenchmarkResult bmr, final long nElemToModify) {
//		// modify
//		final List<Route> routes = new ArrayList<Route>();
//		for (final Object thing : pack.getContains()) {
//			if (thing instanceof Route) {
//				routes.add((Route) thing);
//			}
//		}
//		final List<Route> itemsToModify = Transformation.pickRandom(nElemToModify, routes);
//
//		// edit
//		final long startEdit = System.nanoTime();
//		for (final Route route : itemsToModify) {
//			if (route.getRoute_routeDefinition().size() > 0)
//				route.getRoute_routeDefinition().remove(0);
//		}
//	}
//
//	public static void modifyEMFmodelSwitchSensor(final IndividualContainer pack, final BenchmarkResult bmr, final long nElemToModify) {
//		final List<Switch> switches = new ArrayList<Switch>();
//		for (final Object thing : pack.getContains()) {
//			if (thing instanceof Switch) {
//				switches.add((Switch) thing);
//			}
//		}
//		final List<Switch> itemsToModify = Transformation.pickRandom(nElemToModify, switches);
//
//		// edit
//		final long startEdit = System.nanoTime();
//		for (final Switch aSwitch : itemsToModify) {
//			if (aSwitch.getTrackElement_sensor().size() > 0)
//				aSwitch.getTrackElement_sensor().clear();
//		}
//	}
//
//	public static void modifyEMFmodelSignalNeighbor(final IndividualContainer pack, final BenchmarkResult bmr, final long nElemToModify) {
//		// modify
//		final List<Route> routes = new ArrayList<Route>();
//		for (final Object thing : pack.getContains()) {
//			if (thing instanceof Route) {
//				routes.add((Route) thing);
//			}
//		}
//		final List<Route> itemsToModify = Transformation.pickRandom(nElemToModify, routes);
//
//		// edit
//		for (final Route route : itemsToModify) {
//			route.setRoute_entry(null);
//		}
//	}
//
//	// ------------------------------------------------------------------------------------------
//	// XForm
//	// ------------------------------------------------------------------------------------------
//
//	public static void modifyEMFmodelPosLengthRepair(final IndividualContainer pack, final BenchmarkResult bmr, final long nElemToModify,
//			final List<Segment> invalids) {
//		// modify
//		final List<Segment> itemsToModify = Transformation.pickRandom(nElemToModify, invalids);
//
//		// edit
//		for (final Segment segment : itemsToModify) {
//			segment.setSegment_length(-1 * (segment.getSegment_length() - 1));
//		}
//	}
//
//	public static void modifyEMFmodelRouteSensorRepair(final IndividualContainer pack, final BenchmarkResult bmr, final long nElemToModify,
//			final List<Sensor> invalids) {
//		// modify
//		final List<Sensor> itemsToModify = Transformation.pickRandom(nElemToModify, invalids);
//
//		// edit
//		for (final Sensor sensor : itemsToModify) {
//			sensor.getSensor_trackElement().clear();
//		}
//	}
//
//	public static void modifyEMFmodelSwitchSensorRepair(final IndividualContainer pack, final BenchmarkResult bmr,
//			final long nElemToModify, final List<Switch> invalids) {
//		// modify
//		final List<Switch> itemsToModify = Transformation.pickRandom(nElemToModify, invalids);
//
//		// edit
//		final ConceptFactory factory = ConceptFactory.eINSTANCE;
//		for (final Switch aSwitch : itemsToModify) {
//			final Sensor sensor = factory.createSensor();
//			aSwitch.getTrackElement_sensor().add(sensor);
//			pack.getContains().add(sensor);
//		}
//	}
//
//	public static void modifyEMFmodelSignalNeighborRepair(final IndividualContainer pack, final BenchmarkResult bmr,
//			final long nElemToModify, final List<Route> invalids) {
//		// modify
//		final List<Route> itemsToModify = Transformation.pickRandom(nElemToModify, invalids);
//
//		// edit
//		for (final Route route : itemsToModify) {
//			route.setRoute_exit(null);
//		}
//	}
//
//}
