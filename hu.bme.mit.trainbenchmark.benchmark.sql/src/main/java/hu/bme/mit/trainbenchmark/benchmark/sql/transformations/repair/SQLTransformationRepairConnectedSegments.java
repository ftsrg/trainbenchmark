/*******************************************************************************
 * Copyright (c) 2010-2015, Gabor Szarnyas, Benedek Izso, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.benchmark.sql.transformations.repair;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.CONNECTSTO;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.DEFINED_BY;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ID;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ROUTE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SEGMENT;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.TRACKELEMENT;
import static hu.bme.mit.trainbenchmark.sql.constants.SQLConstants.ID_POSTFIX;
import hu.bme.mit.trainbenchmark.benchmark.sql.driver.SQLDriver;
import hu.bme.mit.trainbenchmark.benchmark.sql.match.SQLConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

public class SQLTransformationRepairConnectedSegments extends SQLTransformationRepair<SQLConnectedSegmentsMatch> {

	public SQLTransformationRepairConnectedSegments(final SQLDriver sqlDriver) {
		super(sqlDriver);
	}

	@Override
	public void rhs(final Collection<SQLConnectedSegmentsMatch> matches) throws IOException {
		for (final SQLConnectedSegmentsMatch match : matches) {
			try {
				// segment2 node as segment
				final String deleteSegment2 = String.format("" + //
						"DELETE FROM `%s` " + //
						"WHERE `%s` = %d;", //
						SEGMENT, ID, match.getSegment2());
				// segment2 node as TrackELement and sensor edge
				final String deleteSegment2TrackElement = String.format("" + //
						"DELETE FROM `%s` " + //
						"WHERE `%s` = %d;", //
						TRACKELEMENT, ID, match.getSegment2());
				// (segment1)-[:connectsTo]->(segment2) edge
				final String deleteConnectsTo1 = String.format("" + //
						"DELETE FROM `%s` " + //
						"WHERE `TrackElement_id` = %d AND `TrackElement_id_connectsTo` = %d;", //
						CONNECTSTO, match.getSegment1(), match.getSegment2());
				// (segment2)-[:connectsTo]->(segment3) edge
				final String deleteConnectsTo2 = String.format("" + //
						"DELETE FROM `%s` " + //
						"WHERE `TrackElement_id` = %d  AND `TrackElement_id_connectsTo` = %d;", //
						CONNECTSTO, match.getSegment2(), match.getSegment3());
				// insert (segment1)-[:connectsTo]->(segment3) edge
				final String insertConnectsTo = String.format("" + //
						"INSERT INTO `%s` (`%s`, `%s`) " + //
						"VALUES (%d, %d);", //
						DEFINED_BY, ROUTE + ID_POSTFIX, ModelConstants.SENSOR + ID_POSTFIX, match.getSegment1(), match.getSegment3());

				sqlDriver.getConnection().createStatement().executeUpdate(deleteSegment2);
				sqlDriver.getConnection().createStatement().executeUpdate(deleteSegment2TrackElement);
				sqlDriver.getConnection().createStatement().executeUpdate(deleteConnectsTo1);
				sqlDriver.getConnection().createStatement().executeUpdate(deleteConnectsTo2);
				sqlDriver.getConnection().createStatement().executeUpdate(insertConnectsTo);
			} catch (final SQLException e) {
				throw new IOException(e);
			}
		}
	}
}
