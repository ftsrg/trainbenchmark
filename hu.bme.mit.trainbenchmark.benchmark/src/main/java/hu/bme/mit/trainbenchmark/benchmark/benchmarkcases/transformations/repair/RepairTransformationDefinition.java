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
package hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.repair;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.TransformationDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class RepairTransformationDefinition<T> extends TransformationDefinition<T> {

	public List<T> pickRandom(long nElementsToModify, final List<T> currentResults) {
		final Random random = getRandom();
		final int size = currentResults.size();
		if (size < nElementsToModify) {
			nElementsToModify = size;
		}

		final List<T> elementsToModify = new ArrayList<>();
		for (int i = 0; i < nElementsToModify; i++) {
			final int rndTarget = random.nextInt(size);
			final T element = currentResults.get(rndTarget);
			elementsToModify.add(element);
		}
		return elementsToModify;
	}

}
