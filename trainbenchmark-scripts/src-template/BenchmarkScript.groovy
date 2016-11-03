import hu.bme.mit.trainbenchmark.benchmark.blazegraph.config.BlazegraphBenchmarkConfig
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBase
import hu.bme.mit.trainbenchmark.benchmark.config.TransformationChangeSetStrategy
import hu.bme.mit.trainbenchmark.benchmark.drools.config.DroolsBenchmarkConfig
import hu.bme.mit.trainbenchmark.benchmark.eclipseocl.config.EclipseOclBenchmarkConfig
import hu.bme.mit.trainbenchmark.benchmark.emfapi.config.EmfApiBenchmarkConfig
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.config.IqdCoreBenchmarkConfig
import hu.bme.mit.trainbenchmark.benchmark.jena.config.JenaBenchmarkConfig
import hu.bme.mit.trainbenchmark.benchmark.mysql.config.MySqlBenchmarkConfig
import hu.bme.mit.trainbenchmark.benchmark.neo4j.config.Neo4jBenchmarkConfig
import hu.bme.mit.trainbenchmark.benchmark.neo4j.config.Neo4jEngine
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.config.Rdf4jBenchmarkConfig
import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkRunner
import hu.bme.mit.trainbenchmark.benchmark.sqlite.config.SQLiteBenchmarkConfig
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.config.TinkerGraphBenchmarkConfig
import hu.bme.mit.trainbenchmark.benchmark.viatra.config.ViatraBackend
import hu.bme.mit.trainbenchmark.benchmark.viatra.config.ViatraBenchmarkConfig
import hu.bme.mit.trainbenchmark.config.ExecutionConfig
import hu.bme.mit.trainbenchmark.constants.RailwayOperation

def ec = new ExecutionConfig(1000, 1000)

def minSize = 1
def maxSize = 8
def timeout = 900
def runs = 3
def queryTransformationCount = 5

def operations = [
	RailwayOperation.CONNECTEDSEGMENTS_REPAIR,
	RailwayOperation.POSLENGTH_REPAIR,
	RailwayOperation.ROUTESENSOR_REPAIR,
	RailwayOperation.SEMAPHORENEIGHBOR_REPAIR,
	RailwayOperation.SWITCHMONITORED_REPAIR,
	RailwayOperation.SWITCHSET_REPAIR,
]

def workload = "Repair"

for (size = minSize; size <= maxSize; size *= 2) {
	def modelFilename = "railway-repair-${size}"

	def bcb = new BenchmarkConfigBase(timeout, runs, queryTransformationCount, modelFilename, operations, workload, TransformationChangeSetStrategy.FIXED, 10)

	BenchmarkRunner.run(new BlazegraphBenchmarkConfig(bcb, ec, false))
	BenchmarkRunner.run(new BlazegraphBenchmarkConfig(bcb, ec, true))
	BenchmarkRunner.run(new EclipseOclBenchmarkConfig(bcb, ec))
	BenchmarkRunner.run(new DroolsBenchmarkConfig(bcb, ec))
	BenchmarkRunner.run(new EmfApiBenchmarkConfig(bcb, ec))
	BenchmarkRunner.run(new IqdCoreBenchmarkConfig(bcb, ec, 16, "", null))
	BenchmarkRunner.run(new JenaBenchmarkConfig(bcb, ec, false))
	BenchmarkRunner.run(new JenaBenchmarkConfig(bcb, ec, true))
	BenchmarkRunner.run(new MySqlBenchmarkConfig(bcb, ec))
	BenchmarkRunner.run(new Neo4jBenchmarkConfig(bcb, ec, Neo4jEngine.COREAPI))
	BenchmarkRunner.run(new Neo4jBenchmarkConfig(bcb, ec, Neo4jEngine.CYPHER))
	BenchmarkRunner.run(new Rdf4jBenchmarkConfig(bcb, ec, false))
	BenchmarkRunner.run(new SQLiteBenchmarkConfig(bcb, ec))
	BenchmarkRunner.run(new TinkerGraphBenchmarkConfig(bcb, ec))
	BenchmarkRunner.run(new ViatraBenchmarkConfig(bcb, ec, ViatraBackend.INCREMENTAL))
	BenchmarkRunner.run(new ViatraBenchmarkConfig(bcb, ec, ViatraBackend.LOCAL_SEARCH))
}

//BenchmarkReporter.reportReady()
