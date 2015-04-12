package hu.bme.mit.trainbenchmark.benchmark.java.transformation.repair;

import hu.bme.mit.trainbenchmark.benchmark.java.matches.JavaRouteSensorMatch;

import java.util.Collection;

public class JavaTransformationRepairRouteSensor extends JavaTransformation<JavaRouteSensorMatch> {

	@Override
	public void rhs(final Collection<JavaRouteSensorMatch> matches) {
		for (final JavaRouteSensorMatch match : matches) {
			match.getRoute().getDefinedBy().add(match.getSensor());
		}
	}
}
