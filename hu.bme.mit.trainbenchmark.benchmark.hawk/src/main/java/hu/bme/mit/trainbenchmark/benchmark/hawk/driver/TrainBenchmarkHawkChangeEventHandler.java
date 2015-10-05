package hu.bme.mit.trainbenchmark.benchmark.hawk.driver;

import uk.ac.york.mondo.integration.api.HawkAttributeRemovalEvent;
import uk.ac.york.mondo.integration.api.HawkAttributeUpdateEvent;
import uk.ac.york.mondo.integration.api.HawkModelElementAdditionEvent;
import uk.ac.york.mondo.integration.api.HawkModelElementRemovalEvent;
import uk.ac.york.mondo.integration.api.HawkReferenceAdditionEvent;
import uk.ac.york.mondo.integration.api.HawkReferenceRemovalEvent;
import uk.ac.york.mondo.integration.api.HawkSynchronizationEndEvent;
import uk.ac.york.mondo.integration.api.HawkSynchronizationStartEvent;
import uk.ac.york.mondo.integration.hawk.emf.IHawkChangeEventHandler;

public class TrainBenchmarkHawkChangeEventHandler implements IHawkChangeEventHandler {

	private volatile boolean finished = false;

	@Override
	public void handle(final HawkModelElementAdditionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handle(final HawkModelElementRemovalEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handle(final HawkReferenceRemovalEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handle(final HawkReferenceAdditionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handle(final HawkAttributeRemovalEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handle(final HawkAttributeUpdateEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handle(final HawkSynchronizationStartEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public synchronized void handle(final HawkSynchronizationEndEvent arg0) {
		finished = true;
		notifyAll();
	}

	public synchronized void await() throws InterruptedException {
		while (!finished) {
			wait(2000);
		}
	}

}
