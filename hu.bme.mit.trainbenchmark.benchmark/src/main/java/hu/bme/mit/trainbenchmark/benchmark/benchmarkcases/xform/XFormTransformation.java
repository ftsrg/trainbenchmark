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
package hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.xform;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.Transformation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class XFormTransformation extends Transformation {

	public static <T> List<T> pickPosLengthXForm(long nElemToModify, final List<T> invalids) {
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

	public static <T> List<T> pickRouteSensorXForm(long nElemToModify, final List<T> invalids) {
		final Random random = getRandom();
		final int size = invalids.size();
		final List<T> itemsToModify = new ArrayList<>();

		if (size < nElemToModify) {
			nElemToModify = size;
		}

		for (int i = 0; i < nElemToModify; i++) {
			final int rndTarget = random.nextInt(size);
			final T sensor = new ArrayList<>(invalids).get(rndTarget);
			itemsToModify.add(sensor);
		}
		return itemsToModify;
	}

	public static <T> List<T> pickSwitchSensorXForm(long nElemToModify, final List<T> invalids) {
		final Random random = getRandom();
		final int size = invalids.size();
		final List<T> itemsToModify = new ArrayList<>();

		if (size < nElemToModify) {
			nElemToModify = size;
		}

		for (int i = 0; i < nElemToModify; i++) {
			final int rndTarget = random.nextInt(size);
			final T sw = new ArrayList<>(invalids).get(rndTarget);
			itemsToModify.add(sw);
		}
		return itemsToModify;
	}

	public static <T> List<T> pickSignalNeighborXForm(final long nElemToModify, final List<T> invalids) {
		final Random random = getRandom();
		final int size = invalids.size();
		final List<T> itemsToModify = new ArrayList<T>();

		long n = nElemToModify;
		if (size < nElemToModify) {
			n = size;
		}

		for (int i = 0; i < n; i++) {
			final int rndTarget = random.nextInt(size);
			final T route = new ArrayList<>(invalids).get(rndTarget);
			itemsToModify.add(route);
		}
		return itemsToModify;
	}
}
