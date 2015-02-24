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

package hu.bme.mit.trainbenchmark.benchmark.sesame.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractTransformationBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.sesame.config.RDFBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;
import hu.bme.mit.trainbenchmark.rdf.RDFConstants;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openrdf.OpenRDFException;
import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.query.BindingSet;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.rio.RDFFormat;
import org.openrdf.sail.inferencer.fc.ForwardChainingRDFSInferencer;
import org.openrdf.sail.memory.MemoryStore;

public class SesameBenchmarkCase extends AbstractTransformationBenchmarkCase<URI> {

	protected RDFBenchmarkConfig sbc;

	protected TupleQuery tupleQuery;
	protected RepositoryConnection con;
	protected Repository repository;

	protected String sparqlFilePath;
	protected String sparqlQuery;

	public String getResourceDirectory() {
		return bc.getWorkspacePath() + "/hu.bme.mit.trainbenchmark.rdf/src/main/resources/";
	}

	@Override
	protected void init() throws IOException {
		this.sbc = (RDFBenchmarkConfig) bc;

		sparqlFilePath = bc.getWorkspacePath() + "/hu.bme.mit.trainbenchmark.rdf/src/main/resources/queries/" + getName() + ".sparql";
		sparqlQuery = FileUtils.readFileToString(new File(sparqlFilePath));
	}

	@Override
	public void read() throws IOException {
		if (sbc.isInferencing()) {
			repository = new SailRepository(new ForwardChainingRDFSInferencer(new MemoryStore()));
		} else {
			repository = new SailRepository(new MemoryStore());
		}

		try {
			repository.initialize();

			final File documentFile = new File(bc.getBenchmarkArtifact());

			con = repository.getConnection();
			con.add(documentFile, RDFConstants.BASE_PREFIX, RDFFormat.TURTLE);

			final String queryString = Util.readFile(sparqlFilePath);
			tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL, queryString);
		} catch (final OpenRDFException e) {
			throw new IOException(e);
		}

		driver = new SesameDriver(RDFConstants.BASE_PREFIX, con, repository);
	}

	@Override
	public List<URI> check() throws IOException {
		results = new ArrayList<>();
		TupleQueryResult queryResults;

		try {
			queryResults = tupleQuery.evaluate();
			try {
				final String bindingName = queryResults.getBindingNames().get(0);

				while (queryResults.hasNext()) {
					final BindingSet bs = queryResults.next();
					final Value resultValue = bs.getValue(bindingName);
					if (resultValue instanceof URI) {
						results.add((URI) resultValue);
					}
				}
			} finally {
				queryResults.close();
			}
		} catch (final QueryEvaluationException e) {
			throw new IOException(e);
		}

		return results;
	}

	@Override
	public void destroy() throws IOException {
		try {
			con.close();
		} catch (final RepositoryException e) {
			throw new IOException(e);
		}
	}

}
