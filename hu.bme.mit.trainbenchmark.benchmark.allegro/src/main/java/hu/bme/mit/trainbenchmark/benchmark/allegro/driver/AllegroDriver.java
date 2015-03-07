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
import java.util.ArrayList;
import java.util.List;

import org.openrdf.OpenRDFException;
import org.openrdf.model.URI;
import org.openrdf.repository.RepositoryException;
import org.openrdf.rio.RDFFormat;
import com.franz.agraph.http.exception.AGHttpException;
import com.franz.agraph.repository.AGCatalog;
import com.franz.agraph.repository.AGServer;


public class AllegroDriver extends SesameDriver{

	protected AGCatalog catalog;
	
	// TODO store the parameters below in external configuration file 
	protected String AGServerURL = "http://localhost:10035";
	protected String AGServerUsername = "root";
	protected String AGServerPassword = "root";
	protected String catalogID = "system";
	protected String repositoryID = "train";
	
	public AllegroDriver(String queryPath) throws IOException {
		super(queryPath);
	}

	@Override
	public void destroy() throws IOException {
		try {
			con.close();
			catalog.deleteRepository(repositoryID);
		} catch (RepositoryException e) {
			throw new IOException();
		}
	}
	
	@Override
	public void read(String modelPath) throws IOException {
		AGServer agServer = new AGServer(AGServerURL, AGServerUsername, AGServerPassword);
		try{
			catalog = agServer.getCatalog(catalogID);
		} catch (AGHttpException e){
			throw new IOException(e);
		}
		try {
			if (catalog.hasRepository(repositoryID)) {
				catalog.deleteRepository(repositoryID);
			}

			repository = catalog.createRepository(repositoryID);
			repository.initialize();

			File modelFile = new File(modelPath);

			con = repository.getConnection();
			con.add(modelFile, RDFConstants.BASE_PREFIX, RDFFormat.TURTLE);
		} catch (OpenRDFException e) {
			throw new IOException(e);
		}
	}
}
