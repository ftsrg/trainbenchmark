package hu.bme.mit.trainbenchmark.benchmark.rdf.tests;

import hu.bme.mit.trainbenchmark.benchmark.test.TrainBenchmarkTest;
import hu.bme.mit.trainbenchmark.rdf.RdfFormat;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;

@RunWith(Parameterized.class)
public abstract class RdfTest extends TrainBenchmarkTest {

	@Parameters(name="inferencing={0}, format={1}")
	public static Iterable<? extends Object[]> data() {
		return Arrays.asList(new Object[][]{ //
			{ true,  RdfFormat.TURTLE   }, //
			{ false, RdfFormat.TURTLE   }, //
			{ false, RdfFormat.NTRIPLES }, //
		});
	}

	@Parameter(value = 0)
	public boolean inferencing;

	@Parameter(value = 1)
	public RdfFormat format;

}
