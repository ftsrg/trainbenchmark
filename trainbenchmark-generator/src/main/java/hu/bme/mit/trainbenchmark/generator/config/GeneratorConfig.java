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
import org.apache.commons.cli.Options;

import hu.bme.mit.trainbenchmark.config.AbstractConfig;
import hu.bme.mit.trainbenchmark.constants.Scenario;

public class GeneratorConfig extends AbstractConfig {

	protected static final String GRAPH_FORMAT = "graphFormat";
	protected static final String SCENARIO = "scenario";

	protected GraphFormat graphFormat;
	protected Scenario scenario;
	protected int size;
	protected Options options;
	protected CommandLine cmd;

	public Scenario getScenario() {
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
		return filename;
	}

}
