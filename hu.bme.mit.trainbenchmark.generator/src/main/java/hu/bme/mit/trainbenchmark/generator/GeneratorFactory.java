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

public abstract class GeneratorFactory {

	protected GeneratorConfig generatorConfig;
	
	public GeneratorFactory(GeneratorConfig generatorConfig) {
		this.generatorConfig = generatorConfig;
	}
	
	public Generator getGenerator() {
		switch (generatorConfig.getModelType()) {
		case SCHEDULE_REAL:
			return getScheduleGenerator();
		default:
			return getRailwayGenerator();
		}
	}
	
	protected abstract Generator getRailwayGenerator();
	
	protected abstract Generator getScheduleGenerator();
}
