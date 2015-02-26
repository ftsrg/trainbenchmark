package hu.bme.mit.trainbenchmark.benchmark.allegro.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.BenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.util.BenchmarkResult;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;

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
import org.openrdf.rio.RDFFormat;

import com.franz.agraph.repository.AGCatalog;
import com.franz.agraph.repository.AGServer;

public abstract class AllegroBenchmarkCase implements BenchmarkCase {

	@Override
	public String getTool() {
		return "Allegro";
	}

	protected BenchmarkResult bmr;
	protected BenchmarkConfig bc;
	protected List<URI> invalids;

	protected String sparqlFilePath;
	protected String sparqlQuery;

	protected TupleQuery tupleQuery;
	protected RepositoryConnection con;
	protected Repository myRepository;
	protected String prefix = "http://www.semanticweb.org/ontologies/2011/1/TrainRequirementOntology.owl#";
	protected AGCatalog catalog;

	protected String AGServerURL = "http://localhost:10035";
	protected String AGServerUsername = "root";
	protected String AGServerPassword = "root";
	protected String catalogID = "system";
	protected String repositoryID = "train";

	public String getResourceDirectory() {
		return bc.getWorkspacePath() + "/hu.bme.mit.trainbenchmark.rdf/src/main/resources/";
	}

	@Override
	public void init(final BenchmarkConfig bc) throws IOException {
		this.bc = bc;
		bmr = new BenchmarkResult(getTool(), getName());
		bmr.setBenchmarkConfig(bc);

		sparqlFilePath = bc.getWorkspacePath() + "/hu.bme.mit.trainbenchmark.rdf/src/main/resources/queries/"
				+ getName() + ".sparql";
		sparqlQuery = FileUtils.readFileToString(new File(sparqlFilePath));

		Util.runGC();
		if (bc.isBenchmarkMode()) {
			Util.freeCache(bc);
		}

		AllegroProcess.startAG();
	}

	@Override
	public void load() throws IOException {
		bmr.startStopper();

		AGServer agServer = new AGServer(AGServerURL, AGServerUsername, AGServerPassword);

		catalog = agServer.getCatalog(catalogID);

		try {
			if (catalog.hasRepository(repositoryID)) {
				catalog.deleteRepository(repositoryID);
			}

			myRepository = catalog.createRepository(repositoryID);
			myRepository.initialize();

			String documentFilename = bc.getBenchmarkArtifact();
			File documentFile = new File(documentFilename);

			con = myRepository.getConnection();
			con.add(documentFile, prefix, RDFFormat.TURTLE);

			tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL, sparqlQuery);
		} catch (OpenRDFException e) {
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
			} finally {
				queryResults.close();
			}
		} catch (final QueryEvaluationException e) {
			throw new IOException(e);
		}

		final int numberOfResults = invalids.size();
		bmr.addInvalid(numberOfResults);
		bmr.addCheckTime();
	}

	@Override
	public void measureMemory() {
		Util.runGC();
		bmr.addMemoryBytes(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
	}

	@Override
	public void destroy() throws IOException {
		try {
			con.close();
			catalog.deleteRepository(repositoryID);
		} catch (final RepositoryException e) {
			throw new IOException(e);
		}
	}

	@Override
	public BenchmarkResult getBenchmarkResult() {
		return bmr;
	}

	@Override
	public int getResultSize() {
		return invalids.size();
	}

}
