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
package hu.bme.mit.trainbenchmark.benchmark.driver;

import hu.bme.mit.trainbenchmark.constants.Query;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public abstract class Driver<T> {

	// these methods should be redefined if required

	public void beginTransaction() throws Exception {
	}

	public void finishTransaction() throws Exception {
	}

	public void destroy() throws Exception {
	}

	// read methods

	public abstract void read(String modelPathWithoutExtension) throws Exception;

	public abstract List<T> collectVertices(final String type) throws Exception;

	// comparator

	public abstract Comparator<T> getElementComparator();

	// extension

	public abstract String getExtension();

	public abstract Collection<?> runQuery(final Query query, final String queryDefinition)
			throws Exception;

}
