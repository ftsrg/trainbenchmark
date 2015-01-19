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

package hu.bme.mit.trainbenchmark.benchmark.sesame.benchmarkcases.xform;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.TransformationBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.sesame.SesameData;
import hu.bme.mit.trainbenchmark.benchmark.sesame.benchmarkcases.SwitchSensor;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.rdf.RDFConstants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openrdf.model.Resource;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.vocabulary.RDF;
import org.openrdf.repository.RepositoryException;

public class SwitchSensorXForm extends SwitchSensor implements TransformationBenchmarkCase {

	long newSensorId = 1000000000;

	@Override
	public void modify() throws IOException {
		final int nElemToModify = Util.calcModify(bc, bc.getModificationConstant(), bmr);
		bmr.addModifyParams(nElemToModify);
		// modify
		final long start = System.nanoTime();

		final ValueFactory f = myRepository.getValueFactory();

		try {
			final Random random = bmr.getRandom();
			final int size = invalids.size();
			final List<SesameData> itemsToAdd = new ArrayList<SesameData>();
			for (int i = 0; i < nElemToModify; i++) {
				final int rndTarget = random.nextInt(size);
				final Resource aSwitch = invalids.get(rndTarget);

				SesameData jd;

				// create new switch connected to the sensor
				final URI trackElement_sensor = f.createURI(RDFConstants.BASE_PREFIX + ModelConstants.TRACKELEMENT_SENSOR);
				final URI newSensor = f.createURI(RDFConstants.BASE_PREFIX + ModelConstants.SENSOR + newSensorId);
				newSensorId++;

				Statement statementToAdd = f.createStatement(aSwitch, trackElement_sensor, newSensor);

				jd = new SesameData();
				jd.setStatement(statementToAdd);
				itemsToAdd.add(jd);

				// set type to Switch
				final URI sensorType = f.createURI(RDFConstants.BASE_PREFIX + ModelConstants.SENSOR);
				statementToAdd = f.createStatement(newSensor, RDF.TYPE, sensorType);

				jd = new SesameData();
				jd.setStatement(statementToAdd);
				itemsToAdd.add(jd);
			}

			// edit
			final long startEdit = System.nanoTime();
			for (final SesameData jd : itemsToAdd) {
				con.add(jd.getStatement());
			}
			con.commit();
			final long end = System.nanoTime();
			bmr.addEditTime(end - startEdit);
			bmr.addModificationTime(end - start);
		} catch (final RepositoryException e) {
			throw new IOException(e);
		}
	}

}
