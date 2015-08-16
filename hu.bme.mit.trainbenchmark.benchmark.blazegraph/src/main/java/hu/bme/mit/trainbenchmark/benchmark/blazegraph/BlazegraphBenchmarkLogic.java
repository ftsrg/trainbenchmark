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

package hu.bme.mit.trainbenchmark.benchmark.blazegraph;

import hu.bme.mit.trainbenchmark.benchmark.AbstractBenchmarkLogic;
import hu.bme.mit.trainbenchmark.benchmark.rdf.RDFBenchmarkConfig;

import org.apache.commons.cli.ParseException;

public class BlazegraphBenchmarkLogic extends AbstractBenchmarkLogic{

	protected RDFBenchmarkConfig rbc;

	public BlazegraphBenchmarkLogic(final String[] args) throws ParseException {
		bc = rbc = new RDFBenchmarkConfig(args, "Blazegraph");
	}

	public BlazegraphBenchmarkLogic(final RDFBenchmarkConfig rbc) {
		super(rbc);
		this.rbc = rbc;
	}

}
