package hu.bme.mit.trainbenchmark.benchmark.emfapi.test;

import java.util.Comparator;
import java.util.Random;

import org.junit.Test;

import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.emf.comparators.EmfMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.emf.driver.EmfDriver;
import hu.bme.mit.trainbenchmark.benchmark.emfapi.operations.EmfApiModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperation;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperationFactory;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;

public class EmfApiBasicTest {

	@Test
	public void test() throws Exception {	
		final EmfDriver driver = new EmfDriver();
		final EmfApiModelOperationFactory<EmfDriver> factory = new EmfApiModelOperationFactory<>();
		final EmfMatchComparator comparator = EmfMatchComparator.create();
		executeGeneric(driver, factory, comparator);
	}
	
	
	public <TDriver extends Driver<?>, TPatternMatch> void executeGeneric(final TDriver driver, final ModelOperationFactory<TPatternMatch, TDriver> factory, Comparator<TPatternMatch> comparator) throws Exception {
		driver.read("../models/railway-repair-1.xmi");
		
		final Random random = new Random(0);
		final ModelOperation<? extends TPatternMatch, TDriver> operation = factory.createOperation(RailwayOperation.POSLENGTH_REPAIR, driver);
		
		final QueryShuffleTransformation<? extends TPatternMatch, TDriver> qst = QueryShuffleTransformation.of(operation, comparator, random);
        qst.evaluateQuery();
        qst.shuffle(10);
        qst.transform();
	}
	
}
