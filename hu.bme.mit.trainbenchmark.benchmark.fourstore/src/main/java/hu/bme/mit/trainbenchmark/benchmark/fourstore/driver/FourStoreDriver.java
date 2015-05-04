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
package hu.bme.mit.trainbenchmark.benchmark.fourstore.driver;

import static hu.bme.mit.trainbenchmark.rdf.RDFConstants.BASE_PREFIX;
import hu.bme.mit.trainbenchmark.benchmark.matches.LongComparator;
import hu.bme.mit.trainbenchmark.rdf.RDFDatabaseDriver;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.io.FileUtils;

import eu.mondo.driver.fourstore.FourStoreGraphDriverReadWrite;

public class FourStoreDriver extends RDFDatabaseDriver<Long> {

	protected static final String CLUSTERNAME = "trainbenchmark_cluster";

	protected FourStoreGraphDriverReadWrite driver;

	// protected final String queryDefinition;
	// this.queryDefinition = FileUtils.readFileToString(new File(queryPath));

	public FourStoreDriver(final String queryPath) throws IOException {
		final String dbPath = "/var/lib/4store/" + CLUSTERNAME;
		if (new File(dbPath).exists()) {
			FileUtils.deleteDirectory(new File(dbPath));
		}

		this.driver = new FourStoreGraphDriverReadWrite(CLUSTERNAME);
		driver.start();
	}

	@Override
	public void read(final String modelPath) throws IOException {
		try {
			driver.load(modelPath);
		} catch (final InterruptedException e) {
			throw new IOException(e);
		}
	}

	public List<Long> runQuery(final String query) throws IOException {
		final List<Long> results = driver.queryIds(query);
		return results;
	}

	@Override
	public void destroy() throws IOException {
		driver.stop();
	}

	// read

	@Override
	public List<Long> collectVertices(final String type) throws IOException {
		return driver.collectVertices(BASE_PREFIX + type);
	}

	// utility

	@Override
	protected boolean ask(final String askQuery) throws IOException {
		return driver.ask(askQuery);
	}

	@Override
	public Comparator<Long> getElementComparator() {
		return new LongComparator();
	}

}
