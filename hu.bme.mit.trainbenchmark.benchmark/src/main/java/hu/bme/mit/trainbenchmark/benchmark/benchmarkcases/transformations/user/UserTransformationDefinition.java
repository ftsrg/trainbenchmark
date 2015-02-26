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
package hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.user;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.TransformationDefinition;

import java.util.ArrayList;
import java.util.List;

public abstract class UserTransformationDefinition<T> extends TransformationDefinition<T> {

	public List<T> pickRandom(long nElementsToModify, final List<T> elements, final List<T> invalids) {
		final List<T> valids = new ArrayList<>(elements);
		valids.removeAll(invalids);
		
		final int size = valids.size();
		if (size < nElementsToModify) {
			nElementsToModify = size;
		}
		
		// System.out.println(elements.size() + " total elements: ");// + elements);
		// System.out.println(invalids.size() + " invalid elements: ");// + invalids);
		// System.out.println(valids.size() + " valid elements: ");// + valids);
		System.out.println(elements.size() + " total elements: " + elements);
		System.out.println(invalids.size() + " invalid elements: " + invalids);
		System.out.println(valids.size() + " valid elements: " + valids);
		
		final List<T> elementsToModify = new ArrayList<>();
		for (int i = 0; i < nElementsToModify; i++) {
			final int rndTarget = getRandom().nextInt(size);
			final T element = new ArrayList<>(valids).get(rndTarget);
			elementsToModify.add(element);
		}
		
		System.out.println(elementsToModify);
		return elementsToModify;
	}

}
