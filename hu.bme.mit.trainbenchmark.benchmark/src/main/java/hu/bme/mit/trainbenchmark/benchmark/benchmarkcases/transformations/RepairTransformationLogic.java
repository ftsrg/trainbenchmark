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

public class RepairTransformationLogic<M, T> extends TransformationLogic<M, T, M> {

	protected RepairTransformationLogic(final Comparator comparator) {
		super(comparator);
	}

	@Override
	protected void lhs(final Collection<M> currentMatches) throws IOException {
		candidatesToModify = currentMatches;
	}

	@Override
	protected List<M> copyAndSort() {
		final Ordering<M> ordering = Ordering.from(comparator);
		final List<M> sortedMatches = ordering.sortedCopy(candidatesToModify);
		return sortedMatches;
	}
}
