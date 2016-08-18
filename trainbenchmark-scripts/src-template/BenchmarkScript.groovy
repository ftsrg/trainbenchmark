import BenchmarkReporter
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigCore

import static hu.bme.mit.trainbenchmark.constants.RailwayOperation.*

def xms = "12G"
def xmx = "12G"
def minSize = 1
def maxSize = 2
def timeout = 900
def runs = 3
def queryTransformationCount = 5

def operations = [
	RailwayOperation.POSLENGTH_REPAIR,
	RailwayOperation.ROUTESENSOR,
	RailwayOperation.SWITCHMONITORED_REPAIR
]

def bcc = new BenchmarkConfigCore()

//	BenchmarkRunner.run(new BlazegraphBenchmarkConfigWrapper(bcc, false))
//	BenchmarkRunner.run(new EclipseOclBenchmarkConfigWrapper(bcc))
//	BenchmarkRunner.run(new DroolsBenchmarkConfigWrapper(bcc))
//	BenchmarkRunner.run(new EmfApiBenchmarkConfigWrapper(bcc))
//	BenchmarkRunner.run(new JenaBenchmarkConfigWrapper(bcc, false))
//	BenchmarkRunner.run(new MySqlBenchmarkConfigWrapper(bcc))
//	BenchmarkRunner.run(new Neo4jBenchmarkConfigWrapper(bcc, Neo4jEngine.COREAPI))
//	BenchmarkRunner.run(new Neo4jBenchmarkConfigWrapper(bcc, Neo4jEngine.CYPHER))
//	BenchmarkRunner.run(new Rdf4jBenchmarkConfigWrapper(bcc, false))
//	BenchmarkRunner.run(new SQLiteBenchmarkConfigWrapper(bcc))
//	BenchmarkRunner.run(new TinkerGraphBenchmarkConfigWrapper(bcc))
//	BenchmarkRunner.run(new ViatraBenchmarkConfigWrapper(bcc))

}

BenchmarkReporter.reportReady()
