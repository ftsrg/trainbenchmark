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

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.TransformationBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.mysql.benchmarkcases.SwitchSensor;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SwitchSensorXForm extends SwitchSensor implements TransformationBenchmarkCase {

	@Override
	public void modify() throws IOException {
		long nElemToModify = Util.calcModify(bc, bc.getModificationConstant(), bmr);
		bmr.addModifyParams(nElemToModify);

		// modify
		final long start = System.nanoTime();

		final Random random = bmr.getRandom();
		final int size = invalids.size();
		final List<Integer> itemsToModify = new ArrayList<Integer>();
		if (size < nElemToModify)
			nElemToModify = size;
		for (int i = 0; i < nElemToModify; i++) {
			final int rndTarget = random.nextInt(size);
			final Integer aSwitch = invalids.get(rndTarget);
			itemsToModify.add(aSwitch);
		}

		// edit
		final long startEdit = System.nanoTime();
		for (final Integer aSwitch : itemsToModify) {
			int senid = 0;
			try {
				st.executeUpdate(String.format("INSERT INTO `%s` (`id`) VALUES (%d);", ModelConstants.SENSOR, senid),
						Statement.RETURN_GENERATED_KEYS);
			} catch (SQLException e) {
				throw new IOException(e);
			}
			try (ResultSet rs = st.getGeneratedKeys()) {
				if (rs.next()) {
					senid = rs.getInt(1);
				}
				st.executeUpdate(String.format("INSERT INTO `%s` (`TrackElement_id`, `Sensor_id`) VALUES (%d, %d);",
						ModelConstants.TRACKELEMENT_SENSOR, aSwitch, senid));
			} catch (final SQLException e) {
				throw new IOException(e);
			}
		}
		final long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}
}
