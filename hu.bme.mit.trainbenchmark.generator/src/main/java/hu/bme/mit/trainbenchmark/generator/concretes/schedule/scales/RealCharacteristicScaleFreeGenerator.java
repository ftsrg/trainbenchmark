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

public class RealCharacteristicScaleFreeGenerator extends HeterogeneousScaleFreeGenerator {

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

//	/**
//	 * Keeps an approximately 1.75 average degree between stations.
//	 */
	@Override
	protected int getNeighborsNumber() {
//		int percent = random.nextInt(100);
//		if (percent < 25) {
//			return 1;
//		} else {
//			return 2;
//		}
		return 0;
	}

	@Override
	protected int getDestinationsNumber() {
		int percent = random.nextInt(1000);
		if (percent < 2 && stations.size() > 150) {
			// generate between 100-150
//			return random.nextInt(51) + 100;
			return (int) getPowerLawValue(random.nextDouble(), powerLawExponent, 100, 150);
		} else if (percent < 42 && stations.size() > 100) {
			// generate between 50-99
//			return random.nextInt(50) + 50;
			return (int) getPowerLawValue(random.nextDouble(), powerLawExponent, 51, 99);
		} else { // generate between 2-49
			percent = random.nextInt(100);
			if (percent < 42) {
				// generate between 2-11
//				return random.nextInt(10) + 2;
				return (int) getPowerLawValue(random.nextDouble(), powerLawExponent, 2, 11);
			} else {
				percent = random.nextInt(100);
				if (percent < 4) {
					// 41 - 49
//					return random.nextInt(9) + 41;
					return (int) getPowerLawValue(random.nextDouble(), powerLawExponent,
							41, 49);
				} else if (percent < 14) {
					// 31 - 40
//					return random.nextInt(10) + 31;
					return (int) getPowerLawValue(random.nextDouble(), powerLawExponent,
							31, 40);
				} else if (percent < 42) {
					// 21 - 30
//					return random.nextInt(10) + 21;
					return (int) getPowerLawValue(random.nextDouble(), powerLawExponent,
							21, 30);
				} else {
					// 11 - 20
//					return random.nextInt(10) + 11;
					return (int) getPowerLawValue(random.nextDouble(), powerLawExponent,
							11, 20);
				}
			}

		}
	}

}
