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

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import hu.bme.mit.trainbenchmark.config.AbstractConfig;
import hu.bme.mit.trainbenchmark.constants.ScenarioEnum;

public class GeneratorConfig extends AbstractConfig {

	protected static final String GRAPH_FORMAT = "graphFormat";
	protected static final String SCENARIO = "scenario";

	protected GraphFormat graphFormat;
	protected ScenarioEnum scenario;
	protected int size;
	protected Options options;
	protected CommandLine cmd;

	public GeneratorConfig(final String[] args) throws ParseException {
		super();
	}

	@Override
	protected void initOptions() {
		super.initOptions();

		// scenario
		final Option scenarioOption = new Option(SCENARIO, true, "specifies the scenario, e.g. Batch/Inject/Repair");
		scenarioOption.setRequired(true);
		options.addOption(scenarioOption);

		// size
		options.addOption(SIZE, true, "specifies model size, e.g. 4");
	}

	protected void processArguments(String[] args) throws ParseException {
//		
//		scenario = ScenarioEnum.valueOf(cmd.getOptionValue(SCENARIO).toUpperCase());
//
//		if (cmd.hasOption(SIZE)) {
//			size = Integer.parseInt(cmd.getOptionValue(SIZE));
//		}
	}

	// public String getScenarioName() {
	// return WordUtils.capitalizeFully(scenario.toString());
	// }

	public ScenarioEnum getScenario() {
		return scenario;
	}

	public int getSize() {
		return size;
	}

	public String getModelFileNameWithoutExtension() {
		final String variant = scenario.toString().toLowerCase();

		final StringBuilder filenameBuilder = new StringBuilder();
		filenameBuilder.append("railway-" + variant + "-");
		filenameBuilder.append(size);
		return filenameBuilder.toString();
	}
	
	public String getModelPathWithoutExtension() {
		final String filename = getModelFileNameWithoutExtension();
		return getModelsPath() + filename;
	}

}
