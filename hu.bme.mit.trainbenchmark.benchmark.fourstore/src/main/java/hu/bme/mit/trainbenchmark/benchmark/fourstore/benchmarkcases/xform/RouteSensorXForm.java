package hu.bme.mit.trainbenchmark.benchmark.fourstore.benchmarkcases.xform;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.Transformation;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.TransformationBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.fourstore.benchmarkcases.RouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;
import hu.bme.mit.trainbenchmark.rdf.RDFConstants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import eu.mondo.driver.graph.util.RDFUtil;

public class RouteSensorXForm extends RouteSensor implements TransformationBenchmarkCase {

	@Override
	public void modify() throws IOException {
		final long nElemToModify = Util.calcModify(bc, bc.getModificationConstant(), bmr);
		bmr.addModifyParams(nElemToModify);

		// start the modification
		final long start = System.nanoTime();
		
		final List<Long> sensorsToRemove = Transformation.pickRandom(nElemToModify, invalids);
		final List<String> sensorURIsToRemove = new ArrayList<>();
		for (final Long id : sensorsToRemove) {
			sensorURIsToRemove.add(RDFUtil.toURI(RDFConstants.BASE_PREFIX, id));
		} 

		// edit
		final long startEdit = System.nanoTime();

		// this also deletes the incoming TrackElement_sensor edges
		driver.deleteVertices(sensorURIsToRemove);

		final long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);

	}

}
