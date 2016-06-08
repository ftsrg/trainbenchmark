import static hu.bme.mit.trainbenchmark.constants.RailwayOperation.*
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigWrapper
import hu.bme.mit.trainbenchmark.runner.BenchmarkRunner

def modelPath = "../models/railway-repair-1.xmi"
def railwayOperations = [
	POSLENGTH_REPAIR,
	ROUTESENSOR_INJECT,
	SWITCHMONITORED,
]
def bc = new BenchmarkConfig(1, 5, "EMF API", modelPath, railwayOperations)

def emfCW = new BenchmarkConfigWrapper(bc)
BenchmarkRunner.runBenchmark(emfCW)

def emfCW2 = new BenchmarkConfigWrapper(bc)
BenchmarkRunner.runBenchmark(emfCW2)
