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

package hu.bme.mit.trainbenchmark.config;

import static hu.bme.mit.trainbenchmark.constants.ScenarioEnum.MINIMAL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.apache.commons.lang.WordUtils;

import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.constants.ScenarioEnum;

public abstract class TrainBenchmarkConfig {

	protected static final String HELP = "help";
	protected static final String MAX_MEMORY = "maxMemory";
	protected static final String QUERY_MIX = "queryMix";
	protected static final String SCENARIO = "scenario";
	protected static final String SIZE = "size";

	protected final Options options = new Options();
	protected final CommandLineParser parser = new PosixParser();
	protected CommandLine cmd;

	// arguments
	protected List<RailwayQuery> queryMix = new ArrayList<>();
	protected ScenarioEnum scenario;
	protected int size;
	protected int maxMemory = 1000;

	public TrainBenchmarkConfig(final String args[]) throws ParseException {
		initOptions();

		if (Arrays.asList(args).contains("-help")) {
			printHelp();
			System.exit(0);
		}

		try {
			processArguments(args);
		} catch (final ParseException e) {
			printHelp();
			throw e;
		}
	}

	public TrainBenchmarkConfig(final ScenarioEnum scenario, final int size) {
		this.scenario = scenario;
		this.size = size;
	}

	protected void initOptions() {
		options.addOption(HELP, false, "displays this text");

		// max memory
		options.addOption(MAX_MEMORY, true, "denotes the maximum memory of the JVM in MBs, e.g. 10240 "
				+ "(this only servers logging purposes, you still need to set Xmx accordingly)");

		// query mix
		final Option queryMixOption = new Option(QUERY_MIX, true, "specifies the query, e.g. RouteSensor");
		queryMixOption.setArgs(Option.UNLIMITED_VALUES);
		options.addOption(queryMixOption);

		// scenario
		final Option scenarioOption = new Option(SCENARIO, true, "specifies the scenario, e.g. Batch/Inject/Repair");
		scenarioOption.setRequired(true);
		options.addOption(scenarioOption);

		// size
		options.addOption(SIZE, true, "specifies model size, e.g. 4");
	}

	protected void processArguments(final String[] args) throws ParseException {
		cmd = parser.parse(options, args);

		if (cmd.hasOption(MAX_MEMORY)) {
			maxMemory = Integer.parseInt(cmd.getOptionValue(MAX_MEMORY));
		}

		scenario = ScenarioEnum.valueOf(cmd.getOptionValue(SCENARIO).toUpperCase());

		if (cmd.hasOption(QUERY_MIX)) {
			final String[] queriesArguments = cmd.getOptionValues(QUERY_MIX);

			for (final String queriesArgument : queriesArguments) {
				queryMix.add(RailwayQuery.valueOf(queriesArgument.toUpperCase()));
			}
		}

		if (cmd.hasOption(SIZE)) {
			size = Integer.parseInt(cmd.getOptionValue(SIZE));
		}
	}

	public void printHelp() {
		final HelpFormatter formatter = new HelpFormatter();
		formatter.setWidth(120);

		formatter.printHelp("java -jar trainbenchmark-project-jarfile.jar [options]", "options:", options, "", false);
		System.out.println();
	}

	public ScenarioEnum getScenario() {
		return scenario;
	}

	public String getScenarioName() {
		return WordUtils.capitalizeFully(scenario.toString());
	}

	public int getSize() {
		return size;
	}

	public String getWorkspacePath() {
		return "../";
	}

	public String getModelsPath() {
		return getWorkspacePath() + "models/";
	}

	public String getModelPathWithoutExtension() {
		final String filename = getModelFileNameWithoutExtension();
		return getModelsPath() + filename;
	}

	public String getModelFileNameWithoutExtension() {
		final String variant = scenario.toString().toLowerCase();

		final StringBuilder filenameBuilder = new StringBuilder();
		filenameBuilder.append("railway-" + variant + "-");
		if (scenario == MINIMAL) {
			filenameBuilder.append(getQuery().toString().toLowerCase());
		} else {
			filenameBuilder.append(size);
		}
		return filenameBuilder.toString();
	}

	public List<RailwayQuery> getQueries() {
		return queryMix;
	}

	public RailwayQuery getQuery() {
		return queryMix.get(0);
	}

	public int getMaxMemory() {
		return maxMemory;
	}

}
