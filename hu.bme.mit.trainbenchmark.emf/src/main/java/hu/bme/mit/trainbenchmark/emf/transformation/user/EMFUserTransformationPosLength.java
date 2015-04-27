package hu.bme.mit.trainbenchmark.emf.transformation.user;

import hu.bme.mit.trainbenchmark.emf.EMFDriver;
import hu.bme.mit.trainbenchmark.railway.RailwayElement;
import hu.bme.mit.trainbenchmark.railway.Segment;

import java.util.Collection;

public class EMFUserTransformationPosLength extends EMFUserTransformation {

	public EMFUserTransformationPosLength(final EMFDriver driver) {
		super(driver);
	}

	@Override
	public void rhs(final Collection<RailwayElement> segments) {
		for (final Object railwayElement : segments) {
			final Segment segment = (Segment) railwayElement;
			segment.setLength(0);
		}
	}

}
