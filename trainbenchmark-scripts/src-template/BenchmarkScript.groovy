import BenchmarkReporter
import hu.bme.mit.trainbenchmark.benchmark.blazegraph.config.BlazegraphBenchmarkConfigWrapper
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigCore
import hu.bme.mit.trainbenchmark.benchmark.drools.config.DroolsBenchmarkConfigWrapper
import hu.bme.mit.trainbenchmark.benchmark.eclipseocl.config.EclipseOclBenchmarkConfigWrapper
import hu.bme.mit.trainbenchmark.benchmark.emfapi.config.EmfApiBenchmarkConfigWrapper
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.config.IqdCoreBenchmarkConfigWrapper
import hu.bme.mit.trainbenchmark.benchmark.jena.config.JenaBenchmarkConfigWrapper
import hu.bme.mit.trainbenchmark.benchmark.mysql.config.MySqlBenchmarkConfigWrapper
import hu.bme.mit.trainbenchmark.benchmark.neo4j.config.Neo4jBenchmarkConfigWrapper
import hu.bme.mit.trainbenchmark.benchmark.neo4j.config.Neo4jEngine
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.config.Rdf4jBenchmarkConfigWrapper
import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkRunner
import hu.bme.mit.trainbenchmark.benchmark.sqlite.config.SQLiteBenchmarkConfigWrapper
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.config.TinkerGraphBenchmarkConfigWrapper
import hu.bme.mit.trainbenchmark.benchmark.viatra.config.ViatraBenchmarkConfigWrapper
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
	
    BenchmarkRunner.run(new BlazegraphBenchmarkConfigWrapper(bcc, false))
    BenchmarkRunner.run(new BlazegraphBenchmarkConfigWrapper(bcc, true))
    BenchmarkRunner.run(new EclipseOclBenchmarkConfigWrapper(bcc))
    BenchmarkRunner.run(new DroolsBenchmarkConfigWrapper(bcc))
    BenchmarkRunner.run(new EmfApiBenchmarkConfigWrapper(bcc))
	BenchmarkRunner.run(new IqdCoreBenchmarkConfigWrapper(bcc, 16, "", null))
    BenchmarkRunner.run(new JenaBenchmarkConfigWrapper(bcc, false))
    BenchmarkRunner.run(new JenaBenchmarkConfigWrapper(bcc, true))
    BenchmarkRunner.run(new MySqlBenchmarkConfigWrapper(bcc))
    BenchmarkRunner.run(new Neo4jBenchmarkConfigWrapper(bcc, Neo4jEngine.COREAPI))
    BenchmarkRunner.run(new Neo4jBenchmarkConfigWrapper(bcc, Neo4jEngine.CYPHER))
    BenchmarkRunner.run(new Rdf4jBenchmarkConfigWrapper(bcc, false))
    BenchmarkRunner.run(new SQLiteBenchmarkConfigWrapper(bcc))
    BenchmarkRunner.run(new TinkerGraphBenchmarkConfigWrapper(bcc))
    BenchmarkRunner.run(new ViatraBenchmarkConfigWrapper(bcc))
}

BenchmarkReporter.reportReady()
