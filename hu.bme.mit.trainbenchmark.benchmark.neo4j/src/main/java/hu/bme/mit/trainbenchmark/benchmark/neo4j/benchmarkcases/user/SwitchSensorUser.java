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

package hu.bme.mit.trainbenchmark.benchmark.neo4j.benchmarkcases.user;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.TransformationBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.benchmarkcases.SwitchSensor;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.util.TrainRelationship;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;

public class SwitchSensorUser extends SwitchSensor implements TransformationBenchmarkCase {

	@Override
	public void modify() {
		long nElemToModify = Util.calcModify(bc, bc.getModificationConstant(), bmr);
		bmr.addModifyParams(nElemToModify);

		long start = System.nanoTime();
		long startEdit = 0;

		try (Transaction tx = graphDb.beginTx()) {
			// query the model
			List<Node> switches = getNodesByTypes(graphDb, ModelConstants.SWITCH);

			Random random = bmr.getRandom();
			int size = switches.size();

			List<Node> itemsToModify = new ArrayList<Node>();
			for (int i = 0; i < nElemToModify; i++) {
				int rndTarget = random.nextInt(size);
				Node segment = switches.get(rndTarget);
				itemsToModify.add(segment);
			}

			// edit
			startEdit = System.nanoTime();

			for (Node sw : itemsToModify) {
				Iterable<Relationship> relationships = sw.getRelationships(TrainRelationship.TRACKELEMENT_SENSOR);

				for (Relationship relationship : relationships) {
					relationship.delete();
				}
			}

			tx.success();
		}

		long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}
}
