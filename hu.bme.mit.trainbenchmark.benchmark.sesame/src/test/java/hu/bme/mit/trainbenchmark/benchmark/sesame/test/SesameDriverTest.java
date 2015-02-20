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
package hu.bme.mit.trainbenchmark.benchmark.sesame.test;

import hu.bme.mit.trainbenchmark.benchmark.driver.DatabaseDriverTest;
import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.rdf.RDFConstants;

import java.io.File;
import java.io.IOException;

import org.openrdf.model.Literal;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.model.ValueFactory;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.RepositoryResult;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFParseException;
import org.openrdf.sail.memory.MemoryStore;

public class SesameDriverTest extends DatabaseDriverTest {

	protected RepositoryConnection con; 
	protected Repository repository;
	
	@Override
	public void init() throws IOException {
		final String modelPath = "../models/railway-test-1.ttl";

		repository = new SailRepository(new MemoryStore());
		try {
			repository.initialize();

			final File modelFile = new File(modelPath);
			con = repository.getConnection();
			con.add(modelFile, RDFConstants.BASE_PREFIX, RDFFormat.TURTLE);

			driver = new SesameDriver(RDFConstants.BASE_PREFIX, con, repository);
		} catch (final RepositoryException | RDFParseException e) {
			throw new IOException(e);
		}
	}

	@Override
	protected long extractLength(final Object segment) throws IOException {
		final ValueFactory f = repository.getValueFactory();

		final URI segmentURI = (URI) segment;
		final URI predicate = f.createURI(RDFConstants.BASE_PREFIX + ModelConstants.SEGMENT_LENGTH);
		try {
			final RepositoryResult<Statement> statements = con.getStatements(segmentURI, predicate, null, true);
			
			while (statements.hasNext()) {
				final Statement s = statements.next();
				final Value value = s.getObject();
				final Literal literal = (Literal) value;
				return literal.longValue();
			}
			
		} catch (final RepositoryException e) {
			throw new IOException(e);
		}
		
		throw new IOException("Couldn't extract length from object.");
	}

}
