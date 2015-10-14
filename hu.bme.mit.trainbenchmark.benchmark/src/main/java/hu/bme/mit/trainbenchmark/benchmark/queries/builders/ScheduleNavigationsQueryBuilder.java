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
import hu.bme.mit.trainbenchmark.constants.schedule.ScheduleGeneratorConstants;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class ScheduleNavigationsQueryBuilder extends QueryBuilder {

	protected String queryName = "ScheduleNavigations";
	protected int maxNumberOfStations;

	public ScheduleNavigationsQueryBuilder(final int modelSize) {
		super();
		maxNumberOfQueries = 20;
		int maxNodes = (int) (ScheduleGeneratorConstants.sizeStep * Math.pow(2, modelSize - 1));
		maxNumberOfStations = (int) (maxNodes * ScheduleGeneratorConstants.stationsProportion);
	}

	@Override
	public String nextQuery(String queryPath, String extension) throws IOException {
		String rawQuery = FileUtils.readFileToString(new File(queryPath + queryName + extension));
		iteration++;
		return injectID(rawQuery);
	}

	protected String injectID(String rawQuery) {
		int sourceID = 0;
		sourceID = random.nextInt(maxNumberOfStations) + 1;
		return String.format(rawQuery, sourceID);
	}

}
