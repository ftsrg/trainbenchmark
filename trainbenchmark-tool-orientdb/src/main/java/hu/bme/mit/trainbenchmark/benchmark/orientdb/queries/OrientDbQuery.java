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
package hu.bme.mit.trainbenchmark.benchmark.orientdb.queries;

import hu.bme.mit.trainbenchmark.benchmark.operations.ModelQuery;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.driver.OrientDbDriver;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.matches.OrientDbMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class OrientDbQuery<TOrientDbMatch extends OrientDbMatch> extends ModelQuery<TOrientDbMatch, OrientDbDriver> {

	protected final RailwayQuery query;
	protected final List<String> lines;

	public OrientDbQuery(final OrientDbDriver driver, final String workspaceDir, final RailwayQuery query) throws IOException {
		super(query, driver);

		this.query = query;

		final String queryFile =
			workspaceDir +
			"/trainbenchmark-tool-orientdb/src/main/resources/queries/" + query + ".gremlin";
		lines = Files.lines(new File(queryFile).toPath()).collect(Collectors.toList());
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<TOrientDbMatch> evaluate() throws Exception {
		return (Collection<TOrientDbMatch>) driver.runQuery(lines, query);
	}

}
