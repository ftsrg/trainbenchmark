/*******************************************************************************
 * Copyright (c) 2010-2015, Gabor Szarnyas, Benedek Izso, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.benchmark.neo4j.driver;

import java.util.Comparator;

import org.neo4j.graphdb.Node;

public class NodeComparator implements Comparator<Node> {

	@Override
	public int compare(final Node node1, final Node node2) {
		final long id1 = node1.getId();
		final long id2 = node2.getId();
		return Long.compare(id1, id2);
	}

}
