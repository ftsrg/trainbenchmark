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

package hu.bme.mit.trainbenchmark.benchmark.mysql.benchmarkcases.user;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.TransformationBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.mysql.benchmarkcases.SwitchSensor;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SwitchSensorUser extends SwitchSensor implements TransformationBenchmarkCase {

	@Override
	public void modify() throws IOException {
		final long nElemToModify = Util.calcModify(bc, bc.getModificationConstant(), bmr);
		bmr.addModifyParams(nElemToModify);

		// modify
		final long start = System.nanoTime();
		final List<Integer> switches = new ArrayList<Integer>();
		try (ResultSet rs = st.executeQuery(String.format("SELECT * FROM %s;", ModelConstants.SWITCH))) {
			while (rs.next()) {
				switches.add(rs.getInt("TrackElement_id"));
			}
		} catch (final SQLException e) {
			throw new IOException(e);
		}
		final Random random = bmr.getRandom();
		final int size = switches.size();
		final List<Integer> itemsToModify = new ArrayList<Integer>();
		for (int i = 0; i < nElemToModify; i++) {
			final int rndTarget = random.nextInt(size);
			final Integer aswitch = switches.get(rndTarget);
			itemsToModify.add(aswitch);
		}

		// edit
		final long startEdit = System.nanoTime();
		for (final Integer aswitch : itemsToModify) {
			try {
				st.executeUpdate(String.format("DELETE FROM %s WHERE TrackElement_id = %d;", ModelConstants.TRACKELEMENT_SENSOR, aswitch));
			} catch (final SQLException e) {
				throw new IOException(e);
			}
		}
		final long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}
}
