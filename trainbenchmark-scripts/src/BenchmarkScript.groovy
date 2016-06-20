import static hu.bme.mit.trainbenchmark.constants.RailwayOperation.*
import hu.bme.mit.trainbenchmark.benchmark.blazegraph.config.BlazegraphBenchmarkConfigWrapper
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigCore
import hu.bme.mit.trainbenchmark.benchmark.drools5.config.Drools5BenchmarkConfigWrapper
import hu.bme.mit.trainbenchmark.benchmark.drools6.config.Drools6BenchmarkConfigWrapper
import hu.bme.mit.trainbenchmark.benchmark.eclipseocl.config.EclipseOclBenchmarkConfigWrapper
import hu.bme.mit.trainbenchmark.benchmark.emfapi.config.EmfApiBenchmarkConfigWrapper
import hu.bme.mit.trainbenchmark.benchmark.jena.config.JenaBenchmarkConfigWrapper
import hu.bme.mit.trainbenchmark.benchmark.mysql.config.MySqlBenchmarkConfigWrapper
import hu.bme.mit.trainbenchmark.benchmark.neo4j.config.Neo4jBenchmarkConfigWrapper
import hu.bme.mit.trainbenchmark.benchmark.neo4j.config.Neo4jEngine
import hu.bme.mit.trainbenchmark.benchmark.runner.BenchmarkRunner
import hu.bme.mit.trainbenchmark.benchmark.sesame.config.SesameBenchmarkConfigWrapper
import hu.bme.mit.trainbenchmark.benchmark.sqlite.config.SQLiteBenchmarkConfigWrapper
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.config.TinkerGraphBenchmarkConfigWrapper
import hu.bme.mit.trainbenchmark.benchmark.viatra.config.ViatraBenchmarkConfigWrapper

import org.codehaus.groovy.runtime.InvokerHelper

class BenchmarkScript extends Script {

	def run() {
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

		BenchmarkRunner.run(new BlazegraphBenchmarkConfigWrapper(bc, false))
		BenchmarkRunner.run(new EclipseOclBenchmarkConfigWrapper(bc))
		BenchmarkRunner.run(new Drools5BenchmarkConfigWrapper(bc))
		BenchmarkRunner.run(new Drools6BenchmarkConfigWrapper(bc))
		BenchmarkRunner.run(new EmfApiBenchmarkConfigWrapper(bc))
		BenchmarkRunner.run(new SesameBenchmarkConfigWrapper(bc, false))
		BenchmarkRunner.run(new JenaBenchmarkConfigWrapper(bc, false))
		BenchmarkRunner.run(new MySqlBenchmarkConfigWrapper(bc))
		BenchmarkRunner.run(new Neo4jBenchmarkConfigWrapper(bc, Neo4jEngine.COREAPI))
		BenchmarkRunner.run(new Neo4jBenchmarkConfigWrapper(bc, Neo4jEngine.CYPHER))
		BenchmarkRunner.run(new SQLiteBenchmarkConfigWrapper(bc))
		BenchmarkRunner.run(new TinkerGraphBenchmarkConfigWrapper(bc))
		BenchmarkRunner.run(new ViatraBenchmarkConfigWrapper(bc))
	}
	
	public static void main(String[] args) {
		InvokerHelper.runScript(BenchmarkScript, args);
	}

}
