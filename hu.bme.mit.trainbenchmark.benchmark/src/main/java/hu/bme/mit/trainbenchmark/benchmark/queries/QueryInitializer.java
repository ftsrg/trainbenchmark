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

package hu.bme.mit.trainbenchmark.benchmark.queries;

import hu.bme.mit.trainbenchmark.benchmark.queries.builders.StationsPathQueryBuilder;
import hu.bme.mit.trainbenchmark.constants.Query;

import java.io.IOException;

public class QueryInitializer {

	protected Query query;
	protected QueryBuilder queryBuilder;
	protected int modelSize;

	public QueryInitializer(final Query query) {
		this.query = query;
	}

	public String resolveQuery(final String queryPath, final String extension) throws IOException {
		if (queryBuilder == null) {
			createBuilder();
		}
		return queryBuilder.nextQuery(queryPath, extension);
	}

	protected void createBuilder() {
		switch (query) {
		case STATIONSPATH:
			queryBuilder = new StationsPathQueryBuilder(modelSize);
			break;
		default:
			throw new IllegalArgumentException("Query is not supported: " + query);
		}
	}

	public void setQuery(Query query) {
		this.query = query;
	}

	public QueryBuilder getQueryBuilder() {
		if (queryBuilder == null) {
			createBuilder();
		}
		return queryBuilder;
	}

	public void setQueryBuilder(QueryBuilder queryBuilder) {
		this.queryBuilder = queryBuilder;
	}

}
