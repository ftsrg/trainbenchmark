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

def generate(String xms, String xmx, Scenario scenario, int size) {
	def gc = new GeneratorConfigBase(xms, xmx, scenario, size)

	// EMF
	def egc = new EmfGeneratorConfig(gc, ec)
	GeneratorRunner.run(egc)

	// graph / Neo4j
	def ngc = new Neo4jGraphGeneratorConfig(gc, ec)
	GeneratorRunner.run(ngc)

	// graph / TinkerPop
	def tgc = new TinkerGraphGeneratorConfig(gc, ec, TinkerGraphFormat.GRAPHML)
	GeneratorRunner.run(tgc)

	// RDF
	def rdfFormats = [RdfFormat.TURTLE]
	def includeInferredOptions = [true, false]
	for (rdfFormat in rdfFormats) {
		for (includeInferredOption in includeInferredOptions) {
			def rgc = new RdfGeneratorConfig(gc, ec, includeInferredOption, rdfFormat)
			GeneratorRunner.run(rgc)
		}
	}

	// SQL
	def sgc = new SqlGeneratorConfig(gc, ec)
	GeneratorRunner.run(sgc)
}

for (scenario in scenarios) {
	for (size = minSize; size <= maxSize; size *= 2) {
		println("Scenario: ${scenario}, size: ${size}")
		generate(xms, xmx, scenario, size)
	}
}
