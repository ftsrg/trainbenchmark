package hu.bme.mit.trainbenchmark.benchmark.fourstore.benchmarkcases.xform;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.TransformationBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.fourstore.benchmarkcases.RouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;
import hu.bme.mit.trainbenchmark.rdf.RDFConstants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import eu.mondo.driver.graph.util.RDFUtil;

public class RouteSensorXForm extends RouteSensor implements TransformationBenchmarkCase {

	@Override
	public void modify() throws IOException {
		int nElemToModify = Util.calcModify(bc, bc.getModificationConstant(), bmr);
		bmr.addModifyParams(nElemToModify);

		// start the modification
		long start = System.nanoTime();

		Random random = bmr.getRandom();
		int size = invalids.size();
		List<String> sensorsToRemove = new ArrayList<>();

		for (int i = 0; i < nElemToModify; i++) {
			int rndTarget = random.nextInt(size);
			Long sensor = invalids.get(rndTarget);
			String sensorURI = RDFUtil.toURI(RDFConstants.BASE_PREFIX, sensor);
			sensorsToRemove.add(sensorURI);
		}

		// edit
		long startEdit = System.nanoTime();
		// this also deletes the incoming TrackElement_sensor edges

		// partitioning
		driver.deleteVertices(sensorsToRemove);

		long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);

	}

}
