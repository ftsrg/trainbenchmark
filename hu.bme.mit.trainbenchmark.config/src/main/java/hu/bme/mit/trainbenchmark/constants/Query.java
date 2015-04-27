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
package hu.bme.mit.trainbenchmark.constants;

public enum Query {
	POSLENGTH(QueryConstants.POSLENGTH), //
	ROUTESENSOR(QueryConstants.ROUTESENSOR), //
	SEMAPHORENEIGHBOR(QueryConstants.SEMAPHORENEIGHBOR), //
	SWITCHSENSOR(QueryConstants.SWITCHSENSOR), //
	SWITCHSET(QueryConstants.SWITCHSET);

	private String name;

	Query(final String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}
}
