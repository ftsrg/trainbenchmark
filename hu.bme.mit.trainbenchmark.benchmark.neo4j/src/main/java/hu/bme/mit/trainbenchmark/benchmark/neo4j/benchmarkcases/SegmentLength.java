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

import java.util.HashMap;
import java.util.Map;

import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.tooling.GlobalGraphOperations;

public class SegmentLength extends Neo4jJavaBenchmarkCase {

	final Map<Long, Integer> segmentLength = new HashMap<>();

	@Override
	public String getName() {
		return "SegmentLength";
	}

	@Override
	public void check() {
		bmr.startStopper();

		final Transaction tx = graphDb.beginTx();

		for (final Node t : GlobalGraphOperations.at(graphDb).getAllNodesWithLabel(DynamicLabel.label(ModelConstants.SEGMENT))) {
			segmentLength.put(t.getId(), (Integer) t.getProperty(ModelConstants.SEGMENT_LENGTH, null));
		}

		tx.success();

		bmr.addInvalid(segmentLength.size());
		bmr.addCheckTime();
	}

	@Override
	public int getResultSize() {
		return segmentLength.size();
	}


}
