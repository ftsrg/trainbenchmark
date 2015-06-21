package hu.bme.mit.trainbenchmark.benchmark.orientdb.transformations.repair;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.LENGTH;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.driver.OrientDbDriver;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.matches.OrientDbPosLengthMatch;

import java.util.Collection;

import com.tinkerpop.blueprints.Vertex;

public class OrientDbTransformationRepairPosLength extends OrientDbTransformationRepair<OrientDbPosLengthMatch> {

	public OrientDbTransformationRepairPosLength(final OrientDbDriver orientDriver) {
		super(orientDriver);
	}

	@Override
	public void rhs(final Collection<OrientDbPosLengthMatch> matches) {
		for (final OrientDbPosLengthMatch plm : matches) {
			final Vertex segment = plm.getSegment();
			final Integer length = (Integer) segment.getProperty(LENGTH);
			segment.setProperty(LENGTH, -length + 1);
		}
	}
	
}
