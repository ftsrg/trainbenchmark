/*******************************************************************************
 * Copyright (c) 2010-2014, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/

package hu.bme.mit.trainbenchmark.benchmark.jena.benchmarkcases.xform;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.TransformationBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.jena.benchmarkcases.SwitchSensor;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.rdf.RDFConstants;

import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;

public class SwitchSensorXForm extends SwitchSensor implements TransformationBenchmarkCase {

	long newSensorId = 1000000000;

	@Override
	public void modify() throws IOException {
		int nElemToModify = Util.calcModify(jbc, jbc.getModificationConstant(), bmr);
		bmr.addModifyParams(nElemToModify);

		// modify
		long start = System.nanoTime();

		Random random = bmr.getRandom();
		int size = invalids.size();
		Set<Statement> newStatements = new HashSet<>();
		for (int i = 0; i < nElemToModify; i++) {
			int rndTarget = random.nextInt(size);
			Resource aSwitch = invalids.get(rndTarget);

			Property trackElement_sensor = model.getProperty(RDFConstants.BASE_PREFIX + ModelConstants.TRACKELEMENT_SENSOR);
			Property type = model.getProperty(RDFConstants.RDF_TYPE);
			Resource sensorType = model.getResource(RDFConstants.BASE_PREFIX + ModelConstants.SENSOR);

			Resource sensor = model.createResource(RDFConstants.BASE_PREFIX + "x" + newSensorId);
			newSensorId++;

			newStatements.add(model.createStatement(aSwitch, trackElement_sensor, sensor));
			newStatements.add(model.createStatement(sensor, type, sensorType));
		}

		// -- do edit
		long startEdit = System.nanoTime();
		for (Statement statement : newStatements) {
			model.add(statement);
		}
		long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);

	}

}
