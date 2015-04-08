package hu.bme.mit.trainbenchmark.benchmark.drools5.driver;

import hu.bme.mit.trainbenchmark.emf.EMFDriver;

import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;

import org.drools.runtime.rule.Row;

public class Drools5Driver extends EMFDriver<Row> {

	protected Comparator<Row> matchComparator = new RowComparator();

	public Drools5Driver(final String modelPath) {
		super(modelPath);
	}

	@Override
	public void posLengthRepair(final Collection<Row> matches) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void routeSensorRepair(final Collection<Row> matches) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void semaphoreNeighborRepair(final Collection<Row> matches) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void switchSensorRepair(final Collection<Row> matches) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void switchSetRepair(final Collection<Row> matches) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public Comparator<Row> getMatchComparator() {
		return matchComparator;
	}

}
