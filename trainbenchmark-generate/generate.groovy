import hu.bme.mit.trainbenchmark.constants.ScenarioEnum

def minSize = 1
def maxSize = 16

def railwayOperations = [
	"emf",
	"graph-neo4j",
	"graph-tinkerpop",
	"rdf",
	"sql"
]
def scenarios = [
	ScenarioEnum.BATCH,
	ScenarioEnum.INJECT,
	ScenarioEnum.REPAIR,
]

//two for RDF
for (scenario in scenarios) {
	for (size = minSize; size <= maxSize; size*=2) {
		println(scenario.toString() + " " + size)
	}
}