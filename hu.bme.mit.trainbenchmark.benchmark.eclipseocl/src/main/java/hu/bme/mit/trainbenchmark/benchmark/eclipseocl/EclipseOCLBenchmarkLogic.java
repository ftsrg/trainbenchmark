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
package hu.bme.mit.trainbenchmark.benchmark.eclipseocl;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.scenarios.AbstractBenchmarkLogic;

import org.apache.commons.cli.ParseException;

public class EclipseOCLBenchmarkLogic extends AbstractBenchmarkLogic {

	public EclipseOCLBenchmarkLogic(final String[] args) throws ParseException {
		super();
		bc = new BenchmarkConfig(args, getTool());
	}

	public EclipseOCLBenchmarkLogic(final BenchmarkConfig bc) {
		super();
		this.bc = bc;
	}

	@Override
	protected String getTool() {
		return "EclipseOCL";
	}
	
}
