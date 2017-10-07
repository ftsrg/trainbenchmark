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

/**
 * Superclass for the drivers used in the benchmark.
 *
 */
public abstract class Driver {

	/**
	 * Should be overridden for tools with support for transactions.
	 */
	public void beginTransaction() throws Exception {
	}

	/**
	 * Should be overridden for tools with support for transactions.
	 */
	public void finishTransaction() throws Exception {
	}

	/**
	 * Should be overridden if the tool requires initialization.
	 */
	public void initialize() throws Exception {
	}

	/**
	 * Should be overridden if the tool requires freeing managed resources.
	 */
	public void destroy() throws Exception {
	}

	/**
	 * Reads the model form a textual format. For disk-resident databases, it persists the loaded data; for in-memory
	 * databases, it load the model to the memory.
	 *
	 * @param modelPath
	 *            path of the model (with the appropriate postfix)
	 */
	public abstract void read(String modelPath) throws Exception;

	/**
	 * @return The postfix of the model files. This contains both the postfix for the file and the extension, e.g.
	 *         "-sqlite.sql";
	 */
	public abstract String getPostfix();

	/**
	 * Generates an id for freshly inserted vertices.
	 *
	 * @return Previously unassigned id value. Specified as a {@code Number}
	 * as most tools either use integers or longs.
	 */
	public abstract Number generateNewVertexId() throws Exception;

}
