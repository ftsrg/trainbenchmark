package hu.bme.mit.trainbenchmark.benchmark.rdf.tests;

import java.util.Arrays;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import hu.bme.mit.trainbenchmark.benchmark.test.TrainBenchmarkTest;

@RunWith(Parameterized.class)
public abstract class RdfTest extends TrainBenchmarkTest {

	@Parameters(name="inferencing={0}")
	public static Iterable<? extends Object> data() {
		return Arrays.asList(false);
	}

	@Parameter
	public boolean inferencing;

}
