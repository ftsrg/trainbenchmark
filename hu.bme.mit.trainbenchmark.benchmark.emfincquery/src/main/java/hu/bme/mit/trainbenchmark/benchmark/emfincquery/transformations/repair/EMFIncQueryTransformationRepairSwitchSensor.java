package hu.bme.mit.trainbenchmark.benchmark.emfincquery.transformations.repair;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SwitchSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.transformations.EMFIncQueryTransformation;
import hu.bme.mit.trainbenchmark.railway.RailwayFactory;
import hu.bme.mit.trainbenchmark.railway.Sensor;

import java.io.IOException;
import java.util.Collection;

import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;

public class EMFIncQueryTransformationRepairSwitchSensor extends EMFIncQueryTransformation<BasePatternMatch> {

	@Override
	public void rhs(final Collection<BasePatternMatch> matches) throws IOException {
		for (final Object match : matches) {
			final SwitchSensorMatch ssm = (SwitchSensorMatch) match;
			final Sensor sensor = RailwayFactory.eINSTANCE.createSensor();
			ssm.getSw().setSensor(sensor);
		}
	}

}
