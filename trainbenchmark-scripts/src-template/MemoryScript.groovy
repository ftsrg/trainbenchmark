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

def tools = [
	new BlazegraphBenchmarkConfigBuilder().setInferencing(false),
	new BlazegraphBenchmarkConfigBuilder().setInferencing(true),
	new EclipseOclBenchmarkConfig(),
	new DroolsBenchmarkConfigBuilder(),
	new EmfApiBenchmarkConfigBuilder(),
	new JenaBenchmarkConfigBuilder().setInferencing(false),
	new JenaBenchmarkConfigBuilder().setInferencing(true),
	new MySqlBenchmarkConfigBuilder(),
	new Neo4jBenchmarkConfigBuilder().setEngine(Neo4jEngine.COREAPI),
	new Neo4jBenchmarkConfigBuilder().setEngine(Neo4jEngine.CYPHER),
	new Rdf4jBenchmarkConfigBuilder().setInferencing(false),
	new SQLiteBenchmarkConfigBuilder(),
	new TinkerGraphBenchmarkConfigBuilder(),
	new ViatraBenchmarkConfigBuilder().setBackend(ViatraBackend.INCREMENTAL),
	new ViatraBenchmarkConfigBuilder().setBackend(ViatraBackend.LOCAL_SEARCH),
]

def workloads = [
	Batch: [
		operations: [
			RailwayOperation.CONNECTEDSEGMENTS,
			RailwayOperation.POSLENGTH,
			RailwayOperation.ROUTESENSOR,
			RailwayOperation.SEMAPHORENEIGHBOR,
			RailwayOperation.SWITCHSET,
			RailwayOperation.SWITCHMONITORED,
		],
		strategy: TransformationChangeSetStrategy.FIXED,
		constant: 0,
		queryTransformationCount: 0,
		numberOfSteps: 9,
	]
]

def runMemoryBenchmarkSeries(BenchmarkConfigBaseBuilder configBaseBuilder, BenchmarkConfigBuilder configBuilder,
		ExecutionConfig ec, ModelSetConfig modelSetConfig, int numberOfSteps) {
	for (def size = modelSetConfig.minSize; size <= modelSetConfig.maxSize; size *= 2) {
		def modelFilename = "railway-${modelSetConfig.modelVariant}-${size}"

		println("------------------------------------------------------------")
		println("Model: $modelFilename")
		println("------------------------------------------------------------")

		configBaseBuilder.setModelFilename(modelFilename)
		def configBase = configBaseBuilder.createConfigBase()
		def config = configBuilder.setConfigBase(configBase).createConfig()

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
	def workloadConfiguration = workload.value

	def operations = workloadConfiguration["operations"]
	def strategy = workloadConfiguration["strategy"]
	def constant = workloadConfiguration["constant"]
	def queryTransformationCount = workloadConfiguration["queryTransformationCount"]
	def numberOfSteps = workloadConfiguration["numberOfSteps"]

	println("============================================================")
	println("Workload: $workloadName")
	println("============================================================")

	def modelSetConfig = new ModelSetConfig(modelVariant, minSize, maxSize)

	def bcbb = new BenchmarkConfigBaseBuilder()
			.setTimeout(timeout).setRuns(1)
			.setQueryTransformationCount(queryTransformationCount).setRailwayOperations(operations)
			.setWorkload(workloadName).setTransformationChangeSetStrategy(TransformationChangeSetStrategy.FIXED)
			.setTransformationConstant(0);

	tools.each{ bcb -> runMemoryBenchmarkSeries(bcbb, bcb, ec, modelSetConfig, numberOfSteps) }
}

//BenchmarkReporter.reportReady()
