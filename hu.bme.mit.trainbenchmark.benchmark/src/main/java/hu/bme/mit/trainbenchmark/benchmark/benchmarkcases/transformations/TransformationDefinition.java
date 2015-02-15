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

	static long nElemToModify;
	static long start;
	static long startEdit;
	static long end;
	
	// User

	protected static void initTransformation(final BenchmarkResult bmr) {
		nElemToModify = Util.calcModify(bmr);
		bmr.addModifyParams(nElemToModify);

		start = System.nanoTime();
		startEdit = 0;
	}

	protected static void saveResults(final BenchmarkResult bmr) {
		end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}
	
	protected static void startEdit() {
		startEdit = System.nanoTime();
	}
	
	
	public static void posLengthUser(final BenchmarkResult bmr, final List<? extends Object> invalids, final DatabaseDriver driver)
			throws IOException {
		initTransformation(bmr);

		driver.beginTransaction();
		
		// query the model
		final List<? extends Object> segments = driver.collectVertices(SEGMENT);
		final List<? extends Object> itemsToModify = Transformation.pickRandom(nElemToModify, segments);

		startEdit();

		// perform transformation
		for (final Object segment : itemsToModify) {
			driver.updateProperty(segment, ModelConstants.SEGMENT_LENGTH, new PosLengthUserOperation());
		}

		driver.finishTransaction();

		saveResults(bmr);
	}

	public static void routeSensorUser(final BenchmarkResult bmr, final List<? extends Object> invalids, final DatabaseDriver driver)
			throws IOException {
		initTransformation(bmr);

		driver.beginTransaction();

		// query the model
		final List<? extends Object> routes = driver.collectVertices(ModelConstants.ROUTE);
		final List<? extends Object> itemsToModify = Transformation.pickRandom(nElemToModify, routes);

		startEdit();
		
		// perform transformation
		for (final Object route : itemsToModify) {
			driver.deleteOneOutgoingEdge(route, ModelConstants.ROUTE_ROUTEDEFINITION);
		}

		driver.finishTransaction();

		saveResults(bmr);
	}

	public static void signalNeighborUser(final BenchmarkResult bmr, final List<? extends Object> invalids, final DatabaseDriver driver)
			throws IOException {
		initTransformation(bmr);

		driver.beginTransaction();

		// query the model
		final List<? extends Object> routes = driver.collectVertices(ModelConstants.ROUTE);
		final List<? extends Object> itemsToModify = Transformation.pickRandom(nElemToModify, routes);

		startEdit();

		// perform transformation
		for (final Object route : itemsToModify) {
			driver.deleteOneOutgoingEdge(route, ModelConstants.ROUTE_ENTRY);
		}

		driver.finishTransaction();

		saveResults(bmr);
	}

	public static void switchSensorUser(final BenchmarkResult bmr, final List<? extends Object> invalids, final DatabaseDriver driver)
			throws IOException {
		initTransformation(bmr);

		driver.beginTransaction();

		// query the model
		final List<? extends Object> switches = driver.collectVertices(ModelConstants.SWITCH);
		final List<? extends Object> itemsToModify = Transformation.pickRandom(nElemToModify, switches);

		startEdit();

		// perform transformation
		for (final Object sw : itemsToModify) {
			driver.deleteAllOutgoingEdges(sw, ModelConstants.TRACKELEMENT_SENSOR);
		}
		driver.finishTransaction();

		saveResults(bmr);
	}

	// XForm

	public static void posLengthXForm(final BenchmarkResult bmr, final List<? extends Object> invalids, final DatabaseDriver driver)
			throws IOException {
		initTransformation(bmr);

		driver.beginTransaction();

		// pick the elements for transformation
		final List<? extends Object> itemsToModify = Transformation.pickRandom(nElemToModify, invalids);

		startEdit();
		
		// perform transformation
		for (final Object segment : itemsToModify) {
			driver.updateProperty(segment, ModelConstants.SEGMENT_LENGTH, new PosLengthXFormOperation());
		}
		driver.finishTransaction();

		saveResults(bmr);
	}

	public static void routeSensorXForm(final BenchmarkResult bmr, final List<? extends Object> invalids, final DatabaseDriver driver)
			throws IOException {
		initTransformation(bmr);

		// pick the elements for transformation
		final List<? extends Object> itemsToModify = pickRandom(nElemToModify, invalids);

		startEdit();

		// perform transformation
		driver.beginTransaction();
		for (final Object sensor : itemsToModify) {
			driver.deleteAllIncomingEdges(sensor, ModelConstants.TRACKELEMENT_SENSOR);
		}
		driver.finishTransaction();


		saveResults(bmr);
	}

	public static void signalNeighborXForm(final BenchmarkResult bmr, final List<? extends Object> invalids, final DatabaseDriver driver)
			throws IOException {
		initTransformation(bmr);

		// pick the elements for transformation
		final List<? extends Object> itemsToModify = Transformation.pickRandom(nElemToModify, invalids);

		startEdit();
		
		// perform transformation
		driver.beginTransaction();
		for (final Object object : itemsToModify) {
			driver.deleteAllOutgoingEdges(object, ModelConstants.ROUTE_EXIT);
		}

		driver.finishTransaction();

		saveResults(bmr);
	}

	public static void switchSensorXForm(final BenchmarkResult bmr, final List<? extends Object> invalids, final DatabaseDriver driver)
			throws IOException {
		initTransformation(bmr);

		// pick the elements for transformation
		final List<? extends Object> itemsToModify = Transformation.pickRandom(nElemToModify, invalids);

		startEdit();

		// perform transformation
		driver.beginTransaction();

		for (final Object vertex : itemsToModify) {
			driver.insertVertexWithEdge(vertex, ModelConstants.SENSOR, ModelConstants.TRACKELEMENT_SENSOR);
		}

		driver.finishTransaction();

		saveResults(bmr);
	}

	// utils
	
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
