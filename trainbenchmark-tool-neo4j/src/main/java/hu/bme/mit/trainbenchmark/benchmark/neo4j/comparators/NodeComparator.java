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
package hu.bme.mit.trainbenchmark.benchmark.neo4j.comparators;

import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import org.neo4j.graphdb.Node;

import java.util.Comparator;

public class NodeComparator implements Comparator<Node> {

	@Override
	public int compare(final Node node1, final Node node2) {
		final long id1 = getLongId(node1);
		final long id2 = getLongId(node2);
		return Long.compare(id1, id2);
	}

	private long getLongId(Node node) {
		Object o = node.getProperty(ModelConstants.ID);
		if (o instanceof Long) {
			return (Long) o;
		}
		if (o instanceof Integer) {
			return Long.valueOf((Integer) o);
		}
		throw new IllegalStateException("ID should be int or long");
	}

}
