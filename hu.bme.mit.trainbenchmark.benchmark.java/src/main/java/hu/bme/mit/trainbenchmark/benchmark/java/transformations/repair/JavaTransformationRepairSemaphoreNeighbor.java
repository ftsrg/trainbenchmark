package hu.bme.mit.trainbenchmark.benchmark.java.transformations.repair;

import hu.bme.mit.trainbenchmark.benchmark.java.matches.JavaSemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.java.transformations.JavaTransformation;

import java.util.Collection;

public class JavaTransformationRepairSemaphoreNeighbor extends JavaTransformation<JavaSemaphoreNeighborMatch> {

	@Override
	public void rhs(final Collection<JavaSemaphoreNeighborMatch> matches) {
		for (final JavaSemaphoreNeighborMatch match : matches) {
			match.getRoute2().setEntry(match.getSemaphore());
		}
	}

}
