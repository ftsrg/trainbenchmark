import static hu.bme.mit.trainbenchmark.constants.RailwayOperation.*
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigCore
import hu.bme.mit.trainbenchmark.benchmark.emfapi.config.EmfApiBenchmarkConfigWrapper
import hu.bme.mit.trainbenchmark.benchmark.jena.config.JenaBenchmarkConfigWrapper
import hu.bme.mit.trainbenchmark.benchmark.runner.BenchmarkRunner

import org.codehaus.groovy.runtime.InvokerHelper

class BenchmarkScript extends Script {

	def run() {
		def xms = "2G"
		def xmx = "4G"
		def modelPath = "railway-repair-1"
		def railwayOperations = [
			POSLENGTH_REPAIR,
			SWITCHMONITORED,
		]
		def timeout = 120
		def runs = 1
		def queryTransformationCount = 1
		def bc = new BenchmarkConfigCore(xms, xmx, timeout, runs, queryTransformationCount, modelPath, railwayOperations, "Batch")

		BenchmarkRunner.run(new EmfApiBenchmarkConfigWrapper(bc))
//		BenchmarkRunner.run(new SesameBenchmarkConfigWrapper(bc, true))
		BenchmarkRunner.run(new JenaBenchmarkConfigWrapper(bc, true))
	}
	
	public static void main(String[] args) {
		InvokerHelper.runScript(BenchmarkScript, args);
	}

}
