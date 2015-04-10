package hu.bme.mit.trainbenchmark.benchmark.emfincquery.transformation;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.PosLengthRepairOperation;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.PosLengthMatch;
import hu.bme.mit.trainbenchmark.emf.transformation.EMFTransformationAction;
import hu.bme.mit.trainbenchmark.railway.Segment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class EMFIncQueryPosLengthTransformation extends EMFTransformationAction {

	@Override
	public void transform(final Collection<Object> matches) throws IOException {
		final PosLengthRepairOperation op = new PosLengthRepairOperation();

		final Collection<Segment> segments = new ArrayList<>();
		for (final Object match : matches) {
			final PosLengthMatch plm = (PosLengthMatch) match;
			segments.add(plm.getSegment());
		}

		System.out.println(segments);
		for (final Segment segment : segments) {
			final int newLength = op.op(segment.getLength());
			segment.setLength(newLength);
		}
	}

}
