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

import hu.bme.mit.trainbenchmark.constants.Scenario;

import java.util.Arrays;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.apache.commons.lang.WordUtils;

public abstract class TrainBenchmarkConfig {

	protected final Options options = new Options();
	protected final CommandLineParser parser = new PosixParser();
	protected CommandLine cmd;

	// arguments
	protected int size;
	protected Scenario scenario;

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

	public TrainBenchmarkConfig(final Scenario scenario, final int size) {
		this.scenario = scenario;
		this.size = size;
	}

	protected void initOptions() {
		options.addOption("help", false, "displays this text");
		options.addOption(requiredOption("scenario", "Batch/Inject/Repair"));
		options.addOption(requiredOption("size", "model size, e.g. 4"));
	}

	protected void processArguments(final String[] args) throws ParseException {
		cmd = parser.parse(options, args);

		scenario = Scenario.valueOf(cmd.getOptionValue("scenario").toUpperCase());
		size = Integer.parseInt(cmd.getOptionValue("size"));
	}

	// shorthand for generating required options
	protected static Option requiredOption(final String name, final String description) {
		final Option option = new Option(name, true, description);
		option.setRequired(true);
		return option;
	}

	public void printHelp() {
		final HelpFormatter formatter = new HelpFormatter();
		formatter.setWidth(120);

		formatter.printHelp("java -jar trainbenchmark-project-jarfile.jar [options]", "options:", options, "", false);
		System.out.println();
	}

	public Scenario getScenario() {
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

	public String getModelPath() {
		return getWorkspacePath() + "models/";
	}

	public String getModelPathNameWithoutExtension() {
		final String filename = getModelFileNameWithoutExtension();
		return getModelPath() + filename;
	}

	public String getModelFileNameWithoutExtension() {
		final String variant = (scenario == Scenario.BATCH) ? "repair" : scenario.toString().toLowerCase();
		final String filename = "railway-" + variant + "-" + size;
		return filename;
	}

}