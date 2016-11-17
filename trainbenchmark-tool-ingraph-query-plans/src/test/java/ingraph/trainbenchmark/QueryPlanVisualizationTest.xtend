package ingraph.trainbenchmark

import ingraph.optimization.transformations.relalg2rete.Relalg2ReteTransformation
import ingraph.relalg2tex.RelalgExpressionSerializer
import ingraph.relalg2tex.RelalgSerializerConfig
import ingraph.relalg2tex.RelalgTreeSerializer
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameter
import org.junit.runners.Parameterized.Parameters
import relalg.RelalgContainer

@RunWith(Parameterized)
class QueryPlanVisualizationTest {

	@Parameters(name="plan={0}")
	static def Iterable<? extends Object> data() {
		#[
			new RouteSensorQueryPlanFactory().routeSensorA,
			new RouteSensorQueryPlanFactory().routeSensorB,
			new RouteSensorQueryPlanFactory().routeSensorC,
			new SemaphoreNeighborQueryPlanFactory().semaphoreNeighborA,
			new SemaphoreNeighborQueryPlanFactory().semaphoreNeighborB,
			new SemaphoreNeighborQueryPlanFactory().semaphoreNeighborC,
			new SemaphoreNeighborQueryPlanFactory().semaphoreNeighborD,
			new SemaphoreNeighborQueryPlanFactory().semaphoreNeighborE,
			new SemaphoreNeighborQueryPlanFactory().semaphoreNeighborF
		];
	}

	@Parameter
	public RelalgContainer plan

	val treeConfig = new RelalgSerializerConfig => [standaloneDocument = true]
	val expressionConfig = new RelalgSerializerConfig => [standaloneDocument = false]

	val extension RelalgTreeSerializer treeSerializer = new RelalgTreeSerializer(treeConfig)
	val extension RelalgExpressionSerializer expressionSerializer = new RelalgExpressionSerializer(expressionConfig)

	val extension Relalg2ReteTransformation transformation = new Relalg2ReteTransformation

	@Test
	def void serialize() {
		val name = plan.name
		treeSerializer.serialize(plan, '''query-plans/«name»-Relalg''')
		treeSerializer.serialize(plan.transformToRete, '''query-plans/«name»-Rete''')
		println('''
			\begin{align*}
			\uline{«name.substring(name.length - 1)»:}
			«expressionSerializer.serialize(plan.transformToRete, '''query-plans/«name»-ReteExpression''')»
			\end{align*}
		''')
	}

}
