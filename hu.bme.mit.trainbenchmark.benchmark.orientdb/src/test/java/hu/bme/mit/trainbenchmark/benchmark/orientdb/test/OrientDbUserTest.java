package hu.bme.mit.trainbenchmark.benchmark.orientdb.test;

import hu.bme.mit.trainbenchmark.benchmark.test.UserTest;

public class OrientDbUserTest extends UserTest {

	public OrientDbUserTest() {
		bi = new OrientDbBenchmarkInitializer();
	}
	
}
