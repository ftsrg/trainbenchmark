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
package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.config.EMFIncQueryBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.scenarios.GenericBenchmarkLogic;

import org.apache.commons.cli.ParseException;

public class EMFIncQueryBenchmarkLogic extends GenericBenchmarkLogic {

	EMFIncQueryBenchmarkConfig iqbc;
	
	public EMFIncQueryBenchmarkLogic(final String[] args) throws ParseException {
		super(args);
		bc = iqbc = new EMFIncQueryBenchmarkConfig(args);
	}

	@Override
	protected String getPackageName() {
		return "emfincquery";
	}

	@Override
	protected String getTool() {
		return "EMFIncQuery";
	}

}
