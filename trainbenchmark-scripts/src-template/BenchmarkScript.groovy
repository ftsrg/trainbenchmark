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

def ec = new ExecutionConfig(1000, 1000)

def minSize = 1
def maxSize = 2
def timeout = 900
def runs = 3
def queryTransformationCount = 5

def injectOperations = [
	RailwayOperation.CONNECTEDSEGMENTS_REPAIR,
	RailwayOperation.POSLENGTH_REPAIR,
	RailwayOperation.ROUTESENSOR_REPAIR,
	RailwayOperation.SEMAPHORENEIGHBOR_REPAIR,
	RailwayOperation.SWITCHSET_REPAIR,
	RailwayOperation.SWITCHMONITORED_REPAIR,
]
def repairOperations = [
	RailwayOperation.CONNECTEDSEGMENTS_INJECT,
	RailwayOperation.POSLENGTH_INJECT,
	RailwayOperation.ROUTESENSOR_INJECT,
	RailwayOperation.SEMAPHORENEIGHBOR_INJECT,
	RailwayOperation.SWITCHSET_INJECT,
	RailwayOperation.SWITCHMONITORED_INJECT,
]

def workloads = [
	Inject: injectOperations,
	Repair: repairOperations,
]

println()

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

		def bcb = new BenchmarkConfigBase(timeout, runs, queryTransformationCount, modelFilename, operations, workloadName, TransformationChangeSetStrategy.FIXED, 10)

		BenchmarkRunner.runPerformanceBenchmark(new BlazegraphBenchmarkConfig(bcb, false), ec)
		BenchmarkRunner.runPerformanceBenchmark(new BlazegraphBenchmarkConfig(bcb, true), ec)
		BenchmarkRunner.runPerformanceBenchmark(new EclipseOclBenchmarkConfig(bcb), ec)
		BenchmarkRunner.runPerformanceBenchmark(new DroolsBenchmarkConfig(bcb), ec)
		BenchmarkRunner.runPerformanceBenchmark(new EmfApiBenchmarkConfig(bcb), ec)
		BenchmarkRunner.runPerformanceBenchmark(new JenaBenchmarkConfig(bcb, false), ec)
		BenchmarkRunner.runPerformanceBenchmark(new JenaBenchmarkConfig(bcb, true), ec)
		BenchmarkRunner.runPerformanceBenchmark(new MySqlBenchmarkConfig(bcb), ec)
		BenchmarkRunner.runPerformanceBenchmark(new Neo4jBenchmarkConfig(bcb, Neo4jEngine.COREAPI), ec)
		BenchmarkRunner.runPerformanceBenchmark(new Neo4jBenchmarkConfig(bcb, Neo4jEngine.CYPHER), ec)
		BenchmarkRunner.runPerformanceBenchmark(new Rdf4jBenchmarkConfig(bcb, false), ec)
		BenchmarkRunner.runPerformanceBenchmark(new SQLiteBenchmarkConfig(bcb), ec)
		BenchmarkRunner.runPerformanceBenchmark(new TinkerGraphBenchmarkConfig(bcb), ec)
		BenchmarkRunner.runPerformanceBenchmark(new ViatraBenchmarkConfig(bcb, ViatraBackend.INCREMENTAL), ec)
		BenchmarkRunner.runPerformanceBenchmark(new ViatraBenchmarkConfig(bcb, ViatraBackend.LOCAL_SEARCH), ec)
	}
}

//BenchmarkReporter.reportReady()
