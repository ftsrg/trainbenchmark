import hu.bme.mit.trainbenchmark.benchmark.blazegraph.config.BlazegraphBenchmarkConfig
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBase
import hu.bme.mit.trainbenchmark.benchmark.config.TransformationChangeSetStrategy;
import hu.bme.mit.trainbenchmark.benchmark.drools.config.DroolsBenchmarkConfig
import hu.bme.mit.trainbenchmark.benchmark.eclipseocl.config.EclipseOclBenchmarkConfig
import hu.bme.mit.trainbenchmark.benchmark.emfapi.config.EmfApiBenchmarkConfig
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.config.IqdCoreBenchmarkConfig
import hu.bme.mit.trainbenchmark.benchmark.jena.config.JenaBenchmarkConfig
import hu.bme.mit.trainbenchmark.benchmark.mysql.config.MySqlBenchmarkConfig
import hu.bme.mit.trainbenchmark.benchmark.neo4j.config.Neo4jBenchmarkConfig
import hu.bme.mit.trainbenchmark.benchmark.neo4j.config.Neo4jEngine
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.config.Rdf4jBenchmarkConfig
import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkReporter
import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkRunner
import hu.bme.mit.trainbenchmark.benchmark.sqlite.config.SQLiteBenchmarkConfig
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.config.TinkerGraphBenchmarkConfig
import hu.bme.mit.trainbenchmark.benchmark.viatra.config.ViatraBackend
import hu.bme.mit.trainbenchmark.benchmark.viatra.config.ViatraBenchmarkConfig
import hu.bme.mit.trainbenchmark.constants.RailwayOperation

def initialMemory = 12800
def maxMemory = 12800
def minSize = 1
def maxSize = 8
def timeout = 900
def runs = 3
def queryTransformationCount = 5

def operations = [
        RailwayOperation.POSLENGTH_REPAIR,
        RailwayOperation.ROUTESENSOR,
        RailwayOperation.SWITCHMONITORED_REPAIR
]

def workload = "Repair"

for (size = minSize; size <= maxSize; size *= 2) {
    def modelFilename = "railway-repair-${size}"

    def bcb = new BenchmarkConfigBase(initialMemory, maxMemory, timeout, runs, queryTransformationCount, modelFilename, operations, workload, TransformationChangeSetStrategy.FIXED, 10)

    BenchmarkRunner.run(new BlazegraphBenchmarkConfig(bcb, false))
    BenchmarkRunner.run(new BlazegraphBenchmarkConfig(bcb, true))
    BenchmarkRunner.run(new EclipseOclBenchmarkConfig(bcb))
    BenchmarkRunner.run(new DroolsBenchmarkConfig(bcb))
    BenchmarkRunner.run(new EmfApiBenchmarkConfig(bcb))
    BenchmarkRunner.run(new IqdCoreBenchmarkConfig(bcb, 16, "", null))
    BenchmarkRunner.run(new JenaBenchmarkConfig(bcb, false))
    BenchmarkRunner.run(new JenaBenchmarkConfig(bcb, true))
    BenchmarkRunner.run(new MySqlBenchmarkConfig(bcb))
    BenchmarkRunner.run(new Neo4jBenchmarkConfig(bcb, Neo4jEngine.COREAPI))
    BenchmarkRunner.run(new Neo4jBenchmarkConfig(bcb, Neo4jEngine.CYPHER))
    BenchmarkRunner.run(new Rdf4jBenchmarkConfig(bcb, false))
    BenchmarkRunner.run(new SQLiteBenchmarkConfig(bcb))
    BenchmarkRunner.run(new TinkerGraphBenchmarkConfig(bcb))
    BenchmarkRunner.run(new ViatraBenchmarkConfig(bcb, ViatraBackend.INCREMENTAL))
    BenchmarkRunner.run(new ViatraBenchmarkConfig(bcb, ViatraBackend.LOCAL_SEARCH))
}

BenchmarkReporter.reportReady()
