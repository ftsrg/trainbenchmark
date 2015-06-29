package hu.bme.mit.trainbenchmark.benchmark.orientdb.test;

import hu.bme.mit.trainbenchmark.benchmark.test.InjectTest;

public class OrientDbInjectTest extends InjectTest {

	public OrientDbInjectTest() {
		bi = new OrientDbBenchmarkInitializer();
	}
	
}
