import static hu.bme.mit.trainbenchmark.constants.RailwayOperation.*
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig

import com.esotericsoftware.kryo.Kryo
import com.esotericsoftware.kryo.io.Input
import com.esotericsoftware.kryo.io.Output

def config = new BenchmarkConfig()
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
