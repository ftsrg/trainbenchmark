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

package hu.bme.mit.trainbenchmark.benchmark.virtuoso.driver;

import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;

import java.io.IOException;

import org.openrdf.repository.RepositoryException;
import org.openrdf.rio.RDFParseException;

import virtuoso.sesame2.driver.VirtuosoRepository;

public class VirtuosoDriver extends SesameDriver {

	private final String VIRTUOSO_INSTANCE = "localhost";
	private final String VIRTUOSO_PORT = "1111";
	private final String VIRTUOSO_USERNAME = "dba";
	private final String VIRTUOSO_PASSWORD = "dba";

	@Override
	public void read(final String modelPathWithoutExtension) throws RepositoryException, RDFParseException, IOException {
		repository = new VirtuosoRepository("jdbc:virtuoso://" + VIRTUOSO_INSTANCE + ":" + VIRTUOSO_PORT, VIRTUOSO_USERNAME,
				VIRTUOSO_PASSWORD);
		load(modelPathWithoutExtension);
	}

}
