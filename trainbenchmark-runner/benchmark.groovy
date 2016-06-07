import static hu.bme.mit.trainbenchmark.constants.RailwayOperation.*

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigWrapper
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig

def modelPath = "../models/railway-repair-1.xmi"
def railwayOperations = [
	POSLENGTH_REPAIR,
	ROUTESENSOR_INJECT,
	SWITCHMONITORED
]
final BenchmarkConfig bc = new BenchmarkConfig(1, 5, "EMF API", modelPath, railwayOperations)
final BenchmarkConfigWrapper bcw = new BenchmarkConfigWrapper(bc)

bcw.saveToFile("/tmp/file.bin")
BenchmarkConfigWrapper.fromFile("/tmp/file.bin")

def toolname = "emfapi"
def projectname = "trainbenchmark-tool-${toolname}"
def script = "bash -c ${projectname}/build/install/${projectname}/${projectname}"

println script.execute().text
