import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBaseBuilder
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBuilder
import hu.bme.mit.trainbenchmark.benchmark.config.ModelSetConfig
import hu.bme.mit.trainbenchmark.benchmark.config.TransformationChangeSetStrategy
//import hu.bme.mit.trainbenchmark.benchmark.ingraph.config.IngraphBenchmarkConfigBuilder
import hu.bme.mit.trainbenchmark.benchmark.result.ResultHelper
import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkRunner
import hu.bme.mit.trainbenchmark.config.ExecutionConfig
import hu.bme.mit.trainbenchmark.constants.RailwayOperation
import hu.bme.mit.trainbenchmark.generator.config.Scenario

def benchmarkId = ResultHelper.createNewResultDir()
ResultHelper.saveConfiguration(benchmarkId)
def ec = new ExecutionConfig(12000, 12000)

def scenarios = [Scenario.REPAIR]
def minSize = 1
def maxSize = 2048
def timeout = 900
def runs = 10
def queryTransformationCount = 100
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
		def configBase = configBaseBuilder.createConfigBase()
		def config = configBuilder.setConfigBase(configBase).createConfig()

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

	def bcbb = new BenchmarkConfigBaseBuilder().setBenchmarkId(benchmarkId)
			.setTimeout(timeout).setRuns(runs).setQueryTransformationCount(queryTransformationCount)
			.setTransformationChangeSetStrategy(transformationChangeSetStrategy)
			.setTransformationConstant(transformationConstant);

	def modelSetConfig = new ModelSetConfig(modelVariant, minSize, maxSize)

	for (variant in 'A'..'C') {
		bcbb.setWorkload("RouteSensor")
		bcbb.setOperations([
			RailwayOperation.ROUTESENSOR_REPAIR
		])

//		def bcb = new IngraphBenchmarkConfigBuilder().setMessageSize(messageSize).setQueryVariant(variant)
//		runBenchmarkSeries(bcbb, bcb, ec, modelSetConfig)
	}

	for (variant in 'A'..'F') {
		bcbb.setWorkload("SemaphoreNeighbor")
		bcbb.setOperations([
			RailwayOperation.SEMAPHORENEIGHBOR_REPAIR
		])

//		def bcb = new IngraphBenchmarkConfigBuilder().setMessageSize(messageSize).setQueryVariant(variant)
//		runBenchmarkSeries(bcbb, bcb, ec, modelSetConfig)
	}
}
