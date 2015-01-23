package hu.bme.mit.trainbenchmark.benchmark.fourstore.benchmarkcases.user;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.TransformationBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.fourstore.benchmarkcases.SignalNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.rdf.RDFConstants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import eu.mondo.driver.graph.util.RDFUtil;

public class SignalNeighborUser extends SignalNeighbor implements TransformationBenchmarkCase {

	@Override
	public void modify() throws IOException {
		int nElemToModify = Util.calcModify(bc, bc.getModificationConstant(), bmr);
		bmr.addModifyParams(nElemToModify);

		// start the modification
		final long start = System.nanoTime();

		final List<Long> routes = driver.collectVertices(RDFConstants.BASE_PREFIX + ModelConstants.ROUTE);
		final Multimap<Long, Long> routesEntries = driver.collectEdges(RDFConstants.BASE_PREFIX + ModelConstants.ROUTE_ENTRY);

		// select Random Routes 
		final Random random = bmr.getRandom();
		final Multimap<String, String> edgesToRemove = ArrayListMultimap.create();

		final int size = routes.size();
		if (size < nElemToModify) {
			nElemToModify = size;
		}
		
		final List<Long> itemsToModify = new ArrayList<>();

		for (int i = 0; i < nElemToModify; i++) {
			final int rndTarget = random.nextInt(size);
			final Long route = routes.get(rndTarget);
			itemsToModify.add(route);
			
			final String routeURI = RDFUtil.toURI(RDFConstants.BASE_PREFIX, route);
			
			final Collection<Long> signals = routesEntries.get(route);
			for (final Long signal : signals) {
				final String signalURI = RDFUtil.toURI(RDFConstants.BASE_PREFIX, signal);
				edgesToRemove.put(routeURI, signalURI);
			}
		}

		// edit
		final long startEdit = System.nanoTime();
		driver.deleteEdges(edgesToRemove, RDFConstants.BASE_PREFIX + ModelConstants.ROUTE_ENTRY);

		final long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}

}
