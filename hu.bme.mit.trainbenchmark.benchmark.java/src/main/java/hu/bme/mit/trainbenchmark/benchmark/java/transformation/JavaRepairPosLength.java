package hu.bme.mit.trainbenchmark.benchmark.java.transformation;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.PosLengthRepairOperation;
import hu.bme.mit.trainbenchmark.benchmark.java.matches.JavaPosLengthMatch;

import java.util.Collection;

public class JavaRepairPosLength extends JavaTransformation<JavaPosLengthMatch> {

	@Override
	public void transform(final Collection<JavaPosLengthMatch> matches) {
		final PosLengthRepairOperation op = new PosLengthRepairOperation();

		for (final JavaPosLengthMatch match : matches) {
			final int newLength = op.op(match.getSegment().getLength());
			match.getSegment().setLength(newLength);
		}
	}
}
