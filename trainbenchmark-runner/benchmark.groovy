import static hu.bme.mit.trainbenchmark.constants.RailwayOperation.*

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigWrapper
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig

def modelPath = "../models/railway-repair-1.xmi"
def railwayOperations = [
	POSLENGTH_REPAIR,
	ROUTESENSOR_INJECT,
	SWITCHMONITORED,
]
final BenchmarkConfig bc = new BenchmarkConfig(1, 5, "EMF API", modelPath, railwayOperations)
final BenchmarkConfigWrapper bcw = new BenchmarkConfigWrapper(bc)
//
//BenchmarkRunner.runBenchmark(bcw)
//
//def configFile = File.createTempFile("trainbenchmark-", ".conf")
//def configPath = configFile.absolutePath;
//bcw.saveToFile(configPath)
//BenchmarkConfigWrapper.fromFile(configPath)
//
//def toolName = "emfapi"
//def projectName = "trainbenchmark-tool-${toolName}"
//
//def script = "../${projectName}/build/install/${projectName}/bin/${projectName} ${configPath}"
//
//def sout = new StringBuilder()
//def serr = new StringBuilder()
//
//println(script)
//def proc = script.execute()
//proc.consumeProcessOutput(sout, serr)
//proc.waitForOrKill(1000)
//println "out> $sout"
//println "err> $serr"
//
//configFile.deleteOnExit()
