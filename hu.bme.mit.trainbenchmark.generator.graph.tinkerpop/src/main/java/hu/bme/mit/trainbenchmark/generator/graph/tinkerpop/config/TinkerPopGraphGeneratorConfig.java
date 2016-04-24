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

package hu.bme.mit.trainbenchmark.generator.graph.tinkerpop.config;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.ParseException;

import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;
import hu.bme.mit.trainbenchmark.generator.config.GraphFormat;

public class TinkerPopGraphGeneratorConfig extends GeneratorConfig {

	public TinkerPopGraphGeneratorConfig(final String[] args) throws ParseException {
		super(args);

		// format
		final Option graphFormatOption = new Option(GRAPH_FORMAT, true, "specifies the graph format, e.g. GraphSON (default)/GraphML/Gryo");
		options.addOption(graphFormatOption);
	}

	@Override
	protected void processArguments(final String[] args) throws ParseException {
		super.processArguments(args);

		if (cmd.hasOption(GRAPH_FORMAT)) {
			graphFormat = GraphFormat.valueOf(cmd.getOptionValue(GRAPH_FORMAT).toUpperCase());
		} else {
			graphFormat = GraphFormat.GRAPHSON;
		}
	}

	public GraphFormat getGraphFormat() {
		return graphFormat;
	}

}
