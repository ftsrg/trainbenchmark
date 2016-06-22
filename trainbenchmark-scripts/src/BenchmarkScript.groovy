import static hu.bme.mit.trainbenchmark.constants.RailwayOperation.*

import org.junit.After;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigCore
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.config.IQDBenchmarkConfigWrapper
import hu.bme.mit.trainbenchmark.benchmark.runner.BenchmarkRunner
import hu.bme.mit.trainbenchmark.constants.Scenario

def xms = "2G"
def xmx = "4G"
def minSize = 1
def maxSize = 2

def scenarios = [
	//	Scenario.BATCH,
	//	Scenario.INJECT,
	Scenario.REPAIR,
]
def railwayOperations = []

for (scenario in scenarios) {
	for (size = minSize; size <= maxSize; size*=2) {
		def scenarioString = scenario.toString().toLowerCase()
		def modelPath = "railway-${scenarioString}-${size}"
		def timeout = 120
		def runs = 1
		def queryTransformationCount = 2
		def messageSize = 10

		def operations1 = [ROUTESENSOR_REPAIR]
		def bcRouteSensor = new BenchmarkConfigCore(xms, xmx, timeout, runs, queryTransformationCount, modelPath, operations1, "PerPol")
		for (variant in ["", "2"]) {
			BenchmarkRunner.run(new IQDBenchmarkConfigWrapper(bcRouteSensor, messageSize, variant))
		}
		
		def operations2 = [CONNECTEDSEGMENTS_REPAIR]
		def bcConnectedSegments = new BenchmarkConfigCore(xms, xmx, timeout, runs, queryTransformationCount, modelPath, operations2, "PerPol")
		for (variant in ["", "2", "3", "4"]) {
			BenchmarkRunner.run(new IQDBenchmarkConfigWrapper(bcConnectedSegments, messageSize, variant))
		}
	}
}


//		BenchmarkRunner.run(new BlazegraphBenchmarkConfigWrapper(bc, false))
//		BenchmarkRunner.run(new EclipseOclBenchmarkConfigWrapper(bc))
//		BenchmarkRunner.run(new Drools5BenchmarkConfigWrapper(bc))
//		BenchmarkRunner.run(new Drools6BenchmarkConfigWrapper(bc))
//		BenchmarkRunner.run(new EmfApiBenchmarkConfigWrapper(bc))
//		BenchmarkRunner.run(new SesameBenchmarkConfigWrapper(bc, false))

//		BenchmarkRunner.run(new JenaBenchmarkConfigWrapper(bc, false))
//		BenchmarkRunner.run(new MySqlBenchmarkConfigWrapper(bc))
//		BenchmarkRunner.run(new Neo4jBenchmarkConfigWrapper(bc, Neo4jEngine.COREAPI))
//		BenchmarkRunner.run(new Neo4jBenchmarkConfigWrapper(bc, Neo4jEngine.CYPHER))
//		BenchmarkRunner.run(new SQLiteBenchmarkConfigWrapper(bc))
//		BenchmarkRunner.run(new TinkerGraphBenchmarkConfigWrapper(bc))
//		BenchmarkRunner.run(new ViatraBenchmarkConfigWrapper(bc))
