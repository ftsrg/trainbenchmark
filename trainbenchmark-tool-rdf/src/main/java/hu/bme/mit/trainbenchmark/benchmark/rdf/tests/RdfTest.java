package hu.bme.mit.trainbenchmark.benchmark.rdf.tests;

import hu.bme.mit.trainbenchmark.benchmark.test.TrainBenchmarkTest;
import hu.bme.mit.trainbenchmark.rdf.RdfFormat;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;

@RunWith(Parameterized.class)
public abstract class RdfTest extends TrainBenchmarkTest {

	@Parameter(value = 0)
	public boolean inferencing;

	@Parameter(value = 1)
	public RdfFormat format;

}
