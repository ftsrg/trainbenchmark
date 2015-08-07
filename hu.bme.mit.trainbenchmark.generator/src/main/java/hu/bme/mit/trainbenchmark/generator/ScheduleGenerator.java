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

import hu.bme.mit.trainbenchmark.constants.schedule.ScheduleConstants;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class ScheduleGenerator extends Generator{

	@Override
	protected void generateModel() throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void generate() throws Exception {
		switch (generatorConfig.getModelType()){
		case SCHEDULE_REAL:
			initModel();
			parseModel();
			persistModel();
			break;
		default:
			generateModel();
			persistModel();
		}
	}

	public void parseModel() throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(new File(generatorConfig.getModelPathNameWithoutExtension() + ".json"));

		JsonNode trains = root.get("Trains");
		for (JsonNode train : trains) {
			createVertex(ScheduleConstants.TRAIN);
		}
	}
	
}
