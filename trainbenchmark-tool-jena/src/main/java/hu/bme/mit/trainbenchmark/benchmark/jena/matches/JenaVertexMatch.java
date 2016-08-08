package hu.bme.mit.trainbenchmark.benchmark.jena.matches;

import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_VERTEX;

import org.apache.jena.query.QuerySolution;
import org.apache.jena.rdf.model.Resource;

public class JenaVertexMatch extends JenaMatch {

	public JenaVertexMatch(final QuerySolution qs) {
		super(qs);
	}

	public Resource getVertex() {
		return qs.getResource(VAR_VERTEX);
	}

	@Override
	public Resource[] toArray() {
		return new Resource[] { getVertex() };
	}

}
