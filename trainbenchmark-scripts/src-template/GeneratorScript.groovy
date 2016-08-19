import hu.bme.mit.trainbenchmark.constants.Scenario
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfigCore
import hu.bme.mit.trainbenchmark.generator.emf.config.EmfGeneratorConfigWrapper
import hu.bme.mit.trainbenchmark.generator.graph.neo4j.config.Neo4jGraphGeneratorConfigWrapper
import hu.bme.mit.trainbenchmark.generator.graph.tinkerpop.config.TinkerGraphFormat
import hu.bme.mit.trainbenchmark.generator.graph.tinkerpop.config.TinkerGraphGeneratorConfigWrapper
import hu.bme.mit.trainbenchmark.generator.rdf.config.RdfGeneratorConfigWrapper
import hu.bme.mit.trainbenchmark.generator.runner.GeneratorRunner
import hu.bme.mit.trainbenchmark.generator.sql.config.SqlGeneratorConfigWrapper
import hu.bme.mit.trainbenchmark.rdf.RdfFormat

def xms = "4G"
def xmx = "8G"
def minSize = 1
def maxSize = 8

def scenarios = [
//	Scenario.BATCH,
//	Scenario.INJECT,
	Scenario.REPAIR,
]

def generate(String xms, String xmx, Scenario scenario, int size) {
	def gcc = new GeneratorConfigCore(xms, xmx, scenario, size)

	// EMF
	def egcw = new EmfGeneratorConfigWrapper(gcc)
	GeneratorRunner.run(egcw)

	// graph / Neo4j
	def ngcw = new Neo4jGraphGeneratorConfigWrapper(gcc)
	GeneratorRunner.run(ngcw)

	// graph / TinkerPop
	def tgcw = new TinkerGraphGeneratorConfigWrapper(gcc, TinkerGraphFormat.GRAPHML)
	GeneratorRunner.run(tgcw)

	// RDF
	def rdfFormats = [RdfFormat.TURTLE]
	def includeInferredOptions = [true, false]
	for (rdfFormat in rdfFormats) {
		for (includeInferredOption in includeInferredOptions) {
			def rgcw = new RdfGeneratorConfigWrapper(gcc, includeInferredOption, rdfFormat)
			GeneratorRunner.run(rgcw)
		}
	}

	// SQL
	def sgcw = new SqlGeneratorConfigWrapper(gcc)
	GeneratorRunner.run(sgcw)
}

for (scenario in scenarios) {
	for (size = minSize; size <= maxSize; size *= 2) {
		println("Scenario: ${scenario}, size: ${size}")
		generate(xms, xmx, scenario, size)
	}
}
