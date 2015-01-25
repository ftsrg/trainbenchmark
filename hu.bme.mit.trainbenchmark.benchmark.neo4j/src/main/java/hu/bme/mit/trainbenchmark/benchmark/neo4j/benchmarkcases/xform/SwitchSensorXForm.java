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

package hu.bme.mit.trainbenchmark.benchmark.neo4j.benchmarkcases.xform;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.Transformation;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.TransformationBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.benchmarkcases.SwitchSensor;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.util.TrainRelationship;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;

import java.util.List;

import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

public class SwitchSensorXForm extends SwitchSensor implements TransformationBenchmarkCase {

	@Override
	public void modify() {
		final long nElemToModify = Util.calcModify(bc, bc.getModificationConstant(), bmr);
		bmr.addModifyParams(nElemToModify);

		final long start = System.nanoTime();
		long startEdit;

		// pick the elements for transformation
		final List<Node> itemsToModify = Transformation.pickRandom(nElemToModify, invalids);

		try (Transaction tx = graphDb.beginTx()) {
			// edit
			startEdit = System.nanoTime();

			for (final Node sw : itemsToModify) {
				final Node sensor = graphDb.createNode();

				// automatic indexing ensures that the new node will be indexed
				// by its type attribute
				sensor.addLabel(DynamicLabel.label(ModelConstants.SENSOR));
				sw.createRelationshipTo(sensor, TrainRelationship.TRACKELEMENT_SENSOR);
			}

			// finishing Neo4j transaction
			tx.success();
		}

		final long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}

}
