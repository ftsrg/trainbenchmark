import static hu.bme.mit.trainbenchmark.constants.RailwayOperation.*
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigCore
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.config.IQDBenchmarkConfigWrapper
import hu.bme.mit.trainbenchmark.benchmark.runner.BenchmarkRunner

def xms = "2G"
def xmx = "4G"
def modelPath = "railway-repair-1"
def railwayOperations = [
	POSLENGTH_REPAIR,
	SWITCHMONITORED,
]
def timeout = 120
def runs = 1
def queryTransformationCount = 1
def bc = new BenchmarkConfigCore(xms, xmx, timeout, runs, queryTransformationCount, modelPath, railwayOperations, "Batch")

//		BenchmarkRunner.run(new BlazegraphBenchmarkConfigWrapper(bc, false))
//		BenchmarkRunner.run(new EclipseOclBenchmarkConfigWrapper(bc))
//		BenchmarkRunner.run(new Drools5BenchmarkConfigWrapper(bc))
//		BenchmarkRunner.run(new Drools6BenchmarkConfigWrapper(bc))
//		BenchmarkRunner.run(new EmfApiBenchmarkConfigWrapper(bc))
//		BenchmarkRunner.run(new SesameBenchmarkConfigWrapper(bc, false))
BenchmarkRunner.run(new IQDBenchmarkConfigWrapper(bc, 10, ""))
//		BenchmarkRunner.run(new JenaBenchmarkConfigWrapper(bc, false))
//		BenchmarkRunner.run(new MySqlBenchmarkConfigWrapper(bc))
//		BenchmarkRunner.run(new Neo4jBenchmarkConfigWrapper(bc, Neo4jEngine.COREAPI))
//		BenchmarkRunner.run(new Neo4jBenchmarkConfigWrapper(bc, Neo4jEngine.CYPHER))
//		BenchmarkRunner.run(new SQLiteBenchmarkConfigWrapper(bc))
//		BenchmarkRunner.run(new TinkerGraphBenchmarkConfigWrapper(bc))
//		BenchmarkRunner.run(new ViatraBenchmarkConfigWrapper(bc))
