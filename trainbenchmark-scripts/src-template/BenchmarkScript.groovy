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

def ec = new ExecutionConfig(8000, 8000)

def minSize = 1
def maxSize = 256
def timeout = 900
def runs = 5

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
	Inject: [
		operations: [
			RailwayOperation.CONNECTEDSEGMENTS_INJECT,
			RailwayOperation.POSLENGTH_INJECT,
			RailwayOperation.ROUTESENSOR_INJECT,
			RailwayOperation.SEMAPHORENEIGHBOR_INJECT,
			RailwayOperation.SWITCHSET_INJECT,
			RailwayOperation.SWITCHMONITORED_INJECT,
		],
		strategy: TransformationChangeSetStrategy.FIXED,
		constant: 10,
		queryTransformationCount: 5,
	],
	Repair: [
		operations: [
			RailwayOperation.CONNECTEDSEGMENTS_REPAIR,
			RailwayOperation.POSLENGTH_REPAIR,
			RailwayOperation.ROUTESENSOR_REPAIR,
			RailwayOperation.SEMAPHORENEIGHBOR_REPAIR,
			RailwayOperation.SWITCHSET_REPAIR,
			RailwayOperation.SWITCHMONITORED_REPAIR,
		],
		strategy: TransformationChangeSetStrategy.PROPORTIONAL,
		constant: 10,
		queryTransformationCount: 5,
	]
]

def runBenchmarkSeries(BenchmarkConfigBaseBuilder configBaseBuilder, BenchmarkConfigBuilder configBuilder,
		ExecutionConfig ec, ModelSetConfig modelSetConfig) {
	for (def size = modelSetConfig.minSize; size <= modelSetConfig.maxSize; size *= 2) {
		def modelFilename = "railway-${modelSetConfig.modelVariant}-${size}"

		println("------------------------------------------------------------")
		println("Model: $modelFilename")
		println("------------------------------------------------------------")

		configBaseBuilder.setModelFilename(modelFilename)
		def configBase = configBaseBuilder.createConfigBase()
		def config = configBuilder.setConfigBase(configBase).createConfig()

		def exitValue = BenchmarkRunner.runPerformanceBenchmark(config, ec)
		if (exitValue != 0) {
			println "Timeout or error occured, skipping models for larger sizes. Error code: ${exitValue}"
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

	println("============================================================")
	println("Workload: $workloadName")
	println("============================================================")

	def modelSetConfig = new ModelSetConfig(modelVariant, minSize, maxSize)

	def bcbb = new BenchmarkConfigBaseBuilder()
			.setTimeout(timeout).setRuns(runs).setQueryTransformationCount(queryTransformationCount)
			.setRailwayOperations(operations).setWorkload(workloadName).setTransformationChangeSetStrategy(strategy)
			.setTransformationConstant(constant);

	tools.each{ bcb -> runBenchmarkSeries(bcbb, bcb, ec, modelSetConfig) }
}

//BenchmarkReporter.reportReady()
