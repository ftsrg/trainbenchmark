package hu.bme.mit.trainbenchmark.benchmark.emfapi.test;

import java.util.Collection;

import org.junit.Test;

import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfapi.operations.EmfApiModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperation;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelQuery;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelTransformation;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;
import hu.bme.mit.trainbenchmark.emf.EmfDriver;

public class EmfApiBasicTest {

	@Test
	public void test() throws Exception {
		final EmfDriver driver = new EmfDriver();
		final EmfApiModelOperationFactory<EmfDriver> factory = new EmfApiModelOperationFactory<>();
		
		driver.read("../models/railway-repair-1.xmi");
		
		final ModelOperation<? extends EmfMatch, EmfDriver> posLengthRepairOperation = factory.createOperation(RailwayOperation.POSLENGTH_REPAIR, driver);
		final ModelQuery<? extends EmfMatch, EmfDriver> query = posLengthRepairOperation.getQuery();
		final ModelTransformation<? extends EmfMatch, EmfDriver> transformation = posLengthRepairOperation.getTransformation();
		
//		final Collection<? extends EmfMatch> matches = query.check();
//		transformation.activate(matches);
//		System.out.println(matches);

	}
	
	public <TPatternMatch, TDriver extends Driver<?>> void execute(final ModelOperation<TPatternMatch, TDriver> operation) throws Exception {
		final Collection<? extends TPatternMatch> matches = operation.evaluateQuery();
		operation.performTransformation(matches);
	}
	
}
