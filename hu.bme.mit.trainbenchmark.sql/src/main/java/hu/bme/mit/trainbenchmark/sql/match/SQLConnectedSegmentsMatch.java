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
package hu.bme.mit.trainbenchmark.sql.match;

import hu.bme.mit.trainbenchmark.benchmark.matches.ConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.constants.QueryConstants;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLConnectedSegmentsMatch extends SQLMatch implements ConnectedSegmentsMatch {

	public SQLConnectedSegmentsMatch(final ResultSet rs) throws SQLException {
		match = new Long[] {
				rs.getLong(QueryConstants.VAR_SENSOR), //
				rs.getLong(QueryConstants.VAR_SEGMENT1), rs.getLong(QueryConstants.VAR_SEGMENT2), rs.getLong(QueryConstants.VAR_SEGMENT3),
				rs.getLong(QueryConstants.VAR_SEGMENT4), rs.getLong(QueryConstants.VAR_SEGMENT5), rs.getLong(QueryConstants.VAR_SEGMENT6) };
	}

	@Override
	public Long getSensor() {
		return match[0];
	}

	@Override
	public Long getSegment1() {
		return match[1];
	}

	@Override
	public Long getSegment2() {
		return match[2];
	}

	@Override
	public Long getSegment3() {
		return match[3];
	}

	@Override
	public Long getSegment4() {
		return match[4];
	}

	@Override
	public Long getSegment5() {
		return match[5];
	}

	@Override
	public Long getSegment6() {
		return match[6];
	}

}
