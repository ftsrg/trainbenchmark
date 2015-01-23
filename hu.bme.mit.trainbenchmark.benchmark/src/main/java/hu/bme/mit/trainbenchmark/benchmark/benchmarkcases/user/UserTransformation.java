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
package hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.user;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.Transformation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserTransformation extends Transformation {
	
	public static <T> List<T> pickPosLengthUser(final long nElemToModify, final List<T> segments) {
		final Random random = getRandom();
		final int size = segments.size();
		final List<T> itemsToModify = new ArrayList<T>();

		for (int i = 0; i < nElemToModify; i++) {
			final int rndTarget = random.nextInt(size);
			final T segment = segments.get(rndTarget);
			itemsToModify.add(segment);
		}
		return itemsToModify;
	}
	
	public static <T> List<T> pickRouteSensorUser(final long nElemToModify, final List<T> routes) {
		final Random random = getRandom();
		final int size = routes.size();

		final List<T> itemsToModify = new ArrayList<T>();
		for (int i = 0; i < nElemToModify; i++) {
			final int rndTarget = random.nextInt(size);
			final T route = routes.get(rndTarget);
			itemsToModify.add(route);
		}
		return itemsToModify;
	}
	
	public static <T> List<T> pickSignalNeighborUser(final long nElemToModify, final List<T> routes) {
		final Random random = getRandom();
		final int size = routes.size();

		final List<T> itemsToModify = new ArrayList<T>();
		for (int i = 0; i < nElemToModify; i++) {
			final int rndTarget = random.nextInt(size);
			final T route = routes.get(rndTarget);
			itemsToModify.add(route);
		}
		return itemsToModify;
	}

	public static <T> List<T> pickSwitchSensorUser(final long nElemToModify, final List<T> switches) {
		final Random random = getRandom();
		final int size = switches.size();

		final List<T> itemsToModify = new ArrayList<T>();
		for (int i = 0; i < nElemToModify; i++) {
			final int rndTarget = random.nextInt(size);
			final T segment = switches.get(rndTarget);
			itemsToModify.add(segment);
		}
		return itemsToModify;
	}

}
