import static hu.bme.mit.trainbenchmark.constants.RailwayOperation.*
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigCore
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigWrapper
import hu.bme.mit.trainbenchmark.benchmark.rdf.RdfBenchmarkConfigWrapper
import hu.bme.mit.trainbenchmark.benchmark.runner.BenchmarkRunner

class BenchmarkScript extends Script {

	def run() {
		def xms = "2G"
		def xmx = "4G"
		def modelPath = "railway-repair-1"
		def railwayOperations = [
			POSLENGTH_REPAIR,
			SWITCHMONITORED,
		]
		def bc = new BenchmarkConfigCore(xms, xmx, 1, 5, modelPath, railwayOperations)

		def emfBCW = new BenchmarkConfigWrapper(bc)
		BenchmarkRunner.run(emfBCW, "emfapi")

		def rdfBCW = new RdfBenchmarkConfigWrapper(bc, false)
		BenchmarkRunner.run(rdfBCW, "sesame")
	}
}