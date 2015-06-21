package hu.bme.mit.trainbenchmark.benchmark.orientdb.transformations.repair;

import static hu.bme.mit.trainbenchmark.benchmark.orientdb.constants.OrientDbConstants.labelSensor;
import static hu.bme.mit.trainbenchmark.benchmark.orientdb.constants.OrientDbConstants.relationshipTypeSensor;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.driver.OrientDbDriver;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.matches.OrientDbSwitchSensorMatch;

import java.util.Collection;

import com.tinkerpop.blueprints.Vertex;

public class OrientDbTransformationRepairSwitchSensor extends OrientDbTransformationRepair<OrientDbSwitchSensorMatch>{

	public OrientDbTransformationRepairSwitchSensor(final OrientDbDriver orientDriver) {
		super(orientDriver);
	}
	
	@Override
	public void rhs(final Collection<OrientDbSwitchSensorMatch> matches) {
		for (OrientDbSwitchSensorMatch ssm : matches) {
			final Vertex sw = ssm.getSw();
			final Vertex sensor = orientDriver.getGraphDb().addVertex(null);
			sensor.setProperty("labels", labelSensor);
			sw.addEdge(relationshipTypeSensor, sensor);
		}
	}
	
}
