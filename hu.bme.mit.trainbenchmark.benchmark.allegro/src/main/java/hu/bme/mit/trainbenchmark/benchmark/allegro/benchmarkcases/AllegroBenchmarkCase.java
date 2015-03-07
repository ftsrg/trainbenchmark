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

package hu.bme.mit.trainbenchmark.benchmark.allegro.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.allegro.driver.AllegroDriver;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.rdf.RDFBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.benchmark.util.BenchmarkResult;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
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
import org.openrdf.rio.RDFFormat;

import com.franz.agraph.repository.AGCatalog;
import com.franz.agraph.repository.AGServer;

public class AllegroBenchmarkCase extends AbstractBenchmarkCase<URI>{

	
	protected RDFBenchmarkConfig rdfConfig;

//	protected List<URI> invalids;

//	protected String sparqlFilePath;
//	protected String sparqlQuery;

//	protected TupleQuery tupleQuery;
//	protected RepositoryConnection con;
//	protected Repository myRepository;
//	protected String prefix = "http://www.semanticweb.org/ontologies/2011/1/TrainRequirementOntology.owl#";
//	protected AGCatalog catalog;

	public String getResourceDirectory() {
		return bc.getWorkspacePath() + "/hu.bme.mit.trainbenchmark.rdf/src/main/resources/";
	}

	
	@Override
	protected void init() throws IOException {
		this.rdfConfig = (RDFBenchmarkConfig) bc;
		final String queryPath = bc.getWorkspacePath() + "/hu.bme.mit.trainbenchmark.rdf/src/main/resources/queries/" + getName() + ".sparql";
		driver = new AllegroDriver(queryPath);
	}
	
	@Override
	protected void read() throws IOException {
		driver.read(bc.getBenchmarkArtifact());
		
	}

	@Override
	protected Collection<URI> check() throws IOException {
		results = driver.runQuery();
		return results;
	}

	
	
	
	
	
	
	
	
	
	
//	public void init(final BenchmarkConfig bc) throws IOException {
//		this.bc = bc;
//		bmr = new BenchmarkResult(getTool(), getName());
//		bmr.setBenchmarkConfig(bc);
//
//		sparqlFilePath = bc.getWorkspacePath() + "/hu.bme.mit.trainbenchmark.rdf/src/main/resources/queries/"
//				+ getName() + ".sparql";
//		sparqlQuery = FileUtils.readFileToString(new File(sparqlFilePath));
//
//		Util.runGC();
//		if (bc.isBenchmarkMode()) {
//			Util.freeCache(bc);
//		}
//
//		AllegroProcess.startAG();
//	}
//
//	
//	public void load() throws IOException {
//		bmr.startStopper();
//
//		AGServer agServer = new AGServer(AGServerURL, AGServerUsername, AGServerPassword);
//
//		catalog = agServer.getCatalog(catalogID);
//
//		try {
//			if (catalog.hasRepository(repositoryID)) {
//				catalog.deleteRepository(repositoryID);
//			}
//
//			myRepository = catalog.createRepository(repositoryID);
//			myRepository.initialize();
//
//			String documentFilename = bc.getBenchmarkArtifact();
//			File documentFile = new File(documentFilename);
//
//			con = myRepository.getConnection();
//			con.add(documentFile, prefix, RDFFormat.TURTLE);
//
//			tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL, sparqlQuery);
//		} catch (OpenRDFException e) {
//			throw new IOException(e);
//		}
//
//		bmr.setReadTime();
//	}
//
//	
//	public void check() throws IOException {
//		bmr.startStopper();
//		TupleQueryResult queryResults;
//		invalids = new ArrayList<URI>();
//
//		try {
//			queryResults = tupleQuery.evaluate();
//			try {
//				final String bindingName = queryResults.getBindingNames().get(0);
//
//				while (queryResults.hasNext()) {
//					final BindingSet bs = queryResults.next();
//					final Value resultValue = bs.getValue(bindingName);
//					if (resultValue instanceof URI) {
//						invalids.add((URI) resultValue);
//					}
//				}
//			} finally {
//				queryResults.close();
//			}
//		} catch (final QueryEvaluationException e) {
//			throw new IOException(e);
//		}
//
//		final int numberOfResults = invalids.size();
//		bmr.addInvalid(numberOfResults);
//		bmr.addCheckTime();
//	}
//
//	@Override
//	public void measureMemory() {
//		Util.runGC();
//		bmr.addMemoryBytes(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
//	}
//
//	@Override
//	public void destroy() throws IOException {
//		try {
//			con.close();
//			catalog.deleteRepository(repositoryID);
//		} catch (final RepositoryException e) {
//			throw new IOException(e);
//		}
//	}
//
//	@Override
//	public BenchmarkResult getBenchmarkResult() {
//		return bmr;
//	}
//
//	@Override
//	public int getResultSize() {
//		return invalids.size();
//	}

}
