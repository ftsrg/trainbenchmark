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

package hu.bme.mit.trainbenchmark.benchmark.allegro.driver;

import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;

import java.io.IOException;

import org.openrdf.OpenRDFException;
import org.openrdf.repository.RepositoryException;

import com.franz.agraph.repository.AGCatalog;
import com.franz.agraph.repository.AGServer;

public class AllegroDriver extends SesameDriver {

	protected AGCatalog catalog;

	protected String AGServerURL = "http://localhost:10035";
	protected String AGServerUsername = "super";
	protected String AGServerPassword = "super";
	protected String catalogID = "system";
	protected String repositoryID = "train";

	@Override
	public void destroy() throws RepositoryException {
		connection.close();
		catalog.deleteRepository(repositoryID);
	}

	@Override
	public void read(final String modelPathWithoutExtension) throws RepositoryException, OpenRDFException, IOException {
		final AGServer agServer = new AGServer(AGServerURL, AGServerUsername, AGServerPassword);
		catalog = agServer.getCatalog(catalogID);
		if (catalog.hasRepository(repositoryID)) {
			catalog.deleteRepository(repositoryID);
		}
		repository = catalog.createRepository(repositoryID);

		load(modelPathWithoutExtension);
	}
}
