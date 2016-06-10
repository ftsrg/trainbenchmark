import hu.bme.mit.trainbenchmark.constants.Scenario

def minSize = 1
def maxSize = 16

def scenarios = [
	Scenario.BATCH,
	Scenario.INJECT,
	Scenario.REPAIR,
]

def generate(Scenario scenario) {
	def generators = [
		"emf",
		"graph-neo4j",
		"graph-tinkerpop",
		"sql"
	]
	
		
}

for (scenario in scenarios) {
	for (size = minSize; size <= maxSize; size*=2) {
		generate(scenario, size)
	}
}