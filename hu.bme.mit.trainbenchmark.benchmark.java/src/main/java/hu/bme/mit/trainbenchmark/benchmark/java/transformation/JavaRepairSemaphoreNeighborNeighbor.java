package hu.bme.mit.trainbenchmark.benchmark.java.transformation;

import hu.bme.mit.trainbenchmark.benchmark.java.matches.JavaSemaphoreNeighborMatch;

import java.util.Collection;

public class JavaRepairSemaphoreNeighborNeighbor extends JavaTransformation<JavaSemaphoreNeighborMatch> {

	@Override
	public void transform(final Collection<JavaSemaphoreNeighborMatch> matches) {
		for (final JavaSemaphoreNeighborMatch match : matches) {
			match.getRoute2().setEntry(match.getSemaphore());
		}
	}

}
