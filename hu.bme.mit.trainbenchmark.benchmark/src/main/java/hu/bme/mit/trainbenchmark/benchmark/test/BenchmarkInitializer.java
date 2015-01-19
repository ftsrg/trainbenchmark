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

package hu.bme.mit.trainbenchmark.benchmark.test;

import hu.bme.mit.trainbenchmark.benchmark.scenarios.GenericBenchmarkLogic;

import org.apache.commons.cli.ParseException;

public abstract class BenchmarkInitializer {

	protected abstract GenericBenchmarkLogic initializeBenchmark(String queryName, String scenario) throws ParseException;

	public GenericBenchmarkLogic initializeBenchmark(String queryName) throws ParseException {
		return initializeBenchmark(queryName);
	}

}
