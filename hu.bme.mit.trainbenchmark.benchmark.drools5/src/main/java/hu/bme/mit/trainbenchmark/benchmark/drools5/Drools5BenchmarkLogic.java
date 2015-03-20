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

package hu.bme.mit.trainbenchmark.benchmark.drools5;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.scenarios.AbstractBenchmarkLogic;

import org.apache.commons.cli.ParseException;

public class Drools5BenchmarkLogic extends AbstractBenchmarkLogic {

	public Drools5BenchmarkLogic(final String[] args) throws ParseException {
		bc = new BenchmarkConfig(args, getTool());
	}

	public Drools5BenchmarkLogic(final BenchmarkConfig bc) {
		super(bc);
	}
	
	@Override
	protected String getTool() {
		return "Drools5";
	}

}
