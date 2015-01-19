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
import hu.bme.mit.trainbenchmark.benchmark.mysql.benchmarkcases.PosLength;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PosLengthUser extends PosLength implements TransformationBenchmarkCase {

	@Override
	public void modify() throws IOException {
		long nElemToModify = Util.calcModify(bc, bc.getModificationConstant(), bmr);
		bmr.addModifyParams(nElemToModify);

		// modify
		long start = System.nanoTime();
		List<Integer> segments = new ArrayList<Integer>();

		try (ResultSet rs = st.executeQuery(String.format("SELECT * FROM %s;", ModelConstants.SEGMENT))) {
			while (rs.next()) {
				segments.add(rs.getInt("Trackelement_id"));
			}
		} catch (SQLException e) {
			throw new IOException(e);
		}
		Random random = bmr.getRandom();
		int size = segments.size();
		List<Integer> itemsToModify = new ArrayList<Integer>();
		for (int i = 0; i < nElemToModify; i++) {
			int rndTarget = random.nextInt(size);
			Integer segment = segments.get(rndTarget);
			itemsToModify.add(segment);
		}

		// edit
		long startEdit = System.nanoTime();
		for (Integer segment : itemsToModify) {
			try {
				st.executeUpdate(String.format("UPDATE %s SET %s = 0 WHERE Trackelement_id = %d;", ModelConstants.SEGMENT,
						ModelConstants.SEGMENT_LENGTH, segment));
			} catch (SQLException e) {
				throw new IOException(e);
			}
		}
		long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}

}
