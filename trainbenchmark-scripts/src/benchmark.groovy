import static hu.bme.mit.trainbenchmark.constants.RailwayOperation.*
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigCore
import hu.bme.mit.trainbenchmark.benchmark.runner.BenchmarkRunner
import hu.bme.mit.trainbenchmark.benchmark.emfapi.config.EmfApiBenchmarkConfigWrapper
import hu.bme.mit.trainbenchmark.benchmark.sesame.config.SesameBenchmarkConfigWrapper

class BenchmarkScript extends Script {

	def run() {
		def xms = "2G"
		def xmx = "4G"
		def modelPath = "railway-repair-1"
		def railwayOperations = [
			POSLENGTH_REPAIR,
			SWITCHMONITORED,
		]
		def bc = new BenchmarkConfigCore(xms, xmx, 1, 5, modelPath, railwayOperations, "Batch")

		def emfApiBcw = new EmfApiBenchmarkConfigWrapper(bc)
		BenchmarkRunner.run(emfApiBcw)

		def sesameBcw = new SesameBenchmarkConfigWrapper(bc)
		BenchmarkRunner.run(sesameBcw)
	}
}
