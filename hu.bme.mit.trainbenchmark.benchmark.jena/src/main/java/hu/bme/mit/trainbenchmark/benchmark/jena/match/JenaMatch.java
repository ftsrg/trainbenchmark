package hu.bme.mit.trainbenchmark.benchmark.jena.match;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.rdf.model.Resource;

public abstract class JenaMatch {

	protected QuerySolution qs;

	public JenaMatch(final QuerySolution qs) {
		this.qs = qs;
	}

	public abstract Resource[] toArray();

}
