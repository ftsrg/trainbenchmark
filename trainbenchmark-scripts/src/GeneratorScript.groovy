import hu.bme.mit.trainbenchmark.constants.Scenario
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfigWrapper
import hu.bme.mit.trainbenchmark.generator.rdf.config.RdfGeneratorConfigWrapper
import hu.bme.mit.trainbenchmark.generator.runner.GeneratorRunner
import hu.bme.mit.trainbenchmark.rdf.RdfFormat



def generate(String xms, String xmx, Scenario scenario, int size) {
	def gc = new GeneratorConfig(xms, xmx, scenario, size)

//	// EMF
//	def egcw = new GeneratorConfigWrapper(gc)
//	GeneratorRunner.run(egcw, "emf")
//
//	// graph / Neo4j
//	def gngcw = new GeneratorConfigWrapper(gc)
//	GeneratorRunner.run(gngcw, "graph-neo4j")
//
//	// graph / TinkerPop
//	def gtgcw = new TinkerPopGraphGeneratorConfigWrapper(gc, GraphFormat.GRAPHML)
//	GeneratorRunner.run(gtgcw, "graph-tinkerpop")

	// RDF
	def rdfFormats = [RdfFormat.TURTLE]
	def inferencingOptions = [true, false]
	for (rdfFormat in rdfFormats) {
		for (inferencingOption in inferencingOptions) {
			def rgcw = new RdfGeneratorConfigWrapper(gc, inferencingOption, rdfFormat)
			GeneratorRunner.run(rgcw, "rdf")
		}
	}

	// SQL
	def sgcw = new GeneratorConfigWrapper(gc)
	//	GeneratorRunner.run(sgcw, "sql")
}


def xms = "2G"
def xmx = "4G"
def minSize = 1
def maxSize = 2

def scenarios = [
	Scenario.BATCH,
	Scenario.INJECT,
	Scenario.REPAIR,
]

for (scenario in scenarios) {
	for (size = minSize; size <= maxSize; size*=2) {
		println("Scenario: ${scenario}, size: ${size}")
		generate(xms, xmx, scenario, size)
	}
}

