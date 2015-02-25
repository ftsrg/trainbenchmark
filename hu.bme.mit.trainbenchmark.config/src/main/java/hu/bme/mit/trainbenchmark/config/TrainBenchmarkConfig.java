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

package hu.bme.mit.trainbenchmark.config;

import java.util.Arrays;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

public abstract class TrainBenchmarkConfig {

	protected final Options options = new Options();
	protected final CommandLineParser parser = new PosixParser();
	protected CommandLine cmd;

	// arguments
	protected String scenario;
	protected String workspacePath;

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

	protected void initOptions() {
		options.addOption("help", false, "displays this text");
		options.addOption(requiredOption("scenario", "Batch/User/Repair"));
		options.addOption(requiredOption("workspacePath", "path of the Eclipse workspace with all projects"));
	}

	protected void processArguments(final String[] args) throws ParseException {
		cmd = parser.parse(options, args);

		scenario = cmd.getOptionValue("scenario");
		workspacePath = cmd.getOptionValue("workspacePath");
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

	public String getScenario() {
		return scenario;
	}

	public String getWorkspacePath() {
		return workspacePath;
	}

	public String getVariant() {
		if ("Batch".equals(scenario))
			return "-repair-";
		return "-" + scenario.toLowerCase() + "-";
	}

}
