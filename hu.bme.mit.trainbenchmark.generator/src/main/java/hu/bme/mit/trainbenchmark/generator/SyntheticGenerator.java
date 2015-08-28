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

package hu.bme.mit.trainbenchmark.generator;

import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;

import java.io.IOException;

public abstract class SyntheticGenerator extends Generator {

	protected FormatGenerator fg;

	public SyntheticGenerator(final FormatGenerator formatGenerator, final GeneratorConfig generatorConfig) {
		this.fg = formatGenerator;
		this.generatorConfig = generatorConfig;
	}

	protected abstract void initializeConstants();

	protected abstract void generateModel() throws IOException;

	public void generate() throws Exception {
		fg.initModel();
		initializeConstants();
		fg.startTransaction();
		generateModel();
		fg.endTransaction();
		fg.persistModel();
	}

	protected double getPowerLawValue(final double uniformRandom, double exponent, double minBound,
			double maxBound) {
		return Math.pow((Math.pow(maxBound, exponent + 1) - Math.pow(minBound, exponent + 1))
				* uniformRandom + Math.pow(minBound, exponent + 1), 1 / (exponent + 1));
	}

	public FormatGenerator getFormatGenerator() {
		return fg;
	}

	public void setFormatGenerator(FormatGenerator formatGenerator) {
		this.fg = formatGenerator;
	}

}
