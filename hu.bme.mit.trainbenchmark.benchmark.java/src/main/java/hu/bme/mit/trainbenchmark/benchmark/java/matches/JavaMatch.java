/*******************************************************************************
 * Copyright (c) 2010-2015, Gabor Szarnyas, Benedek Izso, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.benchmark.java.matches;

import hu.bme.mit.trainbenchmark.railway.RailwayElement;

import java.util.Arrays;


public abstract class JavaMatch {

	protected RailwayElement[] match;

	public RailwayElement[] getMatch() {
		return match;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(match);
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final JavaMatch other = (JavaMatch) obj;
		if (!Arrays.equals(match, other.match))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "JavaMatch [match=" + Arrays.toString(match) + "]";
	}

}
