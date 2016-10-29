import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkReporter
import hu.bme.mit.trainbenchmark.benchmark.blazegraph.config.BlazegraphBenchmarkConfig
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigCore
import hu.bme.mit.trainbenchmark.benchmark.drools.config.DroolsBenchmarkConfig
import hu.bme.mit.trainbenchmark.benchmark.eclipseocl.config.EclipseOclBenchmarkConfig
import hu.bme.mit.trainbenchmark.benchmark.epsilon.config.EpsilonBenchmarkConfig
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
import hu.bme.mit.trainbenchmark.benchmark.viatra.config.ViatraBenchmarkConfig
import hu.bme.mit.trainbenchmark.benchmark.viatra.config.ViatraBackend
import hu.bme.mit.trainbenchmark.constants.RailwayOperation

def xms = "12G"
def xmx = "12G"
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

    def bcc = new BenchmarkConfigCore(xms, xmx, timeout, runs, queryTransformationCount, modelFilename, operations, workload)

    BenchmarkRunner.run(new BlazegraphBenchmarkConfig(bcc, false))
    BenchmarkRunner.run(new BlazegraphBenchmarkConfig(bcc, true))
    BenchmarkRunner.run(new EclipseOclBenchmarkConfig(bcc))
    BenchmarkRunner.run(new DroolsBenchmarkConfig(bcc))
    BenchmarkRunner.run(new EmfApiBenchmarkConfig(bcc))
    BenchmarkRunner.run(new IqdCoreBenchmarkConfig(bcc, 16, "", null))
    BenchmarkRunner.run(new JenaBenchmarkConfig(bcc, false))
    BenchmarkRunner.run(new JenaBenchmarkConfig(bcc, true))
    BenchmarkRunner.run(new MySqlBenchmarkConfig(bcc))
    BenchmarkRunner.run(new Neo4jBenchmarkConfig(bcc, Neo4jEngine.COREAPI))
    BenchmarkRunner.run(new Neo4jBenchmarkConfig(bcc, Neo4jEngine.CYPHER))
    BenchmarkRunner.run(new Rdf4jBenchmarkConfig(bcc, false))
    BenchmarkRunner.run(new SQLiteBenchmarkConfig(bcc))
    BenchmarkRunner.run(new TinkerGraphBenchmarkConfig(bcc))
    BenchmarkRunner.run(new ViatraBenchmarkConfig(bcc, ViatraBackend.INCREMENTAL))
    BenchmarkRunner.run(new ViatraBenchmarkConfig(bcc, ViatraBackend.LOCAL_SEARCH))
}

BenchmarkReporter.reportReady()
