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

/**
 * Superclass for the drivers used in the benchmark.
 * 
 * @author szarnyasg
 *
 * @param <TElement>
 *            the type of the individual model elements
 */
public abstract class Driver<TElement> {

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

	public abstract void read(String modelPath) throws Exception;

	public abstract Collection<TElement> collectVertices(final String type) throws Exception;

	// extension

	public abstract String getPostfix();

}
