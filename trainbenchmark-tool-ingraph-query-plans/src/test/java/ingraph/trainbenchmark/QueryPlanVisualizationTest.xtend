package ingraph.trainbenchmark

import com.google.common.collect.ImmutableMap
import ingraph.optimization.transformations.relalg2rete.Relalg2ReteTransformation
import ingraph.relalg2tex.RelalgExpressionSerializer
import ingraph.relalg2tex.RelalgTreeSerializer
import java.util.Map
import org.junit.Test
import relalg.RelalgContainer

class QueryPlanVisualizationTest {

	val extension RelalgTreeSerializer relalgTreeDrawer = new RelalgTreeSerializer
	val extension RelalgExpressionSerializer expressionSerializer = new RelalgExpressionSerializer(false, false, false)
	val extension Relalg2ReteTransformation transformation = new Relalg2ReteTransformation
	val extension RouteSensorQueryPlanFactory routeSensorFactory = new RouteSensorQueryPlanFactory
	val extension SemaphoreNeighborQueryPlanFactory semaphoreNeighborFactory = new SemaphoreNeighborQueryPlanFactory()

	val Map<String, RelalgContainer> queryPlans = ImmutableMap.builder //
		.put("RouteSensorA", routeSensorA) //
		.put("RouteSensorB", routeSensorB) //
		.put("RouteSensorC", routeSensorB) //
		.put("SemaphoreNeighborA", semaphoreNeighborA) //
		.put("SemaphoreNeighborB", semaphoreNeighborB) //
		.put("SemaphoreNeighborC", semaphoreNeighborC) //
		.put("SemaphoreNeighborD", semaphoreNeighborD) //
		.put("SemaphoreNeighborE", semaphoreNeighborE) //
		.put("SemaphoreNeighborF", semaphoreNeighborF) //
		.build

	@Test
	def void serialize() {
		queryPlans.forEach [ name, plan |
			relalgTreeDrawer.serialize(plan, '''query-plans/«name»-Relalg''')
			relalgTreeDrawer.serialize(plan.transformToRete, '''query-plans/«name»-Rete''')
			expressionSerializer.serialize(plan.transformToRete, '''query-plans/«name»-ReteExpression''')
		]
	}

}
