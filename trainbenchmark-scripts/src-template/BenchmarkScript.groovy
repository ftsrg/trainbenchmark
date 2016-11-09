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

def ec = new ExecutionConfig(1000, 1000)

def minSize = 1
def maxSize = 32
def timeout = 900
def runs = 2
def queryTransformationCount = 5

def repairOperations = [
	RailwayOperation.CONNECTEDSEGMENTS_REPAIR,
	RailwayOperation.POSLENGTH_REPAIR,
	RailwayOperation.ROUTESENSOR_REPAIR,
	RailwayOperation.SEMAPHORENEIGHBOR_REPAIR,
	RailwayOperation.SWITCHSET_REPAIR,
	RailwayOperation.SWITCHMONITORED_REPAIR,
]
def injectOperations = [
	RailwayOperation.CONNECTEDSEGMENTS_INJECT,
	RailwayOperation.POSLENGTH_INJECT,
	RailwayOperation.ROUTESENSOR_INJECT,
	RailwayOperation.SEMAPHORENEIGHBOR_INJECT,
	RailwayOperation.SWITCHSET_INJECT,
	RailwayOperation.SWITCHMONITORED_INJECT,
]

def workloads = [
	Inject: [
		operations: injectOperations,
		strategy: TransformationChangeSetStrategy.FIXED,
		constant: 10,
	],
	Repair: [
		operations: repairOperations,
		strategy: TransformationChangeSetStrategy.PROPORTIONAL,
		constant: 10,
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
		def configBase = configBaseBuilder.createBenchmarkConfigBase()
		def config = configBuilder.setConfigBase(configBase).createBenchmarkConfig()

		def exitValue = BenchmarkRunner.runPerformanceBenchmark(config, ec)
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

	println("============================================================")
	println("Workload: $workloadName")
	println("============================================================")

	def modelSetConfig = new ModelSetConfig(modelVariant, minSize, maxSize)

	def bcbb = new BenchmarkConfigBaseBuilder()
			.setTimeout(timeout).setRuns(runs)
			.setQueryTransformationCount(queryTransformationCount).setRailwayOperations(operations)
			.setWorkload(workloadName).setTransformationChangeSetStrategy(strategy)
			.setTransformationConstant(constant);

	runBenchmarkSeries(bcbb, new BlazegraphBenchmarkConfigBuilder().setInferencing(false), ec, modelSetConfig)
	runBenchmarkSeries(bcbb, new BlazegraphBenchmarkConfigBuilder().setInferencing(true), ec, modelSetConfig)
	runBenchmarkSeries(bcbb, new EclipseOclBenchmarkConfig(), ec, modelSetConfig)
	runBenchmarkSeries(bcbb, new DroolsBenchmarkConfigBuilder(), ec, modelSetConfig)
	runBenchmarkSeries(bcbb, new EmfApiBenchmarkConfigBuilder(), ec, modelSetConfig)
	runBenchmarkSeries(bcbb, new JenaBenchmarkConfigBuilder().setInferencing(false), ec, modelSetConfig)
	runBenchmarkSeries(bcbb, new JenaBenchmarkConfigBuilder().setInferencing(true), ec, modelSetConfig)
	runBenchmarkSeries(bcbb, new MySqlBenchmarkConfigBuilder(), ec, modelSetConfig)
	runBenchmarkSeries(bcbb, new Neo4jBenchmarkConfigBuilder().setEngine(Neo4jEngine.COREAPI), ec, modelSetConfig)
	runBenchmarkSeries(bcbb, new Neo4jBenchmarkConfigBuilder().setEngine(Neo4jEngine.CYPHER), ec, modelSetConfig)
	runBenchmarkSeries(bcbb, new Rdf4jBenchmarkConfigBuilder().setInferencing(false), ec, modelSetConfig)
	runBenchmarkSeries(bcbb, new SQLiteBenchmarkConfigBuilder(), ec, modelSetConfig)
	runBenchmarkSeries(bcbb, new TinkerGraphBenchmarkConfigBuilder(), ec, modelSetConfig)
	runBenchmarkSeries(bcbb, new ViatraBenchmarkConfigBuilder().setBackend(ViatraBackend.INCREMENTAL), ec, modelSetConfig)
	runBenchmarkSeries(bcbb, new ViatraBenchmarkConfigBuilder().setBackend(ViatraBackend.LOCAL_SEARCH), ec, modelSetConfig)
}

//BenchmarkReporter.reportReady()
