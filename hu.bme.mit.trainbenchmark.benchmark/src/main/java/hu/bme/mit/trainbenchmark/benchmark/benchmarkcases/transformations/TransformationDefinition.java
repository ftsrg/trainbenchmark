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
package hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SEGMENT;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.Transformation;
import hu.bme.mit.trainbenchmark.benchmark.driver.DatabaseDriver;
import hu.bme.mit.trainbenchmark.benchmark.util.BenchmarkResult;
import hu.bme.mit.trainbenchmark.benchmark.util.UniqRandom;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.constants.TrainBenchmarkConstants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TransformationDefinition {

	public static void posLengthUser(final BenchmarkResult bmr, final List<? extends Object> invalids, final DatabaseDriver driver)
			throws IOException {
		final long nElemToModify = Util.calcModify(bmr);
		bmr.addModifyParams(nElemToModify);

		final long start = System.nanoTime();
		long startEdit = 0;

		driver.beginTransaction();
		// query the model
		final List<? extends Object> segments = driver.collectVertices(SEGMENT);
		final List<? extends Object> itemsToModify = Transformation.pickRandom(nElemToModify, segments);

		// edit
		startEdit = System.nanoTime();

		for (final Object segment : itemsToModify) {
			driver.updateProperty(segment, ModelConstants.SEGMENT_LENGTH, new PosLengthUserOperation());
		}

		driver.finishTransaction();

		final long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}

	public static void routeSensorUser(final BenchmarkResult bmr, final List<? extends Object> invalids, final DatabaseDriver driver)
			throws IOException {
		final long nElemToModify = Util.calcModify(bmr);
		bmr.addModifyParams(nElemToModify);

		final long start = System.nanoTime();
		final long startEdit = 0;

		driver.beginTransaction();
		// query the model
		final List<? extends Object> routes = driver.collectVertices(ModelConstants.ROUTE);
		final List<? extends Object> itemsToModify = Transformation.pickRandom(nElemToModify, routes);

		// edit
		for (final Object route : itemsToModify) {
			driver.deleteAllOutgoingEdges(route, ModelConstants.ROUTE_ROUTEDEFINITION);
		}

		driver.finishTransaction();

		final long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}

	public static void signalNeighborUser(final BenchmarkResult bmr, final List<? extends Object> invalids, final DatabaseDriver driver)
			throws IOException {
		final long nElemToModify = Util.calcModify(bmr);
		bmr.addModifyParams(nElemToModify);

		final long start = System.nanoTime();
		long startEdit = 0;

		driver.beginTransaction();
		
		// query the model
		final List<? extends Object> routes = driver.collectVertices(ModelConstants.ROUTE);
		final List<? extends Object> itemsToModify = Transformation.pickRandom(nElemToModify, routes);

		startEdit = System.nanoTime();

		for (final Object route : itemsToModify) {
			driver.deleteAllOutgoingEdges(route, ModelConstants.ROUTE_ENTRY);
		}

		driver.finishTransaction();

		final long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}

	public static void switchSensorUser(final BenchmarkResult bmr, final List<? extends Object> invalids, final DatabaseDriver driver)
			throws IOException {
		final long nElemToModify = Util.calcModify(bmr);
		bmr.addModifyParams(nElemToModify);

		final long start = System.nanoTime();
		long startEdit = 0;

		driver.beginTransaction();
		// query the model
		final List<? extends Object> switches = driver.collectVertices(ModelConstants.SWITCH);
		final List<? extends Object> itemsToModify = Transformation.pickRandom(nElemToModify, switches);

		// edit
		startEdit = System.nanoTime();

		for (final Object sw : itemsToModify) {
			driver.deleteAllOutgoingEdges(sw, ModelConstants.TRACKELEMENT_SENSOR);
		}
		driver.finishTransaction();

		final long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}

	public static void posLengthXForm(final BenchmarkResult bmr, final List<? extends Object> invalids, final DatabaseDriver driver)
			throws IOException {
		final long nElemToModify = Util.calcModify(bmr);
		bmr.addModifyParams(nElemToModify);

		// modify
		final long start = System.nanoTime();

		driver.beginTransaction();

		// pick the elements for transformation
		final List<? extends Object> itemsToModify = Transformation.pickRandom(nElemToModify, invalids);

		// edit
		final long startEdit = System.nanoTime();

		for (final Object segment : itemsToModify) {
			driver.updateProperty(segment, ModelConstants.SEGMENT_LENGTH, new PosLengthXFormOperation());
		}
		driver.finishTransaction();

		final long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}

	public static void routeSensorXForm(final BenchmarkResult bmr, final List<? extends Object> invalids, final DatabaseDriver driver)
			throws IOException {
		final long nElemToModify = Util.calcModify(bmr);
		bmr.addModifyParams(nElemToModify);

		final long startModify = System.nanoTime();

		// pick the elements for transformation
		final List<? extends Object> itemsToModify = pickRandom(nElemToModify, invalids);

		// edit
		final long startEdit = System.nanoTime();

		driver.beginTransaction();
		for (final Object sensor : itemsToModify) {
			driver.deleteAllIncomingEdges(sensor, ModelConstants.TRACKELEMENT_SENSOR);
		}
		driver.finishTransaction();

		final long endModify = System.nanoTime();
		bmr.addEditTime(endModify - startEdit);
		bmr.addModificationTime(endModify - startModify);
	}

	public static void signalNeighborXForm(final BenchmarkResult bmr, final List<? extends Object> invalids, final DatabaseDriver driver)
			throws IOException {
		final long nElemToModify = Util.calcModify(bmr);
		bmr.addModifyParams(nElemToModify);

		final long start = System.nanoTime();

		// pick the elements for transformation
		final List<? extends Object> itemsToModify = Transformation.pickRandom(nElemToModify, invalids);

		driver.beginTransaction();

		// edit
		final long startEdit = System.nanoTime();

		for (final Object object : itemsToModify) {
			driver.deleteAllOutgoingEdges(object, ModelConstants.ROUTE_EXIT);
		}

		// finishing transaction
		driver.finishTransaction();

		final long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}

	public static Random getRandom() {
		return new UniqRandom(TrainBenchmarkConstants.RANDOM_SEED);
	}

	public static <T> List<T> pickRandom(long nElemToModify, final List<T> invalids) {
		final Random random = getRandom();
		final int size = invalids.size();
		final List<T> itemsToModify = new ArrayList<>();

		if (size < nElemToModify) {
			nElemToModify = size;
		}

		for (int i = 0; i < nElemToModify; i++) {
			final int rndTarget = random.nextInt(size);
			final T segment = new ArrayList<>(invalids).get(rndTarget);
			itemsToModify.add(segment);
		}
		return itemsToModify;
	}

}
