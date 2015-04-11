package hu.bme.mit.trainbenchmark.benchmark.java.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.java.matches.JavaMatch;
import hu.bme.mit.trainbenchmark.benchmark.java.matches.JavaMatchComparator;
import hu.bme.mit.trainbenchmark.emf.EMFDriver;

import java.util.Comparator;

public abstract class JavaDriver<M extends JavaMatch> extends EMFDriver<M> {

	protected Comparator<M> matchComparator = (Comparator<M>) new JavaMatchComparator();

	@Override
	public Comparator<M> getMatchComparator() {
		return matchComparator;
	}

}
