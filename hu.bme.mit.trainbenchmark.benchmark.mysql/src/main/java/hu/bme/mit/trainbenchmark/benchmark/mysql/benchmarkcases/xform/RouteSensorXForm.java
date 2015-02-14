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

package hu.bme.mit.trainbenchmark.benchmark.mysql.benchmarkcases.xform;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.Transformation;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.TransformationBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.mysql.benchmarkcases.RouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class RouteSensorXForm extends RouteSensor implements TransformationBenchmarkCase {

	@Override
	public void modify() throws IOException {
		final long nElemToModify = Util.calcModify(bmr);
		bmr.addModifyParams(nElemToModify);

		// modify
		final long start = System.nanoTime();

		final List<Integer> itemsToModify = Transformation.pickRandom(nElemToModify, invalids);
		
		// edit
		final long startEdit = System.nanoTime();
		for (final Integer sensor : itemsToModify) {
			try {
				st.executeUpdate(String.format("DELETE FROM %s WHERE Sensor_id = %s;", ModelConstants.TRACKELEMENT_SENSOR,
						sensor.toString()));
			} catch (final SQLException e) {
				throw new IOException(e);
			}
		}
		final long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}
}
