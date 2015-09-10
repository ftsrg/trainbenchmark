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
import hu.bme.mit.trainbenchmark.generator.concretes.schedule.HierarchicalSheduleGenerator;
import hu.bme.mit.trainbenchmark.generator.concretes.schedule.RandomScheduleGenerator;
import hu.bme.mit.trainbenchmark.generator.concretes.schedule.RealModelTransformer;
import hu.bme.mit.trainbenchmark.generator.concretes.schedule.WattsStrogatzScheduleGenerator;
import hu.bme.mit.trainbenchmark.generator.concretes.schedule.scales.HeterogeneousScaleFreeGenerator;
import hu.bme.mit.trainbenchmark.generator.concretes.schedule.scales.HomogeneousScaleFreeGenerator;
import hu.bme.mit.trainbenchmark.generator.concretes.schedule.scales.RealCharacteristicScaleFreeGenerator;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;

public abstract class GeneratorFactory {

	protected GeneratorConfig generatorConfig;

	public GeneratorFactory(GeneratorConfig generatorConfig) {
		this.generatorConfig = generatorConfig;
	}

	public SyntheticGenerator getSyntheticGenerator() {
		switch (generatorConfig.getModelType()) {
		case SCHEDULE_HIERARCHICAL:
			return new HierarchicalSheduleGenerator(getScheduleFormatGenerator(), generatorConfig);
		case SCHEDULE_RANDOM:
			return new RandomScheduleGenerator(getScheduleFormatGenerator(), generatorConfig);
		case SCHEDULE_SCALE_FREE_HOM:
			return new HomogeneousScaleFreeGenerator(getScheduleFormatGenerator(),
					generatorConfig);
		case SCHEDULE_SCALE_FREE_HET:
			return new HeterogeneousScaleFreeGenerator(getScheduleFormatGenerator(),
					generatorConfig);
		case SCHEDULE_SCALE_FREE_CHAR:
			return new RealCharacteristicScaleFreeGenerator(getScheduleFormatGenerator(),
					generatorConfig);
		case SCHEDULE_REAL:
			return new RealModelTransformer(getScheduleFormatGenerator(), generatorConfig);
		case SCHEDULE_WATTS_STROGATZ:
			return new WattsStrogatzScheduleGenerator(getScheduleFormatGenerator(),
					generatorConfig);
		case RAILWAY:
			return new RailwayGenerator(getRailwayFormatGenerator(), generatorConfig);
		default:
			throw new IllegalArgumentException("Invalid model type: "
					+ generatorConfig.getModelType());
		}
	}

	protected abstract FormatGenerator getRailwayFormatGenerator();

	protected abstract FormatGenerator getScheduleFormatGenerator();
}
