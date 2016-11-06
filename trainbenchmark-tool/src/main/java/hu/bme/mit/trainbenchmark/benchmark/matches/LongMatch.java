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
package hu.bme.mit.trainbenchmark.benchmark.matches;

import java.util.Arrays;

/**
 * A match class for storing only the ids of the elements in the match.
 */
public abstract class LongMatch extends BaseMatch {

	protected Long[] match;

	public LongMatch() {
		super();
	}

	@Override
	public String toString() {
		return "LongMatch [match=" + Arrays.toString(match) + "]";
	}

}