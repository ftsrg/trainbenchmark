package hu.bme.mit.trainbenchmark.benchmark.java.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.PosLengthRepairOperation;
import hu.bme.mit.trainbenchmark.benchmark.java.matches.JavaMatch;
import hu.bme.mit.trainbenchmark.benchmark.java.matches.JavaPosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.java.matches.JavaRouteSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.java.matches.JavaSemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.java.matches.JavaSwitchSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.java.matches.JavaSwitchSetMatch;
import hu.bme.mit.trainbenchmark.emf.EMFDriver;
import hu.bme.mit.trainbenchmark.railway.RailwayFactory;
import hu.bme.mit.trainbenchmark.railway.Sensor;

import java.io.IOException;
import java.util.Collection;

public class JavaDriver<M extends JavaMatch> extends EMFDriver<M> {

	public JavaDriver(final String modelPath) {
		super(modelPath);
	}

	@Override
	public void posLengthRepair(final Collection<M> matches) throws IOException {
		final PosLengthRepairOperation op = new PosLengthRepairOperation();

		for (final M match : matches) {
			final JavaPosLengthMatch plm = (JavaPosLengthMatch) match;
			final int newLength = op.op(plm.getSegment().getLength());
			plm.getSegment().setLength(newLength);
		}

	}

	@Override
	public void routeSensorRepair(final Collection<M> matches) throws IOException {
		for (final M match : matches) {
			final JavaRouteSensorMatch rsm = (JavaRouteSensorMatch) match;
			rsm.getRoute().getDefinedBy().add(rsm.getSensor());
		}
	}

	@Override
	public void semaphoreNeighborRepair(final Collection<M> matches) throws IOException {
		for (final M match : matches) {
			final JavaSemaphoreNeighborMatch snm = (JavaSemaphoreNeighborMatch) match;
			snm.getRoute2().setEntry(snm.getSemaphore());
		}
	}

	@Override
	public void switchSensorRepair(final Collection<M> matches) throws IOException {
		for (final M match : matches) {
			final JavaSwitchSensorMatch ssrm = (JavaSwitchSensorMatch) match;
			final Sensor sensor = RailwayFactory.eINSTANCE.createSensor();
			ssrm.getSw().setSensor(sensor);
		}
	}

	@Override
	public void switchSetRepair(final Collection<M> matches) throws IOException {
		for (final M match : matches) {
			final JavaSwitchSetMatch sstm = (JavaSwitchSetMatch) match;
			sstm.getSw().setCurrentPosition(sstm.getSwitchPosition().getPosition());
		}
	}

}
