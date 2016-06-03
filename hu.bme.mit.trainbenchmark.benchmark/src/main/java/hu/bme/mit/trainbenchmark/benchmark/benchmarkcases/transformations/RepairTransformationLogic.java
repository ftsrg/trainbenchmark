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
package hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations;

import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import com.google.common.collect.Ordering;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;

public class RepairTransformationLogic<TMatch, TElement, TBenchmarkConfig extends BenchmarkConfig> extends TransformationLogic<TMatch, TElement, TMatch, TBenchmarkConfig> {

	protected RepairTransformationLogic(final Comparator<TMatch> comparator) {
		super(comparator);
	}

	@Override
	protected void performLHS(final Collection<TMatch> currentMatches) throws IOException {
		candidatesToModify = currentMatches;
	}

	@Override
	protected List<TMatch> copyAndSort() {
		final Ordering<TMatch> ordering = Ordering.from(comparator);
		final List<TMatch> sortedMatches = ordering.sortedCopy(candidatesToModify);
		return sortedMatches;
	}
	
}
