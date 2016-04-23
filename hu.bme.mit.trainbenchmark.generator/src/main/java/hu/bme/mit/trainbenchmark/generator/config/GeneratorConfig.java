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

package hu.bme.mit.trainbenchmark.generator.config;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.ParseException;

import hu.bme.mit.trainbenchmark.config.TrainBenchmarkConfig;

public class GeneratorConfig extends TrainBenchmarkConfig {

	protected static final String GRAPH_FORMAT = "graphFormat";

	protected GraphFormat graphFormat;

	public GeneratorConfig(final String[] args) throws ParseException {
		super(args);
	}

	@Override
	protected void initOptions() {
		super.initOptions();

		// format
		final Option graphFormatOption = new Option(GRAPH_FORMAT, true,
				"specifies the graph format, e.g. GraphML (default)/GraphSON/Gryo");
		options.addOption(graphFormatOption);
	}

	@Override
	protected void processArguments(final String[] args) throws ParseException {
		super.processArguments(args);

		if (cmd.hasOption(GRAPH_FORMAT)) {
			graphFormat = GraphFormat.valueOf(cmd.getOptionValue(GRAPH_FORMAT).toUpperCase());
		} else {
			graphFormat = GraphFormat.GRAPHML;
		}
	}

	public GraphFormat getGraphFormat() {
		return graphFormat;
	}

}
