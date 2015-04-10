package hu.bme.mit.trainbenchmark.benchmark.emfincquery.driver;

import hu.bme.mit.trainbenchmark.emf.EMFDriver;

import java.util.Comparator;

import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;

import EMFIncQueryBenchmarkComparator.EMFIncQueryMatchComparator;

public class EMFIncQueryDriver<M extends BasePatternMatch> extends EMFDriver<M> {

	protected Comparator<M> matchComparator = (Comparator<M>) new EMFIncQueryMatchComparator();

	public EMFIncQueryDriver(final String modelPath) {
		super(modelPath);
	}

	// @Override
	// public void routeSensorRepair(final Collection<Object> matches) throws IOException {
	// for (final Object match : matches) {
	// final RouteSensorMatch rsm = (RouteSensorMatch) match;
	// rsm.getRoute().getDefinedBy().add(rsm.getSensor());
	// }
	// }
	//
	// @Override
	// public void semaphoreNeighborRepair(final Collection<Object> matches) throws IOException {
	// for (final Object match : matches) {
	// final SemaphoreNeighborMatch snm = (SemaphoreNeighborMatch) match;
	// snm.getRoute2().setEntry(snm.getSemaphore());
	// }
	// }
	//
	// @Override
	// public void switchSensorRepair(final Collection<Object> matches) throws IOException {
	// for (final Object match : matches) {
	// final SwitchSensorMatch ssm = (SwitchSensorMatch) match;
	// final Sensor sensor = RailwayFactory.eINSTANCE.createSensor();
	// ssm.getSw().setSensor(sensor);
	// }
	// }
	//
	// @Override
	// public void switchSetRepair(final Collection<Object> matches) throws IOException {
	// for (final Object match : matches) {
	// final SwitchSetMatch ssm = (SwitchSetMatch) match;
	// ssm.getSw().setCurrentPosition(ssm.getSwP().getPosition());
	// }
	// }

	@Override
	public Comparator<M> getMatchComparator() {
		return matchComparator;
	}

}
