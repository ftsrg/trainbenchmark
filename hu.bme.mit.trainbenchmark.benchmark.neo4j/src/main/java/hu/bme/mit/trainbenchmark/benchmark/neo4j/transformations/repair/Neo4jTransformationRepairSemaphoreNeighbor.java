package hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.repair;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.LENGTH;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jPosLengthMatch;

import java.util.Collection;

import org.neo4j.graphdb.Node;

public class Neo4jTransformationRepairSemaphoreNeighbor extends Neo4jTransformationRepair<Neo4jPosLengthMatch> {

	public Neo4jTransformationRepairSemaphoreNeighbor() {
		super();
	}

	@Override
	public void rhs(final Collection<Neo4jPosLengthMatch> matches) {
		for (final Neo4jPosLengthMatch plm : matches) {
			final Node segment = plm.getSegment();
			final Integer length = (Integer) segment.getProperty(LENGTH);
			segment.setProperty(LENGTH, -length + 1);
		}
	}

}
