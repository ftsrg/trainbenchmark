package hu.bme.mit.trainbenchmark.sql.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.sql.driver.SQLDriver;
import hu.bme.mit.trainbenchmark.sql.match.SQLMatch;

import java.io.IOException;
import java.util.Collection;

public abstract class SQLBenchmarkCase extends AbstractBenchmarkCase<SQLMatch, Long> {

	protected SQLDriver sqlDriver;

	protected String queryPath() {
		return bc.getWorkspacePath() + "/hu.bme.mit.trainbenchmark.sql/src/main/resources/queries/" + getName() + ".sql";
	}

	@Override
	protected void read() throws IOException {
		driver.read(bc.getModelPathNameWithoutExtension() + ".sql");
	}

	@Override
	protected Collection<SQLMatch> check() throws IOException {
		return matches = sqlDriver.runQuery(getName());
	}

	@Override
	protected void destroy() throws IOException {
		driver.destroy();
	}

}
