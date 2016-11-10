import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBaseBuilder
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBuilder
import hu.bme.mit.trainbenchmark.benchmark.config.ModelSetConfig
import hu.bme.mit.trainbenchmark.benchmark.config.TransformationChangeSetStrategy
import hu.bme.mit.trainbenchmark.benchmark.ingraph.config.IngraphBenchmarkConfigBuilder
import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkRunner
import hu.bme.mit.trainbenchmark.config.ExecutionConfig
import hu.bme.mit.trainbenchmark.constants.RailwayOperation
import hu.bme.mit.trainbenchmark.constants.Scenario

def ec = new ExecutionConfig(8000, 8000)

def scenarios = [Scenario.REPAIR]
def minSize = 1
def maxSize = 4
def timeout = 300
def runs = 5
def queryTransformationCount = 10
def transformationConstant = 10
def transformationChangeSetStrategy = TransformationChangeSetStrategy.FIXED
def messageSize = 2048

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
			println "Timeout or error occured, skipping models for larger sizes. Error code: ${exitValue}"
			break
		}
	}
}

for (scenario in scenarios) {
	def scenarioName = scenario.toString()
	def modelVariant = scenarioName.toLowerCase()

	def bcbb = new BenchmarkConfigBaseBuilder()
		.setTimeout(timeout).setRuns(runs).setQueryTransformationCount(queryTransformationCount)
		.setTransformationChangeSetStrategy(transformationChangeSetStrategy)
		.setTransformationConstant(transformationConstant);

	def modelSetConfig = new ModelSetConfig(modelVariant, minSize, maxSize)

	for (variant in 'A'..'C') {
		bcbb.setWorkload("RouteSensor")
		bcbb.setRailwayOperations([RailwayOperation.ROUTESENSOR_REPAIR])

		def bcb = new IngraphBenchmarkConfigBuilder().setMessageSize(messageSize).setQueryVariant(variant)
		runBenchmarkSeries(bcbb, bcb, ec, modelSetConfig)

	}

	for (variant in 'A'..'F') {
		bcbb.setWorkload("SemaphoreNeighbor")
		bcbb.setRailwayOperations([RailwayOperation.SEMAPHORENEIGHBOR_REPAIR])

		def bcb = new IngraphBenchmarkConfigBuilder().setMessageSize(messageSize).setQueryVariant(variant)
		runBenchmarkSeries(bcbb, bcb, ec, modelSetConfig)
	}
}
