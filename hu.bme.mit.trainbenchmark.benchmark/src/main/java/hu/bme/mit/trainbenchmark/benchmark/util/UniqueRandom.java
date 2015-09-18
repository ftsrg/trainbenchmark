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

package hu.bme.mit.trainbenchmark.benchmark.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UniqueRandom extends Random {

	private static final long serialVersionUID = 1L;

	List<Integer> ints = new ArrayList<Integer>();

	public UniqueRandom(final long random) {
		super(random);
	}

	@Override
	public int nextInt(final int n) {
		int newInt = super.nextInt(n);
		if (ints.size() >= n) {
			ints = new ArrayList<Integer>();
		}
		while (ints.contains(new Integer(newInt))) {
			newInt = super.nextInt(n);
		}
		ints.add(new Integer(newInt));
		return newInt;
	}
}
