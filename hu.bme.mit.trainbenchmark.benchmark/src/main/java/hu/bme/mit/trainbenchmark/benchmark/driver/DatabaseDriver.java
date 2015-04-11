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

import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public abstract class DatabaseDriver<M, T> {

	protected Collection<M> matches;

	public void beginTransaction() throws IOException {
	}

	public void finishTransaction() throws IOException {
	}

	public abstract void read(String modelPathWithoutExtension) throws IOException;

	public abstract Collection<M> check() throws IOException;

	// comparators

	public abstract Comparator<M> getMatchComparator();

	public abstract Comparator<T> getElementComparator();

	public void destroy() throws IOException {
	}

	public abstract String getExtension();

	// read

	public abstract List<T> collectVertices(final String type) throws IOException;

}
