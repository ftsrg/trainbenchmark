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

import hu.bme.mit.incqueryds.WildcardInput;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.config.IQDConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.driver.IQDCoreDriver;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.match.IQDCoreMatch;

import java.util.Comparator;

public class IQDCoreBenchmarkCase extends AbstractBenchmarkCase<IQDCoreMatch, Long, IQDCoreDriver, IQDConfigWrapper> {

	@Override
	public IQDCoreDriver createDriver(IQDConfigWrapper benchmarkConfigWrapper) throws Exception {
		WildcardInput iqdInput = new WildcardInput(benchmarkConfigWrapper.getMessageSize());
		return new IQDCoreDriver(benchmarkConfigWrapper, iqdInput);
	}

	@Override
	public Comparator<IQDCoreMatch> getMatchComparator() {
		return null;
	}
}
