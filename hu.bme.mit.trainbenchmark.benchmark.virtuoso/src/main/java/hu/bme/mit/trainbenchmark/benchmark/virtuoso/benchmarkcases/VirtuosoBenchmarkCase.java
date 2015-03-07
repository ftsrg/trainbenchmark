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

package hu.bme.mit.trainbenchmark.benchmark.virtuoso.benchmarkcases;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.query.TupleQuery;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.OpenRDFException;
import org.openrdf.query.BindingSet;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.RepositoryException;
import org.openrdf.rio.RDFFormat;

import virtuoso.sesame2.driver.VirtuosoRepository;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.util.BenchmarkResult;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;
import hu.bme.mit.trainbenchmark.benchmark.virtuoso.driver.VirtuosoDriver;
import hu.bme.mit.trainbenchmark.rdf.RDFBenchmarkConfig;
import hu.bme.mit.trainbenchmark.rdf.RDFConstants;

public class VirtuosoBenchmarkCase extends AbstractBenchmarkCase<URI>{

	protected RDFBenchmarkConfig rdfConfig;
	
//	public static final String VIRTUOSO_INSTANCE = "localhost";
//	public static final String VIRTUOSO_PORT = "1111";
//	public static final String VIRTUOSO_USERNAME = "dba";
//	public static final String VIRTUOSO_PASSWORD = "dba";
//	
//	protected BenchmarkResult bmr;
//	protected List<URI> invalids;
//	protected TupleQuery tupleQuery;
//	protected RepositoryConnection con;
//	protected VirtuosoRepository myRepository;
//	protected String sparqlFilePath;
//	protected URI context;
	
	@Override
	public void init() throws IOException {
		this.rdfConfig = (RDFBenchmarkConfig) bc;
		final String queryPath = bc.getWorkspacePath() + "/hu.bme.mit.trainbenchmark.rdf/src/main/resources/queries/" + getName() + ".sparql";
		driver = new VirtuosoDriver(queryPath);
	}

//	@Override
//	public void load() throws IOException {
//		bmr.startStopper();
//		
//		myRepository = new VirtuosoRepository("jdbc:virtuoso://" + VIRTUOSO_INSTANCE + ":" + VIRTUOSO_PORT, VIRTUOSO_USERNAME, VIRTUOSO_PASSWORD);
//		//context = myRepository.getValueFactory().createURI(RDFConstants.BASE_PREFIX + "TrainModel");
//		try {
//			myRepository.initialize();
//			
//			final File documentFile = new File(vbc.getBenchmarkArtifact());
//			con = myRepository.getConnection();
//			//con.add(documentFile, RDFConstants.BASE_PREFIX, RDFFormat.TURTLE, context);
//			con.add(documentFile, RDFConstants.BASE_PREFIX, RDFFormat.TURTLE);
//			
//			if (vbc.isInferencing() == true){
//				System.out.println("Inferencing load");
//				myRepository.createRuleSet(RDFConstants.BASE_PREFIX + "TrainModel", RDFConstants.BASE_PREFIX + "TrainModel");
//				myRepository.setRuleSet(RDFConstants.BASE_PREFIX + "TrainModel");
//			}
//			
//			final String queryStringDef = Util.readFile(sparqlFilePath);
//			//String queryString = queryStringDef.replaceAll("WHERE", "FROM <" + context + "> WHERE");
//			//tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL, queryString);
//			tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL, queryStringDef);
//			
//					
//		}
//		catch (OpenRDFException e) {
//			throw new IOException(e);
//		}
//		
//		bmr.setReadTime();
//	}

//	@Override
//	public void check() throws IOException {
//		bmr.startStopper();
//		TupleQueryResult queryResults;
//		invalids = new ArrayList<URI>();
//		
//		try {
//			tupleQuery.setIncludeInferred(vbc.isInferencing());
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
//			}
//
//			finally {
//				queryResults.close();
//			}
//		} catch (QueryEvaluationException e) {
//			throw new IOException(e);
//		}
//		final int numberOfResults = invalids.size();
//		bmr.addInvalid(numberOfResults);
//		bmr.addCheckTime();
//	}

	@Override
	protected void read() throws IOException {
		driver.read(bc.getBenchmarkArtifact());
	}

	@Override
	protected Collection<URI> check() throws IOException {
		results = driver.runQuery();
		return results;
	}

}
