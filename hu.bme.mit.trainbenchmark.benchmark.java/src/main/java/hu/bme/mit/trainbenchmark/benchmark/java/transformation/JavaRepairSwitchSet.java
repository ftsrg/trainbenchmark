package hu.bme.mit.trainbenchmark.benchmark.java.transformation;

import hu.bme.mit.trainbenchmark.benchmark.java.matches.JavaSwitchSetMatch;

import java.util.Collection;

public class JavaRepairSwitchSet extends JavaTransformation<JavaSwitchSetMatch> {

	@Override
	public void transform(final Collection<JavaSwitchSetMatch> matches) {
		for (final JavaSwitchSetMatch match : matches) {
			match.getSw().setCurrentPosition(match.getSwP().getPosition());
		}
	}

}
