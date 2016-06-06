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

import java.util.ArrayList;
import java.util.List;

import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public abstract class AbstractConfig {

	protected static final String HELP = "help";
	protected static final String MAX_MEMORY = "maxMemory";
	protected static final String OPERATIONS = "operations";
	protected static final String SIZE = "size";

	// arguments
	protected List<RailwayQuery> operations = new ArrayList<>();
	protected int maxMemory = 1000;

	public AbstractConfig() {
	}

	protected void initOptions() {
//		options.addOption(HELP, false, "displays this text");
//
//		// max memory
//		options.addOption(MAX_MEMORY, true, "denotes the maximum memory of the JVM in MBs, e.g. 10240 "
//				+ "(this only servers logging purposes, you still need to set Xmx accordingly)");
//

	}

//	protected void processArguments(final String[] args) throws ParseException {
//		cmd = parser.parse(options, args);
//
//		if (cmd.hasOption(MAX_MEMORY)) {
//			maxMemory = Integer.parseInt(cmd.getOptionValue(MAX_MEMORY));
//		}
//
//		if (cmd.hasOption(OPERATIONS)) {
//			final String[] queriesArguments = cmd.getOptionValues(OPERATIONS);
//
//			for (final String queriesArgument : queriesArguments) {
//				operations.add(RailwayQuery.valueOf(queriesArgument.toUpperCase()));
//			}
//		}
//
//	}
//
//	public void printHelp() {
//		final HelpFormatter formatter = new HelpFormatter();
//		formatter.setWidth(120);
//
//		formatter.printHelp("java -jar trainbenchmark-project-jarfile.jar [options]", "options:", options, "", false);
//		System.out.println();
//	}

	public String getWorkspacePath() {
		return "../";
	}

	public String getModelsPath() {
		return getWorkspacePath() + "models/";
	}

	public List<RailwayQuery> getOperations() {
		return operations;
	}

	public int getMaxMemory() {
		return maxMemory;
	}

}
