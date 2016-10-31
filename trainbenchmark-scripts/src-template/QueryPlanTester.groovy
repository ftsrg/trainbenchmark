import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBase
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.config.IqdCoreBenchmarkConfig
import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkRunner
import hu.bme.mit.trainbenchmark.constants.RailwayOperation
import hu.bme.mit.trainbenchmark.constants.Scenario

def scenarios = [Scenario.REPAIR]
def minSize = 1
def maxSize = 2
def xms = "2G"
def xmx = "2G"
def timeout = 60
def runs = 5
def queryTransformationCount = 10

for (scenario in scenarios) {
	def scenarioString = scenario.toString().toLowerCase()
	def messageSize = 2048

	def operationsRouteSensor = [
		RailwayOperation.ROUTESENSOR_REPAIR
	]
	for (variant in 'A'..'C') {
		for (size = minSize; size <= maxSize; size *= 2) {
			def modelPath = "railway-${scenarioString}-${size}"
			def bc = new BenchmarkConfigBase(xms, xmx, timeout, runs, queryTransformationCount, modelPath, operationsRouteSensor, "RouteSensor")
			if (BenchmarkRunner.run(new IqdCoreBenchmarkConfig(bc, messageSize, variant, null)) == 143) {
				break
			}
		}
	}

	def operationsSemaphoreNeighbor = [
		RailwayOperation.SEMAPHORENEIGHBOR_REPAIR
	]
	for (variant in 'A'..'F') {
		for (size = minSize; size <= maxSize; size *= 2) {
			def modelPath = "railway-${scenarioString}-${size}"
			def bc = new BenchmarkConfigBase(xms, xmx, timeout, runs, queryTransformationCount, modelPath, operationsSemaphoreNeighbor, "SemaphoreNeighbor")
			if (BenchmarkRunner.run(new IqdCoreBenchmarkConfig(bc, messageSize, variant, null)) == 143) {
				break
			}
		}
	}
}
