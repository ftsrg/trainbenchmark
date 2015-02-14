package hu.bme.mit.trainbenchmark.benchmark.fourstore.benchmarkcases.xform;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.Transformation;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.TransformationBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.fourstore.benchmarkcases.PosLength;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.rdf.RDFConstants;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Multimap;

import eu.mondo.driver.graph.util.RDFUtil;

public class PosLengthXForm extends PosLength implements TransformationBenchmarkCase {

	@Override
	public void modify() throws IOException {
		final long nElemToModify = Util.calcModify(bmr);
		bmr.addModifyParams(nElemToModify);

		// start the modification
		final long start = System.nanoTime();

		// collect Segments Lengths
		final Multimap<Long, Object> oldSegmentLengths = driver.collectProperties(RDFConstants.BASE_PREFIX + ModelConstants.SEGMENT_LENGTH);

		// select random Segments
		final List<Long> segmentsToModify = Transformation.pickRandom(nElemToModify, invalids);

		final Map<String, Object> newSegmentLengths = new HashMap<>();
		for (final Long segment : segmentsToModify) {
			// client.updateProperty(segment, SEGMENT_LENGTH, -segmentLengths.get(segment) + 1);
			final Collection<Object> oldSegmentLengthValues = oldSegmentLengths.get(segment);
			for (final Object oldSegmentLength : oldSegmentLengthValues) {
				final Integer oldSegmentLengthInteger = (Integer) oldSegmentLength;

				final String segmentURI = RDFUtil.toURI(RDFConstants.BASE_PREFIX, segment);
				newSegmentLengths.put(segmentURI, -oldSegmentLengthInteger + 1);
			}
		}

		// edit
		final long startEdit = System.nanoTime();

		driver.updateProperties(newSegmentLengths, RDFConstants.BASE_PREFIX + ModelConstants.SEGMENT_LENGTH);

		final long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}

}
