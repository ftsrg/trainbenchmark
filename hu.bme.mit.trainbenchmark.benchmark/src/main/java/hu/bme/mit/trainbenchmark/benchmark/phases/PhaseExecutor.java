package hu.bme.mit.trainbenchmark.benchmark.phases;

import com.google.common.base.Stopwatch;

public class PhaseExecutor {

	public void execute(final Phase phase) {
		phase.initialize();
		
		final Stopwatch stopwatch = Stopwatch.createStarted();
		phase.run();
		stopwatch.stop();
		
		phase.cleanup();
	}
	
	
}
