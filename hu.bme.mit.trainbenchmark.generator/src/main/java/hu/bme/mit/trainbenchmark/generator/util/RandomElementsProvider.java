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

package hu.bme.mit.trainbenchmark.generator.util;

import java.util.ArrayList;
import java.util.Random;

public class RandomElementsProvider {

	public static ArrayList<Integer> getRandomIndices(final Random random, final ArrayList<?> list,
			final int amount) {
		final int size = list.size();
		if (amount > size) {
			throw new IllegalArgumentException("Amount is bigger than the size of the list!");
		}

		ArrayList<Integer> indices = new ArrayList<Integer>();
		int count = 0;
		int index = 0;
		while (count < amount) {
			index = random.nextInt(size);
			if (!indices.contains(index)) {
				indices.add(index);
				count++;
			}
		}
		return indices;

	}

	public static Object getRandomElement(final Random random, final ArrayList<?> list) {
		if (list.size() == 0) {
			throw new IllegalArgumentException("The list parameter is empty");
		}
		return list.get(random.nextInt(list.size()));

	}

	public static int getRandomDisjunctIndex(final Random random, final ArrayList<?> list,
			final int original) {
		if (list.size() < 1) {
			throw new IllegalArgumentException(
					"The list parameter must contain more than 1 value.");
		}
		while (true) {
			int index = getRandomIndex(random, list);
			if (original != index) {
				return index;
			}
		}
	}

	public static Object getRandomDisjunctElement(final Random random, final ArrayList<?> list,
			final Object original) {
		if (list.size() < 1) {
			throw new IllegalArgumentException(
					"The list parameter must contain more than 1 value.");
		}
		while (true) {
			int index = getRandomIndex(random, list);
			if (original != list.get(index)) {
				return list.get(index);
			}
		}
	}

	public static int getRandomIndex(final Random random, final ArrayList<?> list) {
		if (list.size() == 0) {
			throw new IllegalArgumentException("The list parameter is empty");
		}
		return random.nextInt(list.size());
	}
}
