package hu.bme.mit.trainbenchmark.benchmark.fourstore.benchmarkcases.user;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.TransformationBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.fourstore.benchmarkcases.PosLength;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.rdf.RDFConstants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import eu.mondo.driver.graph.util.RDFUtil;

public class PosLengthUser extends PosLength implements TransformationBenchmarkCase {

	@Override
	public void modify() throws IOException {
		final int nElemToModify = Util.calcModify(bc, bc.getModificationConstant(), bmr);
		bmr.addModifyParams(nElemToModify);

		// start the modification
		final long start = System.nanoTime();

		// collect Segments
		final List<Long> segments = driver.collectVertices(RDFConstants.BASE_PREFIX + ModelConstants.SEGMENT);

		// select random Segments
		final Random random = bmr.getRandom();
		final int size = segments.size();
		final List<Long> segmentsToModify = new ArrayList<>();
		for (int i = 0; i < nElemToModify; i++) {
			final int rndTarget = random.nextInt(size);
			final Long segment = segments.get(rndTarget);
			segmentsToModify.add(segment);
		}

		final Map<String, Object> properties = new HashMap<>();
		for (final Long segment : segmentsToModify) {
			final String segmentURI = RDFUtil.toURI(RDFConstants.BASE_PREFIX, segment);
			properties.put(segmentURI, 0);
		}
		
		// edit
		final long startEdit = System.nanoTime();
		driver.updateProperties(properties, RDFConstants.BASE_PREFIX + ModelConstants.SEGMENT_LENGTH);
		
		final long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}

}
