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

package hu.bme.mit.trainbenchmark.benchmark.java.benchmarkcases;

import java.util.ArrayList;

import Concept.Segment;

public class PosLength extends JavaBenchmarkCase<Segment> {

	@Override
	public String getName() {
		return "PosLength";
	}

	protected int constraintCheck() {
		invalids = new ArrayList<Segment>();

		for (Object eObject : pack.getContains()) {
			if (eObject instanceof Segment) {
				Segment segment = (Segment) eObject;
				if (segment.getSegment_length() <= 0)
					invalids.add(segment);
			}
		}

		return invalids.size();
	}

	@Override
	public void check() {
		bmr.startStopper();
		bmr.addInvalid(constraintCheck());
		bmr.addCheckTime();
	}

}
