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

package hu.bme.mit.trainbenchmark.generator.config;

import hu.bme.mit.trainbenchmark.config.TrainBenchmarkConfig;

import org.apache.commons.cli.ParseException;

public class GeneratorConfig extends TrainBenchmarkConfig {

	protected int size;

	public GeneratorConfig(String[] args) throws ParseException {
		super(args);
	}

	@Override
	protected void initOptions() {
		super.initOptions();

		options.addOption(requiredOption("size", "model size, e.g. 4"));
	}

	protected void processArguments(String[] args) throws ParseException {
		super.processArguments(args);

		size = Integer.parseInt(cmd.getOptionValue("size"));
	}

	public String getInstanceModelPath() {
		return workspacePath + "/models";
	}

	public int getSize() {
		return size;
	}

}
