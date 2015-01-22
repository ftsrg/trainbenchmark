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

package hu.bme.mit.trainbenchmark.benchmark.config;

import hu.bme.mit.trainbenchmark.config.TrainBenchmarkConfig;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.cli.ParseException;

public class BenchmarkConfig extends TrainBenchmarkConfig {

	// modification constants
	protected ModificationMethod modificationMethod;
	protected int modificationConstant;

	protected boolean benchmarkMode;
	protected int iterationCount;
	protected int runIndex;
	protected String benchmarkArtifact;
	protected String query;

	protected static int nMax;
	protected static boolean generateHeader;

	public int getRunIndex() {
		return runIndex;
	}

	public BenchmarkConfig(String args[]) throws ParseException {
		super(args);
	}

	@Override
	protected void initOptions() {
		super.initOptions();

		options.addOption(requiredOption("benchmarkArtifact", "path of the benchmark artifact (instance model or instance model source)"));
		options.addOption(requiredOption("query", "the query to run, e.g. RouteSensor"));

		options.addOption("benchmarkMode", true, "run benchmark specific (non-functional) procedures, like cleaning the OS cache");
		options.addOption("runIndex", true, "index of the run in the benchmark series");

		// modification constants
		options.addOption(
				"modificationMethod",
				true,
				"options: constant -- modify a fixed number of elements, resultSet -- modify based a number of elements based on the size of the results set");
		options.addOption("iterationCount", true, "number of modify-check iterations");
		options.addOption("modificationConstant", true, "modification constant for the modification method");

		options.addOption("nMax", true, "the length of the longest edit iteration");
		options.addOption("generateHeader", false, "is header generation needed?");
	}

	public void processArguments(String[] args) throws ParseException {
		super.processArguments(args);

		benchmarkArtifact = cmd.getOptionValue("benchmarkArtifact");

		// queries argument -> testCases list
		query = cmd.getOptionValue("query");

		String modificationMethodString = cmd.getOptionValue("modificationMethod");
		if (modificationMethodString != null) {
			switch (modificationMethodString) {
			case "constant":
				modificationMethod = ModificationMethod.CONSTANT;
				break;
			case "resultSet":
				modificationMethod = ModificationMethod.RESULT_SET;
				break;
			default:
				throw new ParseException("Invalid modification method specified");
			}
		} else {
			modificationMethod = ModificationMethod.CONSTANT;
		}

		String iterationCountString = cmd.getOptionValue("iterationCount");
		if (iterationCountString != null) {
			iterationCount = new Integer(iterationCountString);
		} else {
			iterationCount = 10;
		}

		String runIndexString = cmd.getOptionValue("runIndex");
		if (runIndexString != null) {
			runIndex = new Integer(runIndexString);
		} else {
			runIndex = -1;
		}

		modificationConstant = 1;
		modificationConstant = determineModificationConstant("modificationConstant");

		String benchmarkModeString = cmd.getOptionValue("benchmarkMode");
		if (benchmarkModeString != null) {
			benchmarkMode = new Boolean(benchmarkModeString);
		} else {
			benchmarkMode = false;
		}

		if (cmd.hasOption("nMax")) {
			nMax = new Integer(cmd.getOptionValue("nMax"));
		} else {
			nMax = 100;
		}

		generateHeader = cmd.hasOption("generateHeader");
	}

	private int determineModificationConstant(String optionName) {
		if (cmd.getOptionValue(optionName) != null) {
			return new Integer(cmd.getOptionValue(optionName));
		} else {
			return modificationConstant;
		}
	}

	public ModificationMethod getModificationMethod() {
		return modificationMethod;
	}

	public int getModificationConstant() {
		return modificationConstant;
	}

	public boolean isBenchmarkMode() {
		return benchmarkMode;
	}

	public int getIterationCount() {
		return iterationCount;
	}

	public String getBenchmarkArtifact() {
		return benchmarkArtifact;
	}

	public int getArtifactSize() {
		Pattern pattern = Pattern.compile("-(\\d+)\\.");
		Matcher matcher = pattern.matcher(benchmarkArtifact);
		if (matcher.find()) {
			return Integer.parseInt(matcher.group(1));
		} else {
			return -1;
		}
	}

	public String getQuery() {
		return query;
	}

	public static int getnMax() {
		return nMax;
	}

	public static boolean isGeneratingHeader() {
		return generateHeader;
	}

	public static void setGeneratingHeader(boolean isGeneratingHead) {
		generateHeader = false;
	}
}
