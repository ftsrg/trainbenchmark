package hu.bme.mit.trainbenchmark.benchmark.java.transformation.repair;

import hu.bme.mit.trainbenchmark.benchmark.java.matches.JavaSwitchSensorMatch;
import hu.bme.mit.trainbenchmark.railway.RailwayFactory;
import hu.bme.mit.trainbenchmark.railway.Sensor;

import java.util.Collection;

public class JavaTransformationRepairSwitchSensor extends JavaTransformation<JavaSwitchSensorMatch> {

	@Override
	public void rhs(final Collection<JavaSwitchSensorMatch> matches) {
		for (final JavaSwitchSensorMatch match : matches) {
			final Sensor sensor = RailwayFactory.eINSTANCE.createSensor();
			match.getSw().setSensor(sensor);
		}
	}

}
