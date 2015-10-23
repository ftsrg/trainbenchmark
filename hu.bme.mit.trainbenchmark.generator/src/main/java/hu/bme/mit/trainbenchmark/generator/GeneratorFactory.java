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

import hu.bme.mit.trainbenchmark.constants.Scenario;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;
import hu.bme.mit.trainbenchmark.generator.minimal.MinimalConnectedSegmentsGenerator;
import hu.bme.mit.trainbenchmark.generator.minimal.MinimalPosLengthGenerator;
import hu.bme.mit.trainbenchmark.generator.minimal.MinimalRouteSensorGenerator;
import hu.bme.mit.trainbenchmark.generator.minimal.MinimalSemaphoreNeighborGenerator;
import hu.bme.mit.trainbenchmark.generator.minimal.MinimalSwitchSensorGenerator;
import hu.bme.mit.trainbenchmark.generator.minimal.MinimalSwitchSetGenerator;
import hu.bme.mit.trainbenchmark.generator.scalable.ScalableModelGenerator;

public class GeneratorFactory {

	public static ModelGenerator createGenerator(final ModelSerializer serializer, final GeneratorConfig generatorConfig) {
		if (generatorConfig.getScenario() == Scenario.MINIMAL) {
			switch (generatorConfig.getQuery()) {
			case CONNECTEDSEGMENTS:
				return new MinimalConnectedSegmentsGenerator(serializer, generatorConfig);
			case POSLENGTH:
				return new MinimalPosLengthGenerator(serializer, generatorConfig);
			case ROUTESENSOR:
				return new MinimalRouteSensorGenerator(serializer, generatorConfig);
			case SEMAPHORENEIGHBOR:
				return new MinimalSemaphoreNeighborGenerator(serializer, generatorConfig);
			case SWITCHSENSOR:
				return new MinimalSwitchSensorGenerator(serializer, generatorConfig);
			case SWITCHSET:
				return new MinimalSwitchSetGenerator(serializer, generatorConfig);
			default:
				throw new UnsupportedOperationException("Query " + query + " not supported");
			}
		} else {
			return new ScalableModelGenerator(serializer, generatorConfig);
		}
	}

}
