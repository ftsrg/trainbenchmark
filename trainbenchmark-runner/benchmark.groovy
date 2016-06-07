import static hu.bme.mit.trainbenchmark.constants.RailwayOperation.*
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig

final BenchmarkConfig config = new BenchmarkConfig()
config.modelPath = "../models/railway-repair-1.xmi"
config.railwayOperations = [
	POSLENGTH_REPAIR,
	ROUTESENSOR_INJECT,
	SWITCHMONITORED
]

println(config.modelPath)
println(config.railwayOperations)

config.saveToFile("/tmp/file.bin")
BenchmarkConfig.fromFile("/tmp/file.bin")


println("ls".execute().text)
