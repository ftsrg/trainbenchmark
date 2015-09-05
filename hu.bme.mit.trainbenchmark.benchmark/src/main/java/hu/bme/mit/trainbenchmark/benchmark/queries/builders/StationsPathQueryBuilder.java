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

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class StationsPathQueryBuilder implements QueryBuilder {

	protected int iteration;
	protected int maxNumberOfQueries;

	public StationsPathQueryBuilder(final int modelSize) {
		iteration = 0;
		maxNumberOfQueries = 5;
	}

	@Override
	public String nextQuery(final String queryPath, final String extension) throws IOException {
		String rawQuery = FileUtils
				.readFileToString(new File(queryPath + "StationsPath" + extension));
		iteration++;
		return inject(rawQuery);
	}

	@Override
	public int getNumberOfQueries() {
		return maxNumberOfQueries;
	}

	protected String inject(String rawQuery) {
		return String.format(rawQuery, "3", "25");
	}

}
