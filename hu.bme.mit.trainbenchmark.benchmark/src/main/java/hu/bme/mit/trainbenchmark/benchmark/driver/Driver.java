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

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import hu.bme.mit.trainbenchmark.constants.Query;

public abstract class Driver<TMatch> {

	// these methods should be redefined if required

	public void beginTransaction() throws Exception {
	}

	public void finishTransaction() throws Exception {
	}

	public void initialize() throws Exception {
	}

	public void destroy() throws Exception {
	}

	// read methods

	public abstract void read(String modelPathWithoutExtension) throws Exception;

	public abstract List<TMatch> collectVertices(final String type) throws Exception;

	// comparator

	public abstract Comparator<TMatch> getElementComparator();

	// extension

	public abstract String getPostfix();

	public abstract Collection<?> runQuery(final Query query, final String queryDefinition) throws Exception;

}
