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
package hu.bme.mit.trainbenchmark.benchmark.rdf.driver;

import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.rdf.RdfHelper;

import java.io.IOException;

import static hu.bme.mit.trainbenchmark.rdf.RdfConstants.BASE_PREFIX;
import static hu.bme.mit.trainbenchmark.rdf.RdfConstants.ID_PREFIX;
import static hu.bme.mit.trainbenchmark.rdf.RdfConstants.RDF_TYPE;

public abstract class RdfDriver extends Driver {

	protected Long newVertexId = null;
	protected boolean inferencing;

	public RdfDriver(final boolean inferencing) {
		super();
		this.inferencing = inferencing;
	}

	protected Long determineInitialNewVertexId() throws Exception {
		Long id = 5000L;

		// safety measure to avoid infinite loop in case of a driver bug
		int iterationCount = 1;

		final String askQuery = "PREFIX base: <" + BASE_PREFIX + "> " //
				+ "PREFIX rdf:  <" + RDF_TYPE + "> " //
				+ "ASK { base:" + ID_PREFIX + "%d ?y ?z }";
		while (iterationCount <= 20 && ask(String.format(askQuery, id))) {
			id *= 2;
			iterationCount++;
		}
		if (iterationCount > 20) {
			throw new IOException("Could not generate new unique id.");
		}

		return id;
	}

	@Override
	public Long generateNewVertexId() throws Exception {
		if (newVertexId == null) {
			newVertexId = determineInitialNewVertexId();
		}
		newVertexId++;
		return newVertexId;
	}

	@Override
	public String getPostfix() {
		return RdfHelper.getPostfix(inferencing);
	}

	protected abstract boolean ask(String askQuery) throws Exception;

}
