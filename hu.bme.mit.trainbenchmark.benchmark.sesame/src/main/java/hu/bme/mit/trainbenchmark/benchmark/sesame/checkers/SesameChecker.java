package hu.bme.mit.trainbenchmark.benchmark.sesame.checkers;

import hu.bme.mit.trainbenchmark.benchmark.checker.Checker;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameMatch;
import hu.bme.mit.trainbenchmark.constants.Query;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;

public class SesameChecker extends Checker<SesameMatch> {

	protected final SesameDriver driver;
	protected final Query query;
	protected final String queryDefinition;

	public SesameChecker(final SesameDriver driver, final BenchmarkConfig bc) throws IOException {
		super();
		this.driver = driver;
		this.query = bc.getQuery();

		final String queryPath = bc.getWorkspacePath() + "/hu.bme.mit.trainbenchmark.benchmark.rdf/src/main/resources/queries/" + bc.getQuery()
				+ ".sparql";
		this.queryDefinition = FileUtils.readFileToString(new File(queryPath));
	}

	@Override
	public Collection<SesameMatch> check() throws IOException {
		return driver.runQuery(query, queryDefinition);
	}

}
