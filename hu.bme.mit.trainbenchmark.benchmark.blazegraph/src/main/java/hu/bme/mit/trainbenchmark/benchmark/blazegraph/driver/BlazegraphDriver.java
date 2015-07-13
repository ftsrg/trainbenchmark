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

import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;

import java.io.IOException;

import org.openrdf.repository.RepositoryException;
import org.openrdf.rio.RDFParseException;

import com.bigdata.rdf.model.BigdataValueFactoryImpl;
import com.bigdata.rdf.sail.remote.BigdataSailFactory;

public class BlazegraphDriver extends SesameDriver {

	protected final String BLAZEGRAPH_INSTANCE = "localhost";
	protected final int BLAZEGRAPH_PORT = 9999;

	@Override
	public void beginTransaction() {
		vf = BigdataValueFactoryImpl.getInstance("");
	}

	@Override
	public void read(final String modelPathWithoutExtension) throws IOException {
		repository = BigdataSailFactory.connect(BLAZEGRAPH_INSTANCE, BLAZEGRAPH_PORT);
		try {
			load(modelPathWithoutExtension);
		} catch (RepositoryException | RDFParseException e) {
			throw new IOException(e);
		}
	}

}
