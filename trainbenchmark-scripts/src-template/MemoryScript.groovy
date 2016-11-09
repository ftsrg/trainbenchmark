import hu.bme.mit.trainbenchmark.benchmark.blazegraph.config.BlazegraphBenchmarkConfigBuilder
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBaseBuilder
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBuilder
import hu.bme.mit.trainbenchmark.benchmark.config.ModelSetConfig
import hu.bme.mit.trainbenchmark.benchmark.config.TransformationChangeSetStrategy
import hu.bme.mit.trainbenchmark.benchmark.drools.config.DroolsBenchmarkConfigBuilder
import hu.bme.mit.trainbenchmark.benchmark.eclipseocl.config.EclipseOclBenchmarkConfig
import hu.bme.mit.trainbenchmark.benchmark.emfapi.config.EmfApiBenchmarkConfigBuilder
import hu.bme.mit.trainbenchmark.benchmark.jena.config.JenaBenchmarkConfigBuilder
import hu.bme.mit.trainbenchmark.benchmark.mysql.config.MySqlBenchmarkConfigBuilder
import hu.bme.mit.trainbenchmark.benchmark.neo4j.config.Neo4jBenchmarkConfigBuilder
import hu.bme.mit.trainbenchmark.benchmark.neo4j.config.Neo4jEngine
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.config.Rdf4jBenchmarkConfigBuilder
import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkRunner
import hu.bme.mit.trainbenchmark.benchmark.sqlite.config.SQLiteBenchmarkConfigBuilder
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.config.TinkerGraphBenchmarkConfigBuilder
import hu.bme.mit.trainbenchmark.benchmark.viatra.config.ViatraBackend
import hu.bme.mit.trainbenchmark.benchmark.viatra.config.ViatraBenchmarkConfigBuilder
import hu.bme.mit.trainbenchmark.config.ExecutionConfig
import hu.bme.mit.trainbenchmark.constants.RailwayOperation

def ec = new ExecutionConfig(12800, 12800)

def minSize = 1
def maxSize = 2
def timeout = 900
def queryTransformationCount = 0

// number of steps for memory measurement
def numberOfSteps = 9

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

def runMemoryBenchmarkSeries(BenchmarkConfigBaseBuilder configBaseBuilder, BenchmarkConfigBuilder configBuilder,
		ExecutionConfig ec, ModelSetConfig modelSetConfig, int numberOfSteps) {
	for (def size = modelSetConfig.minSize; size <= modelSetConfig.maxSize; size *= 2) {
		def modelFilename = "railway-${modelSetConfig.modelVariant}-${size}"

		println("------------------------------------------------------------")
		println("Model: $modelFilename")
		println("------------------------------------------------------------")

		configBaseBuilder.setModelFilename(modelFilename)
		def configBase = configBaseBuilder.createBenchmarkConfigBase()
		def config = configBuilder.setConfigBase(configBase).createBenchmarkConfig()

		def exitValue = BenchmarkRunner.runMemoryBenchmark(config, ec, numberOfSteps)
		if (exitValue != 0) {
			println "Timeout or error occured, skipping models for larger sizes. Error code: {$exitValue}"
			break
		}
	}
}

workloads.each { workload ->
	def workloadName = workload.key
	def modelVariant = workloadName.toLowerCase()
	def operations = workload.value

	println("============================================================")
	println("Workload: $workloadName")
	println("============================================================")

	def modelSetConfig = new ModelSetConfig(modelVariant, minSize, maxSize)

	def bcbb = new BenchmarkConfigBaseBuilder()
			.setTimeout(timeout).setRuns(1)
			.setQueryTransformationCount(queryTransformationCount).setRailwayOperations(operations)
			.setWorkload(workloadName).setTransformationChangeSetStrategy(TransformationChangeSetStrategy.FIXED)
			.setTransformationConstant(0);

	runMemoryBenchmarkSeries(bcbb, new BlazegraphBenchmarkConfigBuilder().setInferencing(false), ec, modelSetConfig, numberOfSteps)
	runMemoryBenchmarkSeries(bcbb, new BlazegraphBenchmarkConfigBuilder().setInferencing(true), ec, modelSetConfig, numberOfSteps)
	runMemoryBenchmarkSeries(bcbb, new EclipseOclBenchmarkConfig(), ec, modelSetConfig, numberOfSteps)
	runMemoryBenchmarkSeries(bcbb, new DroolsBenchmarkConfigBuilder(), ec, modelSetConfig, numberOfSteps)
	runMemoryBenchmarkSeries(bcbb, new EmfApiBenchmarkConfigBuilder(), ec, modelSetConfig, numberOfSteps)
	runMemoryBenchmarkSeries(bcbb, new JenaBenchmarkConfigBuilder().setInferencing(false), ec, modelSetConfig, numberOfSteps)
	runMemoryBenchmarkSeries(bcbb, new JenaBenchmarkConfigBuilder().setInferencing(true), ec, modelSetConfig, numberOfSteps)
	runMemoryBenchmarkSeries(bcbb, new MySqlBenchmarkConfigBuilder(), ec, modelSetConfig, numberOfSteps)
	runMemoryBenchmarkSeries(bcbb, new Neo4jBenchmarkConfigBuilder().setEngine(Neo4jEngine.COREAPI), ec, modelSetConfig, numberOfSteps)
	runMemoryBenchmarkSeries(bcbb, new Neo4jBenchmarkConfigBuilder().setEngine(Neo4jEngine.CYPHER), ec, modelSetConfig, numberOfSteps)
	runMemoryBenchmarkSeries(bcbb, new Rdf4jBenchmarkConfigBuilder().setInferencing(false), ec, modelSetConfig, numberOfSteps)
	runMemoryBenchmarkSeries(bcbb, new SQLiteBenchmarkConfigBuilder(), ec, modelSetConfig, numberOfSteps)
	runMemoryBenchmarkSeries(bcbb, new TinkerGraphBenchmarkConfigBuilder(), ec, modelSetConfig, numberOfSteps)
	runMemoryBenchmarkSeries(bcbb, new ViatraBenchmarkConfigBuilder().setBackend(ViatraBackend.INCREMENTAL), ec, modelSetConfig, numberOfSteps)
	runMemoryBenchmarkSeries(bcbb, new ViatraBenchmarkConfigBuilder().setBackend(ViatraBackend.LOCAL_SEARCH), ec, modelSetConfig, numberOfSteps)
}

//BenchmarkReporter.reportReady()
