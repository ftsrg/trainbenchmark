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

package hu.bme.mit.trainbenchmark.benchmark.iqdcore;

import hu.bme.mit.incquerydcore.WildcardInput;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.benchmarkcases.IQDCoreChecker;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.driver.IQDCoreReader;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.match.IQDCoreMatch;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.match.IQDCoreMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.transformations.IQDCoreTransformation;
import hu.bme.mit.trainbenchmark.benchmark.rdf.RDFBenchmarkConfig;

import java.io.IOException;
import java.util.Comparator;

public class IQDCoreBenchmarkCase extends
		AbstractBenchmarkCase<IQDCoreMatch, Long> {
	protected WildcardInput iqdInput;
	protected RDFBenchmarkConfig rdfbc;

	@Override
	protected void init() throws IOException {
		this.rdfbc = (RDFBenchmarkConfig) bc;
		int messageSize = 16;
		iqdInput = new WildcardInput(messageSize);
		driver = new IQDCoreReader(rdfbc, iqdInput);
		checker = new IQDCoreChecker(iqdInput, rdfbc);
		if (bc.getScenario().hasTranformation()) {
			transformation = IQDCoreTransformation.newInstance(iqdInput,
					bc.getQuery(), bc.getScenario());
		}
	}

	@Override
	protected Comparator<?> getMatchComparator() {
		return new IQDCoreMatchComparator();
	}

}
