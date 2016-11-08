import hu.bme.mit.trainbenchmark.benchmark.blazegraph.config.BlazegraphBenchmarkConfig
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBase
import hu.bme.mit.trainbenchmark.benchmark.config.TransformationChangeSetStrategy
import hu.bme.mit.trainbenchmark.benchmark.drools.config.DroolsBenchmarkConfig
import hu.bme.mit.trainbenchmark.benchmark.eclipseocl.config.EclipseOclBenchmarkConfig
import hu.bme.mit.trainbenchmark.benchmark.emfapi.config.EmfApiBenchmarkConfig
import hu.bme.mit.trainbenchmark.benchmark.jena.config.JenaBenchmarkConfig
import hu.bme.mit.trainbenchmark.benchmark.mysql.config.MySqlBenchmarkConfig
import hu.bme.mit.trainbenchmark.benchmark.neo4j.config.Neo4jBenchmarkConfig
import hu.bme.mit.trainbenchmark.benchmark.neo4j.config.Neo4jEngine
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.config.Rdf4jBenchmarkConfig
import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkRunner
import hu.bme.mit.trainbenchmark.benchmark.sqlite.config.SQLiteBenchmarkConfig
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.config.TinkerGraphBenchmarkConfig
import hu.bme.mit.trainbenchmark.benchmark.viatra.config.ViatraBackend
import hu.bme.mit.trainbenchmark.benchmark.viatra.config.ViatraBenchmarkConfig
import hu.bme.mit.trainbenchmark.config.ExecutionConfig
import hu.bme.mit.trainbenchmark.constants.RailwayOperation

def ec = new ExecutionConfig(12800, 12800)

def minSize = 1
def maxSize = 2
def timeout = 900
def runs = 3
def queryTransformationCount = 5
def numberOfSteps = 5

def batchOperations = [
	RailwayOperation.CONNECTEDSEGMENTS,
	RailwayOperation.POSLENGTH,
	RailwayOperation.ROUTESENSOR,
	RailwayOperation.SEMAPHORENEIGHBOR,
	RailwayOperation.SWITCHSET,
	RailwayOperation.SWITCHMONITORED,
]

def workloads = [
	Batch: batchOperations
]

workloads.each { workload ->
	def workloadName = workload.key
	def modelVariant = workloadName.toLowerCase()
	def operations = workload.value

	println("============================================================")
	println("Workload: $workloadName")
	println("============================================================")

	for (size = minSize; size <= maxSize; size *= 2) {
		def modelFilename = "railway-$modelVariant-$size"

		println("------------------------------------------------------------")
		println("Model: $modelFilename")
		println("------------------------------------------------------------")

		def bcb = new BenchmarkConfigBase(
			timeout, runs, queryTransformationCount, modelFilename, operations, workloadName, TransformationChangeSetStrategy.FIXED, 10)

		BenchmarkRunner.runMemoryBenchmark(new BlazegraphBenchmarkConfig(bcb, false), ec, numberOfSteps)
		BenchmarkRunner.runMemoryBenchmark(new BlazegraphBenchmarkConfig(bcb, true), ec, numberOfSteps)
		BenchmarkRunner.runMemoryBenchmark(new EclipseOclBenchmarkConfig(bcb), ec, numberOfSteps)
		BenchmarkRunner.runMemoryBenchmark(new DroolsBenchmarkConfig(bcb), ec, numberOfSteps)
		BenchmarkRunner.runMemoryBenchmark(new EmfApiBenchmarkConfig(bcb), ec, numberOfSteps)
		BenchmarkRunner.runMemoryBenchmark(new JenaBenchmarkConfig(bcb, false), ec, numberOfSteps)
		BenchmarkRunner.runMemoryBenchmark(new JenaBenchmarkConfig(bcb, true), ec, numberOfSteps)
		BenchmarkRunner.runMemoryBenchmark(new MySqlBenchmarkConfig(bcb), ec, numberOfSteps)
		BenchmarkRunner.runMemoryBenchmark(new Neo4jBenchmarkConfig(bcb, Neo4jEngine.COREAPI), ec, numberOfSteps)
		BenchmarkRunner.runMemoryBenchmark(new Neo4jBenchmarkConfig(bcb, Neo4jEngine.CYPHER), ec, numberOfSteps)
		BenchmarkRunner.runMemoryBenchmark(new Rdf4jBenchmarkConfig(bcb, false), ec, numberOfSteps)
		BenchmarkRunner.runMemoryBenchmark(new SQLiteBenchmarkConfig(bcb), ec, numberOfSteps)
		BenchmarkRunner.runMemoryBenchmark(new TinkerGraphBenchmarkConfig(bcb), ec, numberOfSteps)
		BenchmarkRunner.runMemoryBenchmark(new ViatraBenchmarkConfig(bcb, ViatraBackend.INCREMENTAL), ec, numberOfSteps)
		BenchmarkRunner.runMemoryBenchmark(new ViatraBenchmarkConfig(bcb, ViatraBackend.LOCAL_SEARCH), ec, numberOfSteps)
	}
}

//BenchmarkReporter.reportReady()
