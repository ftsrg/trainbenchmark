package hu.bme.mit.trainbenchmark.benchmark.hawk.driver;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import uk.ac.york.mondo.integration.api.HawkAttributeRemovalEvent;
import uk.ac.york.mondo.integration.api.HawkAttributeUpdateEvent;
import uk.ac.york.mondo.integration.api.HawkFileAdditionEvent;
import uk.ac.york.mondo.integration.api.HawkFileRemovalEvent;
import uk.ac.york.mondo.integration.api.HawkModelElementAdditionEvent;
import uk.ac.york.mondo.integration.api.HawkModelElementRemovalEvent;
import uk.ac.york.mondo.integration.api.HawkReferenceAdditionEvent;
import uk.ac.york.mondo.integration.api.HawkReferenceRemovalEvent;
import uk.ac.york.mondo.integration.api.HawkSynchronizationEndEvent;
import uk.ac.york.mondo.integration.api.HawkSynchronizationStartEvent;
import uk.ac.york.mondo.integration.hawk.emf.IHawkChangeEventHandler;

// TODO extends HCEA
public class TrainBenchmarkHawkChangeEventHandler implements IHawkChangeEventHandler {

	private CompletableFuture<Boolean> syncEnd;

	public void reset() {
		syncEnd = new CompletableFuture<Boolean>();
	}
	
	@Override
	public synchronized void handle(final HawkSynchronizationEndEvent arg0) {
		syncEnd.complete(true);
	}

	public Future<Boolean> getSyncEnd() {
		return syncEnd;
	}

	// there methods are NOPs

	@Override
	public void handle(final HawkModelElementAdditionEvent arg0) {
	}

	@Override
	public void handle(final HawkModelElementRemovalEvent arg0) {
	}

	@Override
	public void handle(final HawkReferenceRemovalEvent arg0) {
	}

	@Override
	public void handle(final HawkReferenceAdditionEvent arg0) {
	}

	@Override
	public void handle(final HawkAttributeRemovalEvent arg0) {
	}

	@Override
	public void handle(final HawkAttributeUpdateEvent arg0) {
	}

	@Override
	public void handle(final HawkSynchronizationStartEvent arg0) {
	}

	@Override
	public void handle(final HawkFileAdditionEvent arg0) {
	}

	@Override
	public void handle(final HawkFileRemovalEvent arg0) {
	}

}
