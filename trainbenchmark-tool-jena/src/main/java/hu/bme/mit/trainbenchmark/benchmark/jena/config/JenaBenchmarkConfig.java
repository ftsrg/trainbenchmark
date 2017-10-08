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

package hu.bme.mit.trainbenchmark.benchmark.jena.config;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBase;
import hu.bme.mit.trainbenchmark.benchmark.rdf.config.RdfBenchmarkConfig;
import hu.bme.mit.trainbenchmark.rdf.RdfFormat;

public class JenaBenchmarkConfig extends RdfBenchmarkConfig {

	protected JenaBenchmarkConfig(final BenchmarkConfigBase configBase, final boolean inferencing,
								  final RdfFormat format) {
		super(configBase, inferencing, format);
	}

	@Override
	public String getToolName() {
		return "Jena" + getToolNamePostfix();
	}

	@Override
	public String getProjectName() {
		return "jena";
	}

}
