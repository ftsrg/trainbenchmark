package hu.bme.mit.trainbenchmark.benchmark.fourstore.benchmarkcases.xform;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.TransformationBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.fourstore.benchmarkcases.SignalNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.rdf.RDFConstants;

import java.io.IOException;
import java.util.Collection;
import java.util.Random;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import eu.mondo.driver.graph.util.RDFUtil;

public class SignalNeighborXForm extends SignalNeighbor implements TransformationBenchmarkCase {

	@Override
	public void modify() throws IOException {
		int nElemToModify = Util.calcModify(bc, bc.getModificationConstant(), bmr);
		bmr.addModifyParams(nElemToModify);

		// start the modification
		final long start = System.nanoTime();

		//
		final Multimap<Long, Long> routeExits = driver.collectEdges(RDFConstants.BASE_PREFIX + ModelConstants.ROUTE_EXIT);

		final Random random = bmr.getRandom();
		final Multimap<String, String> edgesToRemove = ArrayListMultimap.create();

		final int size = invalids.size();
		if (size < nElemToModify) {
			nElemToModify = size;
		}

		for (int i = 0; i < nElemToModify; i++) {
			final int rndTarget = random.nextInt(size);
			final Long route = invalids.get(rndTarget);
			final String routeURI = RDFUtil.toURI(RDFConstants.BASE_PREFIX, route);
			final Collection<Long> exits = routeExits.get(route);

			// TODO just one
			for (Long exit : exits) {
				final String exitURI = RDFUtil.toURI(RDFConstants.BASE_PREFIX, exit);
				edgesToRemove.put(routeURI, exitURI);
			}
		}

		// edit
		final long startEdit = System.nanoTime();

		// partitioning
		driver.deleteEdges(edgesToRemove, RDFConstants.BASE_PREFIX + ModelConstants.ROUTE_EXIT);

		final long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}

}
