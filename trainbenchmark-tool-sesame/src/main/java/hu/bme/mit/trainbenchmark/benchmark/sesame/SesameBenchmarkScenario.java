package hu.bme.mit.trainbenchmark.benchmark.sesame;

import hu.bme.mit.trainbenchmark.benchmark.phases.BenchmarkScenario;
import hu.bme.mit.trainbenchmark.benchmark.rdf.RdfBenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.sesame.comparators.SesameMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameMatch;
import hu.bme.mit.trainbenchmark.benchmark.sesame.operations.SesameModelOperationFactory;

public class SesameBenchmarkScenario extends BenchmarkScenario<SesameMatch, SesameDriver, RdfBenchmarkConfigWrapper> {

	public SesameBenchmarkScenario(final RdfBenchmarkConfigWrapper bcw) throws Exception {
		super(SesameDriver.create(bcw.isInferencing()), SesameModelOperationFactory.create(),
				SesameMatchComparator.create(), bcw);
	}

}
