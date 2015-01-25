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

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.Transformation;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.TransformationBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.benchmarkcases.RouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.util.TrainRelationship;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;

import java.util.List;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;

public class RouteSensorUser extends RouteSensor implements TransformationBenchmarkCase {

	@Override
	public void modify() {
		final long nElemToModify = Util.calcModify(bc, bc.getModificationConstant(), bmr);
		bmr.addModifyParams(nElemToModify);

		final long start = System.nanoTime();
		final long startEdit = 0;

		try (Transaction tx = graphDb.beginTx()) {
			// query the model
			final List<Node> routes = getNodesByTypes(graphDb, ModelConstants.ROUTE);

			final List<Node> itemsToModify = Transformation.pickRandom(nElemToModify, routes);

			// edit
			for (final Node route : itemsToModify) {
				final Iterable<Relationship> relationships = route.getRelationships(TrainRelationship.ROUTE_ROUTEDEFINITION);

				if (relationships.iterator().hasNext()) {
					relationships.iterator().next().delete();
				}
			}

			tx.success();
		}

		final long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}

}
