import hu.bme.mit.trainbenchmark.config.ExecutionConfig
import hu.bme.mit.trainbenchmark.constants.Scenario
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfigBase
import hu.bme.mit.trainbenchmark.generator.emf.config.EmfGeneratorConfigBuilder
import hu.bme.mit.trainbenchmark.generator.graph.neo4j.config.Neo4jGraphGeneratorConfigBuilder
import hu.bme.mit.trainbenchmark.generator.graph.tinkerpop.config.TinkerGraphGeneratorConfigBuilder
import hu.bme.mit.trainbenchmark.generator.rdf.config.RdfGeneratorConfigBuilder
import hu.bme.mit.trainbenchmark.generator.runner.GeneratorRunner
import hu.bme.mit.trainbenchmark.generator.sql.config.SqlGeneratorConfigBuilder
import hu.bme.mit.trainbenchmark.rdf.RdfFormat

def ec = new ExecutionConfig(4000, 8000)
def minSize = 1
def maxSize = 256

def scenarios = [
	Scenario.BATCH,
	Scenario.INJECT,
	Scenario.REPAIR,
]

def formats = [
	new EmfGeneratorConfigBuilder(),
	new Neo4jGraphGeneratorConfigBuilder(),
	new TinkerGraphGeneratorConfigBuilder(),
	new RdfGeneratorConfigBuilder().setFormat(RdfFormat.TURTLE).setInferencing(true),
	new RdfGeneratorConfigBuilder().setFormat(RdfFormat.TURTLE).setInferencing(false),
	new SqlGeneratorConfigBuilder(),
]

for (scenario in scenarios) {
	formats.each { generatorConfigBuilder ->
		for (def size = minSize; size <= maxSize; size *= 2) {
			println("Scenario: ${scenario}, size: ${size}")

			def configBase = new GeneratorConfigBase(scenario, size)
			def config = generatorConfigBuilder.setConfigBase(configBase).createConfig()

			def exitValue = GeneratorRunner.run(config, ec)
			if (exitValue != 0) {
				println "Timeout or error occured, skipping models for larger sizes. Error code: {$exitValue}"
				break
			}
		}
	}
}
