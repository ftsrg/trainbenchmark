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

package hu.bme.mit.trainbenchmark.benchmark.blazegraph.driver;

import java.io.IOException;

import org.openrdf.repository.RepositoryException;
import org.openrdf.rio.RDFParseException;

import com.bigdata.rdf.model.BigdataValueFactoryImpl;
import com.bigdata.rdf.sail.remote.BigdataSailFactory;

import hu.bme.mit.trainbenchmark.benchmark.rdf.RDFBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;

public class BlazegraphDriver extends SesameDriver {

	protected final String BLAZEGRAPH_INSTANCE = "localhost";
	protected final int BLAZEGRAPH_PORT = 9999;

	public BlazegraphDriver(final RDFBenchmarkConfig rdfbc) {
		super(rdfbc);
	}

	@Override
	public void beginTransaction() {
		vf = BigdataValueFactoryImpl.getInstance("");
	}

	@Override
	public void read(final String modelPathWithoutExtension) throws IOException, RepositoryException, RDFParseException {
		repository = BigdataSailFactory.connect(BLAZEGRAPH_INSTANCE, BLAZEGRAPH_PORT);
		load(modelPathWithoutExtension);
	}

	@Override
	public void destroy() throws RepositoryException {
		super.destroy();
		repository.shutDown();
	}

}
