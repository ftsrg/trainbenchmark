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

package hu.bme.mit.trainbenchmark.benchmark.allegro.driver;

import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.rdf.RDFConstants;

import java.io.File;
import java.io.IOException;

import org.openrdf.OpenRDFException;
import org.openrdf.repository.RepositoryException;
import org.openrdf.rio.RDFFormat;

import com.franz.agraph.http.exception.AGHttpException;
import com.franz.agraph.repository.AGCatalog;
import com.franz.agraph.repository.AGServer;

public class AllegroDriver extends SesameDriver {

	protected AGCatalog catalog;

	// TODO store the parameters below in external configuration file
	protected String AGServerURL = "http://localhost:10035";
	protected String AGServerUsername = "super";
	protected String AGServerPassword = "super";
	protected String catalogID = "system";
	protected String repositoryID = "train";

	@Override
	public void destroy() throws IOException {
		try {
			connection.close();
			catalog.deleteRepository(repositoryID);
		} catch (final RepositoryException e) {
			throw new IOException();
		}
	}

	@Override
	public void read(final String modelPath) throws IOException {
		final AGServer agServer = new AGServer(AGServerURL, AGServerUsername, AGServerPassword);
		try {
			catalog = agServer.getCatalog(catalogID);
		} catch (final AGHttpException e) {
			throw new IOException(e);
		}
		try {
			if (catalog.hasRepository(repositoryID)) {
				catalog.deleteRepository(repositoryID);
			}

			repository = catalog.createRepository(repositoryID);
			repository.initialize();

			final File modelFile = new File(modelPath + getExtension());

			connection = repository.getConnection();
			connection.add(modelFile, RDFConstants.BASE_PREFIX, RDFFormat.TURTLE);
		} catch (final OpenRDFException e) {
			throw new IOException(e);
		}
	}
}
