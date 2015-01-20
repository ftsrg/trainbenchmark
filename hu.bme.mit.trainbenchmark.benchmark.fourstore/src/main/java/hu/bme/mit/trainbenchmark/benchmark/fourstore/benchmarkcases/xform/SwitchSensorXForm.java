package hu.bme.mit.trainbenchmark.benchmark.fourstore.benchmarkcases.xform;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.TransformationBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.fourstore.benchmarkcases.SwitchSensor;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

public class SwitchSensorXForm extends SwitchSensor implements TransformationBenchmarkCase {

	long newSensorId = 1000000000;

	@Override
	public void modify() throws IOException {
		final int nElemToModify = Util.calcModify(bc, bc.getModificationConstant(), bmr);
		bmr.addModifyParams(nElemToModify);

		// start the modification
		final long start = System.nanoTime();

		final Random random = bmr.getRandom();
		final int size = invalids.size();

		// edit
		final long startEdit = System.nanoTime();

		final Multimap<Long, Long> edges = ArrayListMultimap.create();
		for (int i = 0; i < nElemToModify; i++) {
			final int rndTarget = random.nextInt(size);
			final Long switchId = invalids.get(rndTarget);

			// create a new sensor connected to the switch
			// long sensor = client.insertVertex(SENSOR);
			// client.insertEdge(aSwitch, sensor, TRACKELEMENT_SENSOR);
			newSensorId++;
			edges.put(switchId, newSensorId);
		}

		// partitioning
		final List<Long> sourceVertices = new ArrayList<>(edges.keySet());
		final List<List<Long>> partition = Lists.partition(sourceVertices, 500);
		for (final List<Long> sourceVerticesChunk : partition) {

			final Multimap<Long, Long> edgesChunk = ArrayListMultimap.create();
			for (final Long sourceVertexId : sourceVerticesChunk) {
				final Collection<Long> targetVertexIds = edges.get(sourceVertexId);
				edgesChunk.putAll(sourceVertexId, targetVertexIds);
			}

			driver.insertEdgesWithVertex(edgesChunk, ModelConstants.TRACKELEMENT_SENSOR, ModelConstants.SENSOR);
		}

		final long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}
}
