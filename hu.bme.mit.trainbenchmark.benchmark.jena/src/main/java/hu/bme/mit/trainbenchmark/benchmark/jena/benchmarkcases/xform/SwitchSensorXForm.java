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

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.Transformation;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.TransformationBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.jena.benchmarkcases.SwitchSensor;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.rdf.RDFConstants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;

public class SwitchSensorXForm extends SwitchSensor implements TransformationBenchmarkCase {

	long newSensorId = 1000000000;

	@Override
	public void modify() throws IOException {
		final long nElemToModify = Util.calcModify(bmr);
		bmr.addModifyParams(nElemToModify);

		// modify
		final long start = System.nanoTime();

		final List<Resource> switches = Transformation.pickRandom(nElemToModify, invalids);
		final List<Statement> newStatements = new ArrayList<>();
		for (final Resource aSwitch : switches) {
			final Property trackElement_sensor = model.getProperty(RDFConstants.BASE_PREFIX + ModelConstants.TRACKELEMENT_SENSOR);
			final Property type = model.getProperty(RDFConstants.RDF_TYPE);
			final Resource sensorType = model.getResource(RDFConstants.BASE_PREFIX + ModelConstants.SENSOR);

			final Resource sensor = model.createResource(RDFConstants.BASE_PREFIX + "x" + newSensorId);
			newSensorId++;

			newStatements.add(model.createStatement(aSwitch, trackElement_sensor, sensor));
			newStatements.add(model.createStatement(sensor, type, sensorType));
		}

		// edit
		final long startEdit = System.nanoTime();
		for (final Statement statement : newStatements) {
			model.add(statement);
		}
		final long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);

	}

}
