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

package hu.bme.mit.trainbenchmark.generator;

import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;

import java.util.ArrayList;

public abstract class ScheduleGenerator extends SyntheticGenerator {

	/**
	 * It equals to the sizeStep * size of the model.
	 */
	protected int maxNodes;

	/**
	 * Represents the current number of nodes.
	 */
	protected int nodes;

	public ScheduleGenerator(FormatGenerator formatGenerator, GeneratorConfig generatorConfig) {
		super(formatGenerator, generatorConfig);
	}

	protected ArrayList<Integer> getRandomIndices(final ArrayList<?> list, final int amount) {
		final int size = list.size();
		if (amount > size) {
			throw new IllegalArgumentException("Amount is bigger than the size of the list!");
		}

		ArrayList<Integer> indices = new ArrayList<Integer>();
		int count = 0;
		int index = 0;
		while (count < amount) {
			index = random.nextInt(size);
			if (!indices.contains(index)) {
				indices.add(index);
				count++;
			}
		}
		return indices;

	}

	protected Object getRandomElement(final ArrayList<?> list) {
		if (list.size() == 0) {
			throw new IllegalArgumentException("The list parameter is empty");
		}
		return list.get(random.nextInt(list.size()));

	}

	protected class Node {

		public Object obj;

		public int degree;

		/**
		 * The indices of the connected nodes.
		 */
		public ArrayList<Integer> conn;

		public Node(Object obj, int degree) {
			this.obj = obj;
			this.degree = degree;
			this.conn = new ArrayList<Integer>();
		}

		public int lastConn() {
			return conn.get(conn.size() - 1);
		}
	}
}