package hu.bme.mit.trainbenchmark.benchmark.phases;

import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkBundle;

/**
 * A phase consists of three subphases.
 *
 * <ul>
 * <li>Initialize</li>
 * <li>Run</li>
 * <li>Cleanup</li>
 * </ul>
 *
 */
public abstract class Phase {

	protected final BenchmarkBundle<?, ?, ?> bundle;

	public Phase(final BenchmarkBundle<?, ?, ?> bundle) {
		this.bundle = bundle;
	}

	/**
	 * Initializes the phase, e.g. loads the query definition strings from the disk. This subphase should be measured.
	 *
	 * @throws Exception
	 */
	public void initialize() throws Exception {

	}

	/**
	 * Runs the main activies of the phase, e.g. loads the model, evaluates the queries. This subphase should be measured.
	 *
	 * @throws Exception
	 */
	public abstract void run() throws Exception;

	/**
	 * Performs cleanup of the phase, e.g. frees managed resources. This subphase should not be measured.
	 *
	 * @throws Exception
	 */
	public void cleanup() throws Exception {

	}

}
