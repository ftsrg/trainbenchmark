package hu.bme.mit.trainbenchmark.benchmark.phases;

public abstract class Phase {

	/**
	 * Initializes the phase, e.g. loads the query definition strings from the disk. This subphase should be measured.
	 */
	public void initialize() {
		
	}
	
	/**
	 * Runs the main activies of the phase, e.g. loads the model, evaluates the queries. This subphase should be measured.
	 */
	public abstract void run();
	
	/**
	 * Performs cleanup of the phase, e.g. frees managed resources. This subphase should not be measured.
	 */
	public void cleanup() {
		
	}
	
	
	
}
