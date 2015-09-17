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

package hu.bme.mit.trainbenchmark.benchmark.queries;

import hu.bme.mit.trainbenchmark.constants.TrainBenchmarkConstants;

import java.io.IOException;
import java.util.Random;

public abstract class QueryBuilder {

	protected int iteration;
	protected int maxNumberOfQueries;
	protected final Random random;

	public abstract String nextQuery(final String queryPath, final String extension) throws IOException;

	public QueryBuilder() {
		random = new Random(TrainBenchmarkConstants.RANDOM_SEED);
		iteration = 1;
	}

	public int getNumberOfQueries() {
		return maxNumberOfQueries;
	}

	public int getIteration() {
		return iteration;
	}

	public void setIteration(int iteration) {
		this.iteration = iteration;
	}

}
