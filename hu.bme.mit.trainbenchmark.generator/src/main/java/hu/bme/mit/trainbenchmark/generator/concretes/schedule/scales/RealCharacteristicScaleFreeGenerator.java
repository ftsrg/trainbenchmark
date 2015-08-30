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

package hu.bme.mit.trainbenchmark.generator.concretes.schedule.scales;

import hu.bme.mit.trainbenchmark.generator.FormatGenerator;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;

public class RealCharacteristicScaleFreeGenerator extends HomogeneousScaleFreeGenerator {

	protected double powerLawExponent;

	public RealCharacteristicScaleFreeGenerator(FormatGenerator formatGenerator,
			GeneratorConfig generatorConfig) {
		super(formatGenerator, generatorConfig);
	}

	@Override
	protected void printMessage() {
		System.out.print("Generate real charasteristic scale-free model...");
	}

	@Override
	protected void initializeConstants() {
		super.initializeConstants();
		powerLawExponent = -3;
	}

	/**
	 * Keeps an approximately 1.75 average degree between stations.
	 */
	@Override
	protected int getNeighborsNumber() {
		int percent = random.nextInt(100);
		if (percent < 25) {
			return 1;
		} else {
			return 2;
		}
	}

}
