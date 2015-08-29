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

package hu.bme.mit.trainbenchmark.generator.concretes.schedule;

import hu.bme.mit.trainbenchmark.generator.FormatGenerator;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;

import java.io.IOException;

public class WattsStrogatzScheduleGenerator extends HomogeneousScheduleGenerator {

	public WattsStrogatzScheduleGenerator(FormatGenerator formatGenerator, GeneratorConfig generatorConfig) {
		super(formatGenerator, generatorConfig);
	}

	@Override
	protected void printMessage() {
		System.out.print("Generate Watts-Strogatz model...");

	}

	@Override
	protected void initializationStep() throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void generateStations() throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	protected int getNeighborsNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

}
