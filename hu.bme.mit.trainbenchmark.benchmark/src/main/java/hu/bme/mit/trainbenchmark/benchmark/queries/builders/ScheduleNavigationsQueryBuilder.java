/*******************************************************************************
 * Copyright (c) 2010-2015, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/

package hu.bme.mit.trainbenchmark.benchmark.queries.builders;

import hu.bme.mit.trainbenchmark.benchmark.queries.QueryBuilder;
import hu.bme.mit.trainbenchmark.constants.QuerySubtypes;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.enums.EnumUtils;

public class ScheduleNavigationsQueryBuilder extends QueryBuilder {

	protected QuerySubtypes querySubtype;
	protected String queryName = "ScheduleNavigations";

	public ScheduleNavigationsQueryBuilder() {
		super();
		maxNumberOfQueries = 5;
		querySubtype = QuerySubtypes.QUERY_A;
	}

	@Override
	public String nextQuery(String queryPath, String extension) throws IOException {
		String query = FileUtils.readFileToString(new File(queryPath + queryName + "-"
				+ querySubtype.getName() + extension));
		iteration++;
		if (querySubtype.getName() == "E") {
			querySubtype = QuerySubtypes.QUERY_A;
		} else {
			querySubtype = (QuerySubtypes) EnumUtils.getEnum(QuerySubtypes.class,
					querySubtype.getValue() + 1);
		}
		return query;
	}

}
