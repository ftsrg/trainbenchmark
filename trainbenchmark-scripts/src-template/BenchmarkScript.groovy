import static hu.bme.mit.trainbenchmark.constants.RailwayOperation.*
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigCore
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.config.IQDBenchmarkConfigWrapper
import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkRunner
import hu.bme.mit.trainbenchmark.constants.Scenario
import BenchmarkReporter

def xms = "12G"
def xmx = "12G"
def minSize = 1
def maxSize = 2
def timeout = 900
def runs = 1
def queryTransformationCount = 0

def scenarios = [
	Scenario.BATCH,
	Scenario.INJECT,
	Scenario.REPAIR,
]

for (scenario in scenarios) {
	def scenarioString = scenario.toString().toLowerCase()
	def messageSize = 2048

//	BenchmarkRunner.run(new BlazegraphBenchmarkConfigWrapper(bc, false))
//	BenchmarkRunner.run(new EclipseOclBenchmarkConfigWrapper(bc))
//	BenchmarkRunner.run(new Drools5BenchmarkConfigWrapper(bc))
//	BenchmarkRunner.run(new Drools6BenchmarkConfigWrapper(bc))
//	BenchmarkRunner.run(new EmfApiBenchmarkConfigWrapper(bc))
//	BenchmarkRunner.run(new JenaBenchmarkConfigWrapper(bc, false))
//	BenchmarkRunner.run(new MySqlBenchmarkConfigWrapper(bc))
//	BenchmarkRunner.run(new Neo4jBenchmarkConfigWrapper(bc, Neo4jEngine.COREAPI))
//	BenchmarkRunner.run(new Neo4jBenchmarkConfigWrapper(bc, Neo4jEngine.CYPHER))
//	BenchmarkRunner.run(new SesameBenchmarkConfigWrapper(bc, false))
//	BenchmarkRunner.run(new SQLiteBenchmarkConfigWrapper(bc))
//	BenchmarkRunner.run(new TinkerGraphBenchmarkConfigWrapper(bc))
//	BenchmarkRunner.run(new ViatraBenchmarkConfigWrapper(bc))

}

BenchmarkReporter.reportReady()
