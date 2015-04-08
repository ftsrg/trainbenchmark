package hu.bme.mit.trainbenchmark.benchmark.emfincquery.driver;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.PosLengthRepairOperation;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.PosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.RouteSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SwitchSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SwitchSetMatch;
import hu.bme.mit.trainbenchmark.emf.EMFDriver;
import hu.bme.mit.trainbenchmark.railway.RailwayFactory;
import hu.bme.mit.trainbenchmark.railway.Segment;
import hu.bme.mit.trainbenchmark.railway.Sensor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.incquery.runtime.api.IPatternMatch;

public class EMFIncQueryDriver<M extends IPatternMatch> extends EMFDriver<M> {

	public EMFIncQueryDriver(final String modelPath) {
		super(modelPath);
	}

	@Override
	public void posLengthRepair(final Collection<M> matches) throws IOException {
		final PosLengthRepairOperation op = new PosLengthRepairOperation();

		final Collection<Segment> segments = new ArrayList<>();
		for (final M match : matches) {
			final PosLengthMatch plm = (PosLengthMatch) match;
			segments.add(plm.getSegment());
		}

		System.out.println(segments);
		for (final Segment segment : segments) {
			final int newLength = op.op(segment.getLength());
			segment.setLength(newLength);
		}
	}

	@Override
	public void routeSensorRepair(final Collection<M> matches) throws IOException {
		for (final M match : matches) {
			final RouteSensorMatch rsm = (RouteSensorMatch) match;
			rsm.getRoute().getDefinedBy().add(rsm.getSensor());
		}
	}

	@Override
	public void semaphoreNeighborRepair(final Collection<M> matches) throws IOException {
		for (final M match : matches) {
			final SemaphoreNeighborMatch snm = (SemaphoreNeighborMatch) match;
			snm.getRoute2().setEntry(snm.getSemaphore());
		}
	}

	@Override
	public void switchSensorRepair(final Collection<M> matches) throws IOException {
		for (final M match : matches) {
			final SwitchSensorMatch ssm = (SwitchSensorMatch) match;
			final Sensor sensor = RailwayFactory.eINSTANCE.createSensor();
			ssm.getSw().setSensor(sensor);
		}
	}

	@Override
	public void switchSetRepair(final Collection<M> matches) throws IOException {
		for (final M match : matches) {
			final SwitchSetMatch ssm = (SwitchSetMatch) match;
			ssm.getSw().setCurrentPosition(ssm.getSwP().getPosition());
		}
	}

}
