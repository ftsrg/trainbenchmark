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

package hu.bme.mit.trainbenchmark.generator.util;

import java.util.ArrayList;

public class Node implements Comparable<Node> {

	public Object obj;

	public int degree;

	public int id;

	/**
	 * The indices of the connected nodes.
	 */
	public ArrayList<Integer> conn;

	public Node(Object obj, int degree) {
		this.obj = obj;
		this.degree = degree;
		this.conn = new ArrayList<Integer>();
	}

	public Node(int degree, int id) {
		this.degree = degree;
		this.id = id;
		this.conn = new ArrayList<Integer>();
	}

	public int lastConn() {
		return conn.get(conn.size() - 1);
	}

	@Override
	public int compareTo(Node o) {
		if (this.id > o.id) {
			return 1;
		} else if (this.id < o.id) {
			return -1;
		}
		return 0;
	}

}
