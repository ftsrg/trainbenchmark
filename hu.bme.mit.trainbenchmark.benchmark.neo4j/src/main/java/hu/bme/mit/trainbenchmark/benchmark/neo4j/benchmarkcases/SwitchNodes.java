/*******************************************************************************
 * Copyright (c) 2010-2014, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/

package hu.bme.mit.trainbenchmark.benchmark.neo4j.benchmarkcases;

import hu.bme.mit.trainbenchmark.constants.ModelConstants;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.tooling.GlobalGraphOperations;

public class SwitchNodes extends Neo4jJavaBenchmarkCase {

	final List<Long> switches = new ArrayList<>();

	@Override
	public String getName() {
		return "SwitchNodes";
	}

	@Override
	public void check() {
		bmr.startStopper();

		final Transaction tx = graphDb.beginTx();

		for (final Node t : GlobalGraphOperations.at(graphDb).getAllNodesWithLabel(DynamicLabel.label(ModelConstants.SWITCH))) {
			switches.add(t.getId());
		}

		tx.success();

		bmr.addInvalid(switches.size());
		bmr.addCheckTime();
	}

	@Override
	public int getResultSize() {
		return switches.size();
	}

}
