/*******************************************************************************
 * Copyright (c) 2010-2014, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/

package hu.bme.mit.trainbenchmark.benchmark.virtuoso.driver;

import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.rdf.RDFConstants;

import java.io.File;
import java.io.IOException;

import org.openrdf.OpenRDFException;
import org.openrdf.rio.RDFFormat;

import virtuoso.sesame2.driver.VirtuosoRepository;

public class VirtuosoDriver extends SesameDriver {

	private final String VIRTUOSO_INSTANCE = "localhost";
	private final String VIRTUOSO_PORT = "1111";
	private final String VIRTUOSO_USERNAME = "dba";
	private final String VIRTUOSO_PASSWORD = "dba";

	protected VirtuosoRepository virtuosoRepository;

	@Override
	public void beginTransaction() {
		vf = virtuosoRepository.getValueFactory();
	}

	@Override
	public void read(final String modelPath) throws IOException {
		virtuosoRepository = new VirtuosoRepository("jdbc:virtuoso://" + VIRTUOSO_INSTANCE + ":" + VIRTUOSO_PORT, VIRTUOSO_USERNAME,
				VIRTUOSO_PASSWORD);
		
		final File modelFile = new File(modelPath + getExtension());
		try {
			virtuosoRepository.initialize();
			connection = virtuosoRepository.getConnection();
			connection.add(modelFile, RDFConstants.BASE_PREFIX, RDFFormat.TURTLE);
		} catch (final OpenRDFException e) {
			throw new IOException(e);
		}
	}

}
