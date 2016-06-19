package hu.bme.mit.trainbenchmark.benchmark.phases;

import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;

public class PhaseExecutor {

	public long execute(final Phase phase) throws Exception {
		phase.initialize();
		
		final Stopwatch stopwatch = Stopwatch.createStarted();
		phase.run();
		stopwatch.stop();
		
		phase.cleanup();
		
		return stopwatch.elapsed(TimeUnit.NANOSECONDS);
	}
	
}
