package hu.bme.mit.trainbenchmark.benchmark.sesame.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.checker.Checker;
import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameMatch;

import java.io.IOException;
import java.util.Collection;

public class SesameChecker extends Checker<SesameMatch> {

	protected final SesameDriver sesameDriver;

	public SesameChecker(final SesameDriver sesameDriver) {
		super();
		this.sesameDriver = sesameDriver;
	}

	@Override
	public Collection<SesameMatch> check() throws IOException {
		return sesameDriver.runQuery();
	}

}
