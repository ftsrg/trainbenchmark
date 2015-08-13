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

import hu.bme.mit.trainbenchmark.constants.schedule.ScheduleConstants;
import hu.bme.mit.trainbenchmark.generator.FormatGenerator;
import hu.bme.mit.trainbenchmark.generator.ScheduleGenerator;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RealModelGenerator extends ScheduleGenerator {

	public RealModelGenerator(FormatGenerator formatGenerator, GeneratorConfig generatorConfig) {
		super(formatGenerator, generatorConfig);
	}

	@Override
	protected void initializeConstants() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void generateModel() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(new File(generatorConfig.getModelPathNameWithoutExtension() + ".json"));

		JsonNode trains = root.get("Trains");
		for (JsonNode train : trains) {
			fg.createVertex(ScheduleConstants.TRAIN);
		}
	}

}
