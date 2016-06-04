package hu.bme.mit.trainbenchmark.benchmark.emfapi.test;

import org.junit.Test;

import hu.bme.mit.trainbenchmark.benchmark.emf.comparators.EmfMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.emf.driver.EmfDriver;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfapi.operations.EmfApiModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.executor.BenchmarkExecutor;

public class EmfApiBasicTest {

	@Test
	public void test() throws Exception {	
		final EmfDriver driver = new EmfDriver();
		final EmfApiModelOperationFactory<EmfDriver> factory = new EmfApiModelOperationFactory<>();
		final EmfMatchComparator comparator = EmfMatchComparator.create();
		
		BenchmarkExecutor<EmfDriver, EmfMatch> executor = new BenchmarkExecutor<>(driver, factory, comparator);
		executor.executeGeneric();
	}
	
}
