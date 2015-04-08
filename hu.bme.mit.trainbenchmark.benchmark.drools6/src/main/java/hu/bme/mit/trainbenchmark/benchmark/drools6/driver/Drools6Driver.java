package hu.bme.mit.trainbenchmark.benchmark.drools6.driver;

import hu.bme.mit.trainbenchmark.emf.EMFDriver;

import java.io.IOException;
import java.util.Collection;

import org.kie.api.runtime.rule.Row;

public class Drools6Driver extends EMFDriver<Row> {

	public Drools6Driver(final String modelPath) {
		super(modelPath);
	}

	@Override
	public void posLengthRepair(final Collection matches) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void routeSensorRepair(final Collection matches) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void semaphoreNeighborRepair(final Collection matches) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void switchSensorRepair(final Collection matches) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void switchSetRepair(final Collection matches) throws IOException {
		// TODO Auto-generated method stub

	}

}
