package hu.bme.mit.trainbenchmark.emf.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.emf.matches.EMFMatch;
import hu.bme.mit.trainbenchmark.emf.matches.EMFMatchComparator;
import hu.bme.mit.trainbenchmark.railway.RailwayElement;

import java.util.Comparator;

public class EMFBenchmarkCase extends AbstractBenchmarkCase<EMFMatch, RailwayElement> {

	@Override
	protected Comparator<?> getMatchComparator() {
		return new EMFMatchComparator();
	}

}
