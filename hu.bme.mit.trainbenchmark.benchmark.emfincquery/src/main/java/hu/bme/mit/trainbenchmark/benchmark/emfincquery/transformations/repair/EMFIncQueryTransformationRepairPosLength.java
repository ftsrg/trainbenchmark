package hu.bme.mit.trainbenchmark.benchmark.emfincquery.transformations.repair;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.PosLengthRepairOperation;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.PosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.transformations.EMFIncQueryTransformation;
import hu.bme.mit.trainbenchmark.railway.Segment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;

public class EMFIncQueryTransformationRepairPosLength extends EMFIncQueryTransformation<BasePatternMatch> {

	@Override
	public void rhs(final Collection<BasePatternMatch> matches) throws IOException {
		final PosLengthRepairOperation op = new PosLengthRepairOperation();

		final Collection<Segment> segments = new ArrayList<>();
		for (final Object match : matches) {
			final PosLengthMatch plm = (PosLengthMatch) match;
			segments.add(plm.getSegment());
		}

		for (final Segment segment : segments) {
			final int newLength = op.op(segment.getLength());
			segment.setLength(newLength);
		}
	}

}
