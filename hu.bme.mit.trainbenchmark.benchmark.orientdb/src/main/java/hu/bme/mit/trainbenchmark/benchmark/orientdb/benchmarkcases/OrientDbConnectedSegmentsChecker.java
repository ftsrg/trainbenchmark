package hu.bme.mit.trainbenchmark.benchmark.orientdb.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.orientdb.driver.OrientDbDriver;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.matches.OrientDbPosLengthMatch;

import java.util.Collection;

public class OrientDbConnectedSegmentsChecker extends OrientDbChecker<OrientDbPosLengthMatch> {

	public OrientDbConnectedSegmentsChecker(final OrientDbDriver orientDriver) {
		super(orientDriver);
	}

	@Override
	public Collection<OrientDbPosLengthMatch> check() {
		return null;
	}
	
}
