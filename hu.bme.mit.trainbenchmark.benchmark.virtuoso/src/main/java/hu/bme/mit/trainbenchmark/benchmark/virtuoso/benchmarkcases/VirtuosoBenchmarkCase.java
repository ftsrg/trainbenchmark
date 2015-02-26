package hu.bme.mit.trainbenchmark.benchmark.virtuoso.benchmarkcases;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.BenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.util.BenchmarkResult;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;
import hu.bme.mit.trainbenchmark.benchmark.virtuoso.config.VirtuosoBenchmarkConfig;
import hu.bme.mit.trainbenchmark.rdf.RDFConstants;

public abstract class VirtuosoBenchmarkCase implements BenchmarkCase{

	public static final String VIRTUOSO_INSTANCE = "localhost";
	public static final String VIRTUOSO_PORT = "1111";
	public static final String VIRTUOSO_USERNAME = "dba";
	public static final String VIRTUOSO_PASSWORD = "dba";
	
	protected BenchmarkResult bmr;
	protected VirtuosoBenchmarkConfig vbc;
	protected List<URI> invalids;
	protected TupleQuery tupleQuery;
	protected RepositoryConnection con;
	protected VirtuosoRepository myRepository;
	protected String sparqlFilePath;
	protected URI context;
	
	public String getTool() {
		return "Virtuoso";
	}

	@Override
	public BenchmarkResult getBenchmarkResult() {
		return bmr;
	}

	@Override
	public void init(BenchmarkConfig bc) throws IOException {
		this.vbc = (VirtuosoBenchmarkConfig) bc;
		this.bmr = new BenchmarkResult(getTool(), getName());
		bmr.setBenchmarkConfig(vbc);
		
		sparqlFilePath = bc.getWorkspacePath() + "/hu.bme.mit.trainbenchmark.rdf/src/main/resources/queries/" + getName() + ".sparql";
		
		Util.runGC();
		if (bc.isBenchmarkMode()) {
			Util.freeCache(bc);
		}
	}

	@Override
	public void load() throws IOException {
		bmr.startStopper();
		
		myRepository = new VirtuosoRepository("jdbc:virtuoso://" + VIRTUOSO_INSTANCE + ":" + VIRTUOSO_PORT, VIRTUOSO_USERNAME, VIRTUOSO_PASSWORD);
		//context = myRepository.getValueFactory().createURI(RDFConstants.BASE_PREFIX + "TrainModel");
		try {
			myRepository.initialize();
			
			final File documentFile = new File(vbc.getBenchmarkArtifact());
			con = myRepository.getConnection();
			//con.add(documentFile, RDFConstants.BASE_PREFIX, RDFFormat.TURTLE, context);
			con.add(documentFile, RDFConstants.BASE_PREFIX, RDFFormat.TURTLE);
			
			if (vbc.isInferencing() == true){
				System.out.println("Inferencing load");
				myRepository.createRuleSet(RDFConstants.BASE_PREFIX + "TrainModel", RDFConstants.BASE_PREFIX + "TrainModel");
				myRepository.setRuleSet(RDFConstants.BASE_PREFIX + "TrainModel");
			}
			
			final String queryStringDef = Util.readFile(sparqlFilePath);
			//String queryString = queryStringDef.replaceAll("WHERE", "FROM <" + context + "> WHERE");
			//tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL, queryString);
			tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL, queryStringDef);
			
					
		}
		catch (OpenRDFException e) {
			throw new IOException(e);
		}
		
		bmr.setReadTime();
	}

	@Override
	public void check() throws IOException {
		bmr.startStopper();
		TupleQueryResult queryResults;
		invalids = new ArrayList<URI>();
		
		try {
			tupleQuery.setIncludeInferred(vbc.isInferencing());
			queryResults = tupleQuery.evaluate();
			try {
				final String bindingName = queryResults.getBindingNames().get(0);

				while (queryResults.hasNext()) {
					final BindingSet bs = queryResults.next();
					final Value resultValue = bs.getValue(bindingName);
					if (resultValue instanceof URI) {
						invalids.add((URI) resultValue);
					}
				}
			}

			finally {
				queryResults.close();
			}
		} catch (QueryEvaluationException e) {
			throw new IOException(e);
		}
		final int numberOfResults = invalids.size();
		bmr.addInvalid(numberOfResults);
		bmr.addCheckTime();
	}

	@Override
	public void measureMemory() throws IOException {
		Util.runGC();
		bmr.addMemoryBytes(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
	}
	
	@Override
	public void destroy() throws IOException {
		try {
			con.clear(context);
			con.close();
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
	}
	
	public List<URI> getInvalids() {
		return invalids;
	}
	
	@Override
	public int getResultSize() {
		return invalids.size();
	}

}
