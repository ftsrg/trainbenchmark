package hu.bme.mit.trainbenchmark.benchmark.fourstore.benchmarkcases.user;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.TransformationBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.fourstore.benchmarkcases.RouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.rdf.RDFConstants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import eu.mondo.driver.graph.util.RDFUtil;

public class RouteSensorUser extends RouteSensor implements TransformationBenchmarkCase {

	@Override
	public void modify() throws IOException {
		final int nElemToModify = Util.calcModify(bc, bc.getModificationConstant(), bmr);
		bmr.addModifyParams(nElemToModify);

		// start the modification
		final long start = System.nanoTime();

		final List<Long> routes = driver.collectVertices(RDFConstants.BASE_PREFIX + ModelConstants.ROUTE);
		final Multimap<Long, Long> routesAndEdges = driver.collectEdges(RDFConstants.BASE_PREFIX + ModelConstants.ROUTE_ROUTEDEFINITION);

		// randomly choosing some Routes to modify
		final List<Long> routesToModify = new ArrayList<>();

		final Random random = bmr.getRandom();

		// choose nElemToModify elements to modify
		for (int i = 0; i < nElemToModify; i++) {
			final int rndTargetPosition = random.nextInt(routes.size());
			final Long routeId = routes.get(rndTargetPosition);
			routesToModify.add(routeId);
		}

		final Multimap<String, String> edgesToRemove = ArrayListMultimap.create();
		for (final Long route : routesToModify) {
			final List<Long> sensors = new ArrayList<>(routesAndEdges.get(route));
			if (!sensors.isEmpty()) {
				final Long sensor = sensors.get(0);
				
				String routeURI = RDFUtil.toURI(RDFConstants.BASE_PREFIX, route);
				String sensorURI = RDFUtil.toURI(RDFConstants.BASE_PREFIX, sensor);
				
				edgesToRemove.put(routeURI, sensorURI);
			}
		}

		// edit
		final long startEdit = System.nanoTime();
		driver.deleteEdges(edgesToRemove, RDFConstants.BASE_PREFIX + ModelConstants.ROUTE_ROUTEDEFINITION);

		final long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}

}
