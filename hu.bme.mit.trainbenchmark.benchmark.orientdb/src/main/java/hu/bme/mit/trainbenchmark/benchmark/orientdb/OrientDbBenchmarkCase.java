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
package hu.bme.mit.trainbenchmark.benchmark.orientdb;

import java.util.Comparator;

import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.checkers.OrientDbChecker;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.driver.OrientDbDriver;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.matches.OrientDbMatch;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.matches.OrientDbMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.transformations.OrientDbTransformation;

public class OrientDbBenchmarkCase extends AbstractBenchmarkCase<OrientDbMatch, Vertex> {

	protected OrientGraph graphDb;
	protected String dbPath;
	protected String benchmarkDir;

	protected OrientDbDriver orientDriver;

	@Override
	public void initialize() throws Exception {
		super.initialize();

		dbPath = bc.getWorkspacePath() + "models/orient-dbs/railway-database";
		benchmarkDir = bc.getWorkspacePath() + "/hu.bme.mit.trainbenchmark.benchmark.orientdb";
		driver = orientDriver = new OrientDbDriver(dbPath, benchmarkDir);
		checker = OrientDbChecker.newInstance(orientDriver, bc.getQuery());

		if (bc.getScenario().hasTranformation()) {
			transformation = OrientDbTransformation.newInstance(orientDriver, bc.getQuery(), bc.getScenario());
		}
	}

	@Override
	protected Comparator<?> getMatchComparator() {
		return new OrientDbMatchComparator();
	}

}
