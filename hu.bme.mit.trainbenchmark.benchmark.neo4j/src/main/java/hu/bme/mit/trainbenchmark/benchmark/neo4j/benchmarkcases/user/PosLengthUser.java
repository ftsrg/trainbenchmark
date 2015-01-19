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

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SEGMENT;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SEGMENT_LENGTH;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.TransformationBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.benchmarkcases.PosLength;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

public class PosLengthUser extends PosLength implements TransformationBenchmarkCase {

	@Override
	public void modify() {
		final long nElemToModify = Util.calcModify(bc, bc.getModificationConstant(), bmr);
		bmr.addModifyParams(nElemToModify);

		final long start = System.nanoTime();
		long startEdit = 0;

		try (Transaction tx = graphDb.beginTx()) {
			// query the model
			final List<Node> segments = getNodesByTypes(graphDb, SEGMENT);

			final Random random = bmr.getRandom();
			final int size = segments.size();
			final List<Node> itemsToModify = new ArrayList<Node>();

			for (int i = 0; i < nElemToModify; i++) {
				final int rndTarget = random.nextInt(size);
				final Node segment = segments.get(rndTarget);
				itemsToModify.add(segment);
			}

			// edit
			startEdit = System.nanoTime();

			for (final Node segment : itemsToModify) {
				segment.setProperty(SEGMENT_LENGTH, 0);
			}

			tx.success();
		}

		final long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}

}
