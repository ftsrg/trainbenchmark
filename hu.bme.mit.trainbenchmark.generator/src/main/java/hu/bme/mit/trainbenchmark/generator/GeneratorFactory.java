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

import hu.bme.mit.trainbenchmark.generator.concretes.railway.RailwayGenerator;
import hu.bme.mit.trainbenchmark.generator.concretes.schedule.RealModelGenerator;
import hu.bme.mit.trainbenchmark.generator.concretes.schedule.ScaleFreeGenerator;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;

public abstract class GeneratorFactory {

	protected GeneratorConfig generatorConfig;

	public GeneratorFactory(GeneratorConfig generatorConfig) {
		this.generatorConfig = generatorConfig;
	}

	public SyntheticGenerator getSyntheticGenerator() {
		switch (generatorConfig.getModelType()) {
		case SCHEDULE_SCALE_FREE:
			return new ScaleFreeGenerator(getScheduleFormatGenerator(), generatorConfig);
		case SCHEDULE_REAL:
			return new RealModelGenerator(getScheduleFormatGenerator(), generatorConfig);
		default:
			return new RailwayGenerator(getRailwayFormatGenerator(), generatorConfig);
		}
	}

	protected abstract FormatGenerator getRailwayFormatGenerator();

	protected abstract FormatGenerator getScheduleFormatGenerator();
}
