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

	@Override
	public String createQuery(final String queryPath, final String extension) throws IOException {
		return FileUtils.readFileToString(new File(queryPath + "StationsPath" + extension));
	}

	@Override
	public int getNumberOfQueries() {
		return 1;
	}

}
