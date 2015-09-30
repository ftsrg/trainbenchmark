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
package hu.bme.mit.trainbenchmark.benchmark.hawk;

import org.apache.commons.cli.ParseException;

import hu.bme.mit.trainbenchmark.benchmark.hawk.config.HawkBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.scenarios.AbstractBenchmarkLogic;

public class HawkBenchmarkLogic extends AbstractBenchmarkLogic {

	protected HawkBenchmarkConfig hbc;

	public HawkBenchmarkLogic(final String[] args) throws ParseException {
		super();
		bc = hbc = new HawkBenchmarkConfig(args);
	}

	public HawkBenchmarkLogic(final HawkBenchmarkConfig hbc) {
		super(hbc);
		this.hbc = hbc;
	}

}
