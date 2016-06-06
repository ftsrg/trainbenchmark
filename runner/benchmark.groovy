@Grapes([
	@Grab(group='hu.bme.mit.trainbenchmark', module='hu.bme.mit.trainbenchmark.benchmark', version='1.0.0-SNAPSHOT', changing=true),
	@Grab(group='com.google.guava', module='guava', version='19.0'),
	@Grab(group='com.esotericsoftware', module='kryo', version='3.0.3'),
	@Grab(group='org.ow2.asm', module='asm', version='5.1')
])

import com.google.common.collect.ImmutableList
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig
import static hu.bme.mit.trainbenchmark.constants.RailwayOperation.*
import com.esotericsoftware.kryo.Kryo
import com.esotericsoftware.kryo.io.Input
import com.esotericsoftware.kryo.io.Output

import java.io.FileInputStream
import java.io.FileOutputStream

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
