package hu.bme.mit.trainbenchmark.benchmark.fourstore.benchmarkcases.xform;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.TransformationBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.fourstore.benchmarkcases.RouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

public class RouteSensorXForm extends RouteSensor implements TransformationBenchmarkCase {

	@Override
	public void modify() throws IOException {
		int nElemToModify = Util.calcModify(bc, bc.getModificationConstant(), bmr);
		bmr.addModifyParams(nElemToModify);
		 
		// start the modification
		long start = System.nanoTime();

		Random random = bmr.getRandom();
		int size = invalids.size();
		List<Long> sensorsToRemove = new ArrayList<>();

		for (int i = 0; i < nElemToModify; i++) {
			int rndTarget = random.nextInt(size);
			Long sensor = invalids.get(rndTarget);
			sensorsToRemove.add(sensor);
		}
		
		// edit
		long startEdit = System.nanoTime();
		// this also deletes the incoming TrackElement_sensor edges
		
		// partitioning
		List<List<Long>> partition = Lists.partition(sensorsToRemove, 500);
		for (List<Long> sensorsToRemoveChunk : partition) {
			driver.deleteVertices(sensorsToRemoveChunk);	
		}		

		long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);

	}
	
}
