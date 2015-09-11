package hu.bme.mit.trainbenchmark.benchmark.publisher;

import java.io.IOException;

import eu.mondo.sam.core.publishers.CommandLinePublisher;
import eu.mondo.sam.core.results.BenchmarkResult;
import eu.mondo.sam.core.results.CaseDescriptor;

public class TrainBenchmarkCommandLinePublisher extends CommandLinePublisher {

	@Override
	public void publish(BenchmarkResult benchmarkResult) throws IOException {
		CaseDescriptor desc = benchmarkResult.getCaseDescriptor();
		if (desc instanceof TrainBenchmarkCaseDescriptor) {
			TrainBenchmarkCaseDescriptor tbdesc = (TrainBenchmarkCaseDescriptor) desc;
			System.out.println("Running benchmark: " + tbdesc.getModel() + "-"
					+ tbdesc.getSubmodel() + ", " + desc.getScenario() + " scenario, "
					+ desc.getTool() + ", " + desc.getCaseName() + ", " + desc.getSize());
		} else {
			super.publish(benchmarkResult);
		}

	}

}
