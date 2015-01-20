package hu.bme.mit.trainbenchmark.benchmark.fourstore.benchmarkcases.xform;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.TransformationBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.fourstore.benchmarkcases.SignalNeighbor;
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

public class SignalNeighborXForm extends SignalNeighbor implements TransformationBenchmarkCase {

	@Override
	public void modify() throws IOException {
		int nElemToModify = Util.calcModify(bc, bc.getModificationConstant(), bmr);
		bmr.addModifyParams(nElemToModify);

		// start the modification
		final long start = System.nanoTime();

		//
		final Multimap<Long, Long> routeExits = driver.collectEdges(ModelConstants.ROUTE_EXIT);

		final Random random = bmr.getRandom();
		final Multimap<Long, Long> edgesToRemove = ArrayListMultimap.create();

		final int size = invalids.size();
		if (size < nElemToModify) {
			nElemToModify = size;
		}

		for (int i = 0; i < nElemToModify; i++) {
			final int rndTarget = random.nextInt(size);
			final Long route = invalids.get(rndTarget);

			final Collection<Long> edges = routeExits.get(route);
			// TODO just one
			edgesToRemove.putAll(route, edges);
		}

		// edit
		final long startEdit = System.nanoTime();
	
		// partitioning
		final ArrayList<Long> sourceVertices = new ArrayList<>(edgesToRemove.keySet());		
		final List<List<Long>> partition = Lists.partition(sourceVertices, 500);
		for (final List<Long> sourceVerticesChunk : partition) {
			
			final Multimap<Long, Long> edgesToRemoveChunk = ArrayListMultimap.create();
			for (final Long sourceVertexId : sourceVerticesChunk) {
				final Collection<Long> targetVertexIds = edgesToRemove.get(sourceVertexId);
				edgesToRemoveChunk.putAll(sourceVertexId, targetVertexIds);
			}
						
			driver.deleteEdges(edgesToRemoveChunk, ModelConstants.ROUTE_EXIT);
		}
		

		final long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}

}
