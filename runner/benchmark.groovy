
import static hu.bme.mit.trainbenchmark.constants.RailwayOperation.*
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig

import com.esotericsoftware.kryo.Kryo
import com.esotericsoftware.kryo.io.Input
import com.esotericsoftware.kryo.io.Output

def config = new BenchmarkConfig()
config.modelPath = "../models/railway-repair-1.xmi"
config.railwayOperations = [POSLENGTH_REPAIR, ROUTESENSOR_INJECT, SWITCHMONITORED]

println(config.modelPath)
println(config.railwayOperations)

def kryo = new Kryo()
def output = new Output(new FileOutputStream("/tmp/file.bin"))
kryo.writeObject(output, config)
output.close()

def input = new Input(new FileInputStream("/tmp/file.bin"))
def someObject = kryo.readObject(input, BenchmarkConfig.class)
println(someObject)
input.close()

println("ls".execute().text)
