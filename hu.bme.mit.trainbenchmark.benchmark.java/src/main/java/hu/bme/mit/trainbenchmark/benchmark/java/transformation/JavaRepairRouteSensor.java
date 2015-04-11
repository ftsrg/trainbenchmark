package hu.bme.mit.trainbenchmark.benchmark.java.transformation;

import hu.bme.mit.trainbenchmark.benchmark.java.matches.JavaRouteSensorMatch;

import java.util.Collection;

public class JavaRepairRouteSensor extends JavaTransformation<JavaRouteSensorMatch> {

	@Override
	public void transform(final Collection<JavaRouteSensorMatch> matches) {
		for (final JavaRouteSensorMatch match : matches) {
			match.getRoute().getDefinedBy().add(match.getSensor());
		}
	}
}
