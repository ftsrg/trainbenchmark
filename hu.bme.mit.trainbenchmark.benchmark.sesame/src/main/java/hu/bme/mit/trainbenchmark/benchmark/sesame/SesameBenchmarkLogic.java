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

package hu.bme.mit.trainbenchmark.benchmark.sesame;

import hu.bme.mit.trainbenchmark.benchmark.scenarios.AbstractBenchmarkLogic;
import hu.bme.mit.trainbenchmark.rdf.RDFBenchmarkConfig;

import org.apache.commons.cli.ParseException;

public class SesameBenchmarkLogic extends AbstractBenchmarkLogic {

	protected RDFBenchmarkConfig rbc;

	public SesameBenchmarkLogic(final String[] args) throws ParseException {
		bc = rbc = new RDFBenchmarkConfig(args, getTool());
	}

	public SesameBenchmarkLogic(final RDFBenchmarkConfig rbc) {
		super(rbc);
		this.rbc = rbc;
	}
	
	@Override
	protected String getTool() {
		return "Sesame";
	}

}
