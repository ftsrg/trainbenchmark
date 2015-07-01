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
package hu.bme.mit.trainbenchmark.benchmark.orientdb.checkers;

import hu.bme.mit.trainbenchmark.benchmark.orientdb.driver.OrientDbDriver;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.matches.OrientDbSemaphoreNeighborMatch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.tinkerpop.pipes.util.structures.Row;

public class OrientDbSemaphoreNeighborChecker extends OrientDbChecker<OrientDbSemaphoreNeighborMatch> {

	public OrientDbSemaphoreNeighborChecker(final OrientDbDriver orientDriver) {
		super(orientDriver);
	}

	@Override
	public Collection<OrientDbSemaphoreNeighborMatch> check() throws IOException {
		
		final Collection<OrientDbSemaphoreNeighborMatch> matches = new ArrayList<OrientDbSemaphoreNeighborMatch>();
		List<String> lines = FileUtils.readLines(FileUtils.getFile(queryPath + "SemaphoreNeighbor.gremlin"));
		List<Row> result = driver.runQuery(lines);
		
		for (Row row : result) {
			matches.add(new OrientDbSemaphoreNeighborMatch(row));
		}
		
		return matches;
	}

}
