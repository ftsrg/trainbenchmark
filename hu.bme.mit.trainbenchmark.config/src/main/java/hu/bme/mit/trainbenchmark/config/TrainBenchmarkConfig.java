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

import hu.bme.mit.trainbenchmark.constants.ModelType;
import hu.bme.mit.trainbenchmark.constants.ScenarioConstants;
import hu.bme.mit.trainbenchmark.constants.schedule.ScheduleSubmodels;

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
	protected ScenarioConstants scenario;
	protected ModelType modelType;
	protected ScheduleSubmodels submodel;

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

	public TrainBenchmarkConfig(final ScenarioConstants scenario, final int size) {
		this.scenario = scenario;
		this.size = size;
		this.submodel = ScheduleSubmodels.valueOf("A");
	}

	protected void initOptions() {
		options.addOption("help", false, "displays this text");
		options.addOption(requiredOption("scenario", "Batch/Inject/Repair"));
		options.addOption(requiredOption("size", "model size, e.g. 4"));
		options.addOption(requiredOption("model", "the type of the model"));
		options.addOption("subModel", true, "the subtype of the model");
	}

	protected void processArguments(final String[] args) throws ParseException {
		cmd = parser.parse(options, args);

		scenario = ScenarioConstants.valueOf(cmd.getOptionValue("scenario").toUpperCase());
		size = Integer.parseInt(cmd.getOptionValue("size"));
		modelType = ModelType.valueOf(cmd.getOptionValue("model").toUpperCase().replace("-", "_"));
		final String model = cmd.getOptionValue("subModel");
		if (model != null) {
			submodel = ScheduleSubmodels.valueOf(model);
		} else {
			submodel = ScheduleSubmodels.valueOf("A");
		}
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

		formatter.printHelp("java -jar trainbenchmark-project-jarfile.jar [options]", "options:",
				options, "", false);
		System.out.println();
	}

	public ScenarioConstants getScenario() {
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

	public ModelType getModelType() {
		return modelType;
	}

	public void setModelType(ModelType modelType) {
		this.modelType = modelType;
	}

	public String getModelPathNameWithoutExtension() {
		final String filename = getModelFileNameWithoutExtension();
		return getModelPath() + filename;
	}

	public ScheduleSubmodels getSubmodel() {
		return submodel;
	}

	public void setSubmodel(ScheduleSubmodels submodel) {
		this.submodel = submodel;
	}

	public String getModelFileNameWithoutExtension() {
		switch (modelType) {
		case SCHEDULE_HIERARCHICAL:
			return "schedule-hierarchical-" + submodel + "-" + size;
		case SCHEDULE_REAL:
			return "schedule-real";
		case SCHEDULE_RANDOM:
			return "schedule-random-" + submodel + "-" + size;
		case SCHEDULE_SCALE_FREE:
			return "schedule-scale-" + submodel + "-" + size;
		case SCHEDULE_SCALE_FREE_CHAR:
			return "schedule-scale-char-" + submodel + "-" + size;
		case SCHEDULE_SCALE_FREE_HET:
			return "schedule-scale-het-" + submodel + "-" + size;
		case SCHEDULE_SCALE_FREE_HOM:
			return "schedule-scale-hom-" + submodel + "-" + size;
		case SCHEDULE_WATTS_STROGATZ_001:
			return "schedule-watts-strogatz-001" + submodel + "-" + size;
		case SCHEDULE_WATTS_STROGATZ_01:
			return "schedule-watts-strogatz-01" + submodel + "-" + size;
		case SCHEDULE_WATTS_STROGATZ_0001:
			return "schedule-watts-strogatz-0001" + submodel + "-" + size;
		case RAILWAY:
			final String variant = (scenario == ScenarioConstants.BATCH) ? "repair" : scenario
					.toString().toLowerCase();
			final String filename = "railway-" + variant + "-" + size;
			return filename;
		default:
			throw new IllegalArgumentException("Invalid model type: " + modelType);
		}
	}

}