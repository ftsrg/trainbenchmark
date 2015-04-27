package hu.bme.mit.trainbenchmark.benchmark.emfincquery.transformations.repair;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.transformations.EMFIncQueryTransformation;

import java.io.IOException;
import java.util.Collection;

import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;

public class EMFIncQueryTransformationRepairSemaphoreNeighbor extends EMFIncQueryTransformation<BasePatternMatch> {

	@Override
	public void rhs(final Collection<BasePatternMatch> matches) throws IOException {
		for (final Object match : matches) {
			final SemaphoreNeighborMatch snm = (SemaphoreNeighborMatch) match;
			snm.getRoute2().setEntry(snm.getSemaphore());
		}
	}

}
