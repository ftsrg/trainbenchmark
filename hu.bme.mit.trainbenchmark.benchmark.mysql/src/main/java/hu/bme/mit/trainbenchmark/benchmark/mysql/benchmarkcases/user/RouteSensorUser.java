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
import hu.bme.mit.trainbenchmark.benchmark.mysql.benchmarkcases.RouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RouteSensorUser extends RouteSensor implements TransformationBenchmarkCase {

	@Override
	public void modify() throws IOException {
		long nElemToModify = Util.calcModify(bc, bc.getModificationConstant(), bmr);
		bmr.addModifyParams(nElemToModify);

		// modify
		long start = System.nanoTime();
		List<Integer> routes = new ArrayList<Integer>();
		try (ResultSet rs = st.executeQuery(String.format("SELECT * FROM %s;", ModelConstants.ROUTE))) {
			while (rs.next()) {
				routes.add(rs.getInt("id"));
			}
		} catch (SQLException e) {
			throw new IOException(e);
		}

		Random random = bmr.getRandom();
		int size = routes.size();
		List<Integer> itemsToModify = new ArrayList<Integer>();
		for (int i = 0; i < nElemToModify; i++) {
			int rndTarget = random.nextInt(size);
			Integer route = routes.get(rndTarget);
			itemsToModify.add(route);
		}

		// edit
		long startEdit = System.nanoTime();
		for (Integer route : itemsToModify) {
			int routedef_size = 0;
			try (ResultSet rs = st.executeQuery(String.format("SELECT COUNT(*) FROM %s WHERE Route_id = %d;",
					ModelConstants.ROUTE_ROUTEDEFINITION, route))) {
				if (rs.next()) {
					routedef_size = rs.getInt(1);
				}
			} catch (SQLException e) {
				throw new IOException(e);
			}
			if (routedef_size > 0) {
				try {
					st.executeUpdate(String.format("DELETE FROM %s WHERE Route_id = %d LIMIT 1;", ModelConstants.ROUTE_ROUTEDEFINITION,
							route));
				} catch (SQLException e) {
					throw new IOException(e);
				}
			}
		}
		long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}
}
