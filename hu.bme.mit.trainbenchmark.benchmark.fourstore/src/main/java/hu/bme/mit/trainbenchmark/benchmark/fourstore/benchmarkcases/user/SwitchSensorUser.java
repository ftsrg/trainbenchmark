package hu.bme.mit.trainbenchmark.benchmark.fourstore.benchmarkcases.user;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.Transformation;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.TransformationBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.fourstore.benchmarkcases.SwitchSensor;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.rdf.RDFConstants;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import eu.mondo.driver.graph.util.RDFUtil;

public class SwitchSensorUser extends SwitchSensor implements TransformationBenchmarkCase {

	@Override
	public void modify() throws IOException {
		final long nElemToModify = Util.calcModify(bc, bc.getModificationConstant(), bmr);
		bmr.addModifyParams(nElemToModify);

		// start the modification
		final long start = System.nanoTime();

		// collect Switches
		final List<Long> switches = driver.collectVertices(RDFConstants.BASE_PREFIX + ModelConstants.SWITCH);
		
		// select random Switches
		final List<Long> itemsToModify = Transformation.pickRandom(nElemToModify, switches);

		final Multimap<Long, Long> trackElementSensors = driver.collectEdges(RDFConstants.BASE_PREFIX + ModelConstants.TRACKELEMENT_SENSOR);
		final Multimap<String, String> edgesToRemove = ArrayListMultimap.create();

		for (final Long aSwitch : itemsToModify) {
			final Collection<Long> sensors = trackElementSensors.get(aSwitch);
			for (final Long sensor : sensors) {
				final String switchURI = RDFUtil.toURI(RDFConstants.BASE_PREFIX, aSwitch);
				final String sensorURI = RDFUtil.toURI(RDFConstants.BASE_PREFIX, sensor);
				
				edgesToRemove.put(switchURI, sensorURI);
			}
		}
		
		// edit
		final long startEdit = System.nanoTime();
		driver.deleteEdges(edgesToRemove, RDFConstants.BASE_PREFIX + ModelConstants.TRACKELEMENT_SENSOR);
		
		final long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);

	}

}
