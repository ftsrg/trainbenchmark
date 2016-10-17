package hu.bme.mit.trainbenchmark.benchmark.epsilon.queries;

import java.util.Collection;

import hu.bme.mit.trainbenchmark.benchmark.emf.driver.EmfDriver;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfMatch;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelQuery;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class EpsilonQuery<TMatch extends EmfMatch> extends ModelQuery<TMatch, EmfDriver> {

	public EpsilonQuery(RailwayQuery query, EmfDriver driver) {
		super(query, driver);
	}

	@Override
	public Collection<TMatch> evaluate() throws Exception {
		return null;
	}

}
