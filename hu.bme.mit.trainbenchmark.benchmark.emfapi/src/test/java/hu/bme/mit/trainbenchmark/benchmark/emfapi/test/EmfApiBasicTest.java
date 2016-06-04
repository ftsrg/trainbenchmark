package hu.bme.mit.trainbenchmark.benchmark.emfapi.test;

import java.util.Comparator;
import java.util.Random;

import org.junit.Test;

import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.emfapi.operations.EmfApiModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperation;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;
import hu.bme.mit.trainbenchmark.emf.EmfDriver;

public class EmfApiBasicTest {

	@Test
	public void test() throws Exception {
		final EmfDriver driver = new EmfDriver();
		final EmfApiModelOperationFactory<EmfDriver> factory = new EmfApiModelOperationFactory<>();
		
		driver.read("../models/railway-repair-1.xmi");
		
		final Random random = new Random(0);

		final ModelOperation<? extends EmfMatch, EmfDriver> posLengthRepairOperation = factory.createOperation(RailwayOperation.POSLENGTH_REPAIR, driver);
		final Comparator<EmfMatch> emfMatchComparator = new EmfMatchComparator();
        
        final QueryShuffleTransformation<? extends EmfMatch, EmfDriver> qst = QueryShuffleTransformation.of(posLengthRepairOperation, emfMatchComparator, random);
        qst.evaluateQuery();
        qst.shuffle(10);
        qst.transform();
	}
	
}
