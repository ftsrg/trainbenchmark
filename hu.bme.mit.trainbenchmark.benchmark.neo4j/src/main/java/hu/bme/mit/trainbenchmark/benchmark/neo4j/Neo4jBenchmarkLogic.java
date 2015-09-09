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

package hu.bme.mit.trainbenchmark.benchmark.neo4j;

import hu.bme.mit.trainbenchmark.benchmark.BenchmarkLogic;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.config.Neo4jBenchmarkConfig;

import org.apache.commons.cli.ParseException;

public class Neo4jBenchmarkLogic extends BenchmarkLogic {

	Neo4jBenchmarkConfig nbc;
	
	public Neo4jBenchmarkLogic(final String[] args) throws ParseException {
		bc = nbc = new Neo4jBenchmarkConfig(args);
	}

	public Neo4jBenchmarkLogic(final Neo4jBenchmarkConfig nbc) {
		super(nbc);
		this.nbc = nbc;
	}

}
