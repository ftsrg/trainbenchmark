import static hu.bme.mit.trainbenchmark.constants.RailwayOperation.*
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigCore
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.config.IQDBenchmarkConfigWrapper
import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkRunner
import hu.bme.mit.trainbenchmark.constants.Scenario

def xms = "12G"
def xmx = "12G"
def minSize = 1
def maxSize = 2
def timeout = 900
def runs = 1
def queryTransformationCount = 0

def scenarios = [
	//	Scenario.BATCH,
	//	Scenario.INJECT,
	Scenario.REPAIR,
]

for (scenario in scenarios) {
	def scenarioString = scenario.toString().toLowerCase()
	def messageSize = 2048

	def operations1 = [ACTIVEROUTE]
	for (variant in ["A", "B", "C", "D"]) {
		for (size = minSize; size <= maxSize; size *= 2) {
			def modelPath = "railway-${scenarioString}-${size}"
			def bcRouteSensor = new BenchmarkConfigCore(xms, xmx, timeout, runs, queryTransformationCount, modelPath, operations1, "ActiveRoute")
			if (BenchmarkRunner.run(new IQDBenchmarkConfigWrapper(bcRouteSensor, messageSize, variant)) == 143) {
				break
			}
		}
	}
	
	//	def operations1 = [ROUTESENSOR_REPAIR]
	//	for (variant in ["A", "B"]) {
	//		for (size = minSize; size <= maxSize; size *= 2) {
	//			def modelPath = "railway-${scenarioString}-${size}"
	//			def bcRouteSensor = new BenchmarkConfigCore(xms, xmx, timeout, runs, queryTransformationCount, modelPath, operations1, "RouteSensor")
	//			if (BenchmarkRunner.run(new IQDBenchmarkConfigWrapper(bcRouteSensor, messageSize, variant)) == 143) {
	//				break
	//			}
	//		}
	//	}
	//
	//	def operations2 = [CONNECTEDSEGMENTS_REPAIR]
	//	for (variant in ["A", "B", "C", "D"]) {
	//		for (size = minSize; size <= maxSize; size *= 2) {
	//			def modelPath = "railway-${scenarioString}-${size}"
	//			def bcConnectedSegments = new BenchmarkConfigCore(xms, xmx, timeout, runs, queryTransformationCount, modelPath, operations2, "ConnectedSegments")
	//			if (BenchmarkRunner.run(new IQDBenchmarkConfigWrapper(bcConnectedSegments, messageSize, variant)) == 143) {
	//				break
	//			}
	//		}
	//	}
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
