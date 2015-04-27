package hu.bme.mit.trainbenchmark.benchmark.java.transformations.repair;

import hu.bme.mit.trainbenchmark.benchmark.java.matches.JavaSwitchSetMatch;
import hu.bme.mit.trainbenchmark.benchmark.java.transformations.JavaTransformation;

import java.util.Collection;

public class JavaTransformationRepairSwitchSet extends JavaTransformation<JavaSwitchSetMatch> {

	@Override
	public void rhs(final Collection<JavaSwitchSetMatch> matches) {
		for (final JavaSwitchSetMatch match : matches) {
			match.getSw().setCurrentPosition(match.getSwP().getPosition());
		}
	}

}
