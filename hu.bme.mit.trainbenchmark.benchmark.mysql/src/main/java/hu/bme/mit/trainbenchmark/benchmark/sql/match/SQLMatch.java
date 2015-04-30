package hu.bme.mit.trainbenchmark.benchmark.sql.match;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.rdf.model.Resource;

public abstract class SQLMatch {

	protected QuerySolution qs;

	public SQLMatch(final QuerySolution qs) {
		this.qs = qs;
	}

	public abstract Resource[] toArray();

}
