package hu.bme.mit.trainbenchmark.benchmark.orientdb.transformations.repair;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.CURRENTPOSITION;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.POSITION;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.driver.OrientDbDriver;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.matches.OrientDbSwitchSetMatch;

import java.util.Collection;

import com.tinkerpop.blueprints.Vertex;

public class OrientDbTransformationRepairSwitchSet extends OrientDbTransformationRepair<OrientDbSwitchSetMatch>{

	public OrientDbTransformationRepairSwitchSet(final OrientDbDriver orientDriver) {
		super(orientDriver);
	}

	@Override
	public void rhs(final Collection<OrientDbSwitchSetMatch> matches) {
		for (OrientDbSwitchSetMatch ssm : matches) {
			final Vertex sw = ssm.getSw();
			final Vertex swP = ssm.getSwP();
			final String position = swP.getProperty(POSITION);
			sw.setProperty(CURRENTPOSITION, position);
		}
	}
}
