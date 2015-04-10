package hu.bme.mit.trainbenchmark.emf.transformation;

import hu.bme.mit.trainbenchmark.railway.Segment;

import java.util.Collection;

public class EMFTransformationPosLength extends EMFTransformationAction {

	@Override
	public void transform(final Collection<Object> segments) {
		for (final Object railwayElement : segments) {
			final Segment segment = (Segment) railwayElement;
			segment.setLength(0);
		}
	}

}
