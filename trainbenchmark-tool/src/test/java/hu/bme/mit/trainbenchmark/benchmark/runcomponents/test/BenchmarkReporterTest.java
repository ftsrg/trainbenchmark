package hu.bme.mit.trainbenchmark.benchmark.runcomponents.test;

import java.io.IOException;
import java.net.SocketException;

import org.junit.Ignore;
import org.junit.Test;

import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkReporter;

public class BenchmarkReporterTest {

	@Ignore
	@Test
	public void test() throws SocketException, IOException {
		final String reportUrl = "https://hooks.slack.com/services/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";
		BenchmarkReporter.reportReady(reportUrl);
	}

}
