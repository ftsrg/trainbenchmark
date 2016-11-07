import hu.bme.mit.trainbenchmark.config.ExecutionConfig
import hu.bme.mit.trainbenchmark.constants.Scenario
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfigBase
import hu.bme.mit.trainbenchmark.generator.emf.config.EmfGeneratorConfig
import hu.bme.mit.trainbenchmark.generator.graph.neo4j.config.Neo4jGraphGeneratorConfig
import hu.bme.mit.trainbenchmark.generator.graph.tinkerpop.config.TinkerGraphFormat
import hu.bme.mit.trainbenchmark.generator.graph.tinkerpop.config.TinkerGraphGeneratorConfig
import hu.bme.mit.trainbenchmark.generator.rdf.config.RdfGeneratorConfig
import hu.bme.mit.trainbenchmark.generator.runner.GeneratorRunner
import hu.bme.mit.trainbenchmark.generator.sql.config.SqlGeneratorConfig
import hu.bme.mit.trainbenchmark.rdf.RdfFormat

def ec = new ExecutionConfig(4000, 8000)
def minSize = 1
def maxSize = 16

def scenarios = [
	Scenario.BATCH,
	Scenario.INJECT,
	Scenario.REPAIR,
]

def generate(ExecutionConfig ec, Scenario scenario, int size) {
	def gc = new GeneratorConfigBase(scenario, size)

	// EMF
	def egc = new EmfGeneratorConfig(gc)
	GeneratorRunner.run(egc, ec)

	// graph / Neo4j
	def ngc = new Neo4jGraphGeneratorConfig(gc)
	GeneratorRunner.run(ngc, ec)

	// graph / TinkerPop
	def tgc = new TinkerGraphGeneratorConfig(gc, TinkerGraphFormat.GRAPHML)
	GeneratorRunner.run(tgc, ec)

	// RDF
	def rdfFormats = [RdfFormat.TURTLE]
	def includeInferredOptions = [true, false]
	for (rdfFormat in rdfFormats) {
		for (includeInferredOption in includeInferredOptions) {
			def rgc = new RdfGeneratorConfig(gc, includeInferredOption, rdfFormat)
			GeneratorRunner.run(rgc, ec)
		}
	}

	// SQL
	def sgc = new SqlGeneratorConfig(gc)
	GeneratorRunner.run(sgc, ec)
}

for (scenario in scenarios) {
	for (size = minSize; size <= maxSize; size *= 2) {
		println("Scenario: ${scenario}, size: ${size}")
		generate(ec, scenario, size)
	}
}
