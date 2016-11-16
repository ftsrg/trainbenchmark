package ingraph.trainbenchmark

import java.util.Arrays
import relalg.ArithmeticComparisonOperator
import relalg.Direction

class SemaphoreNeighborQueryPlanFactory extends QueryPlanFactory {

	// container
	val semaphoreNeighbor = createRelalgContainer

	// vertex labels
	val routeLabel = createVertexLabel => [name = "Route"]
	val semaphoreLabel = createVertexLabel => [name = "Semaphore"]
	val sensorLabel = createVertexLabel => [name = "Sensor"]
	val teLabel = createVertexLabel => [name = "TrackElement"]

	// edge labels
	val connectsToLabel = createEdgeLabel => [name = "connectsTo"]
	val entryLabel = createEdgeLabel => [name = "entry"]
	val exitLabel = createEdgeLabel => [name = "exit"]
	val requiresLabel = createEdgeLabel => [name = "requires"]
	val monitoredByLabel = createEdgeLabel => [name = "monitoredBy"]

	// vertex variables
	val route1 = createVertexVariable => [name = "route1"; vertexLabels.add(routeLabel)]
	val route2 = createVertexVariable => [name = "route2"; vertexLabels.add(routeLabel)]
	val semaphore = createVertexVariable => [
		name = "semaphore";
		vertexLabels.add(semaphoreLabel);
		container = semaphoreNeighbor
	]
	val sensor1 = createVertexVariable => [
		name = "sensor1";
		vertexLabels.add(sensorLabel);
		container = semaphoreNeighbor
	]
	val sensor2 = createVertexVariable => [
		name = "sensor2";
		vertexLabels.add(sensorLabel);
		container = semaphoreNeighbor
	]
	val te1 = createVertexVariable => [name = "te1"; vertexLabels.add(teLabel)]
	val te2 = createVertexVariable => [name = "te2"; vertexLabels.add(teLabel)]

	// edge variables
	val requires1 = createEdgeVariable => [name = "g1"; edgeLabels.add(requiresLabel)]
	val requires2 = createEdgeVariable => [name = "g2"; edgeLabels.add(requiresLabel)]
	val monitoredBy1 = createEdgeVariable => [
		name = "mb1";
		edgeLabels.add(monitoredByLabel);
		container = semaphoreNeighbor
	]
	val monitoredBy2 = createEdgeVariable => [
		name = "mb2";
		edgeLabels.add(monitoredByLabel);
		container = semaphoreNeighbor
	]
	val connectsTo = createEdgeVariable => [name = "ct"; edgeLabels.add(connectsToLabel)]
	val entry = createEdgeVariable => [name = "entry"; edgeLabels.add(entryLabel)]
	val exit = createEdgeVariable => [name = "exit"; edgeLabels.add(exitLabel)]

	// inputs
	val route1s = createGetVerticesOperator => [vertexVariable = route1]
	val route1sA = createGetVerticesOperator => [vertexVariable = route1]
	val route1sB = createGetVerticesOperator => [vertexVariable = route1]
	val route2sA = createGetVerticesOperator => [vertexVariable = route2]
	val route2sB = createGetVerticesOperator => [vertexVariable = route2]
	val route2sC = createGetVerticesOperator => [vertexVariable = route2]
	val te1s = createGetVerticesOperator => [vertexVariable = te1]
	val te1sA = createGetVerticesOperator => [vertexVariable = te1]
	val te1sB = createGetVerticesOperator => [vertexVariable = te1]
	val te2s = createGetVerticesOperator => [vertexVariable = te2]

	// conditions
	val filterCondition = createArithmeticComparisonExpression => [
		operator = ArithmeticComparisonOperator.NOT_EQUAL_TO
		leftOperand = route1
		rightOperand = route2
	]

	// //////////////////////////////////////////////////////////////////////////////////////////////////
	def semaphoreNeighborA() {
		// (sensor1:Sensor)<-[:MONITORED_BY]-(te1:TrackElement)-[:CONNECTS_TO]->(te2:TrackElement)-[:MONITORED_BY]->(sensor2:Sensor)
		val expand1 = createExpandOperator => [
			input = te1s
			direction = Direction.OUT
			sourceVertexVariable = te1
			targetVertexVariable = te2
			edgeVariable = connectsTo
		]
		val expand2 = createExpandOperator => [
			input = expand1
			direction = Direction.OUT
			sourceVertexVariable = te1
			targetVertexVariable = sensor1
			edgeVariable = monitoredBy1
		]
		val expand3 = createExpandOperator => [
			input = expand2
			direction = Direction.OUT
			sourceVertexVariable = te2
			targetVertexVariable = sensor2
			edgeVariable = monitoredBy2
		]

		val expand4 = createExpandOperator => [
			input = route1s
			direction = Direction.OUT
			sourceVertexVariable = route1
			targetVertexVariable = semaphore
			edgeVariable = exit
		]
		val expand5 = createExpandOperator => [
			input = expand4
			direction = Direction.OUT
			sourceVertexVariable = route1
			targetVertexVariable = sensor1
			edgeVariable = requires1
		]

		val expand6 = createExpandOperator => [
			input = route2sA
			direction = Direction.OUT
			sourceVertexVariable = route2
			targetVertexVariable = sensor2
			edgeVariable = requires2
		]

		val expand7 = createExpandOperator => [
			input = route2sB
			direction = Direction.OUT
			sourceVertexVariable = route2
			targetVertexVariable = semaphore
			edgeVariable = entry
		]

		val join1 = createJoinOperator => [
			leftInput = expand3
			rightInput = expand5
		]
		val join2 = createJoinOperator => [
			leftInput = join1
			rightInput = expand6
		]

		val filter = createSelectionOperator => [
			input = join2
			condition = filterCondition
		]

		val antiJoin = createAntiJoinOperator => [
			leftInput = filter
			rightInput = expand7
		]

		val projection = createProjectionOperator => [
			input = antiJoin
			variables.addAll(Arrays.asList(semaphore, route1, route2, sensor1, sensor2, te1, te2))
		]
		val production = createProductionOperator => [
			input = projection
		]
		semaphoreNeighbor.rootExpression = production
		return semaphoreNeighbor
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////////
	def semaphoreNeighborB() {
		// (te1:TrackElement)-[:CONNECTS_TO]->(te2:TrackElement)
		val expand1 = createExpandOperator => [
			input = te1s
			direction = Direction.OUT
			sourceVertexVariable = te1
			targetVertexVariable = te2
			edgeVariable = connectsTo
		]

		// (te1:TrackElement)-[:MONITORED_BY]->(sensor1:Sensor)<-[:REQUIRES]-(route1:Route)
		val expand2 = createExpandOperator => [
			input = te1s
			direction = Direction.OUT
			sourceVertexVariable = te1
			targetVertexVariable = sensor1
			edgeVariable = monitoredBy1
		]
		val expand3 = createExpandOperator => [
			input = route1s
			direction = Direction.OUT
			sourceVertexVariable = route1
			targetVertexVariable = sensor1
			edgeVariable = requires1
		]
		val join1 = createJoinOperator => [
			leftInput = expand2
			rightInput = expand3
		]

		// (te2:TrackElement)-[:MONITORED_BY]->(sensor2:Sensor)<-[:REQUIRES]-(route2:Route)
		val expand4 = createExpandOperator => [
			input = te2s
			direction = Direction.OUT
			sourceVertexVariable = te2
			targetVertexVariable = sensor2
			edgeVariable = monitoredBy2
		]
		val expand5 = createExpandOperator => [
			input = route2sA
			direction = Direction.OUT
			sourceVertexVariable = route2
			targetVertexVariable = sensor2
			edgeVariable = requires2
		]
		val join2 = createJoinOperator => [
			leftInput = expand4
			rightInput = expand5
		]

		// (route2:Route)-[:REQUIRES]->(sensor2:Sensor)<-[:MONITORED_BY]-(te1:TrackElement)
		// -[:CONNECTS_TO]->
		// (te2:TrackElement)-[:MONITORED_BY]->(sensor2:Sensor)<-[:REQUIRES]-(route2:Route)
		val join3 = createJoinOperator => [leftInput = expand1 rightInput = join1]

		// (route1:Route)-[:EXIT]->(semaphore:Semaphore)
		val expand6 = createExpandOperator => [
			input = route1s
			direction = Direction.OUT
			sourceVertexVariable = route1
			targetVertexVariable = semaphore
			edgeVariable = exit
		]

		// (route2:Route)-[:EXIT]->(semaphore:Semaphore)
		val expand7 = createExpandOperator => [
			input = route2sB
			direction = Direction.OUT
			sourceVertexVariable = route2
			targetVertexVariable = semaphore
			edgeVariable = entry
		]

		val join4 = createJoinOperator => [
			leftInput = join3
			rightInput = join2
		]

		val filter = createSelectionOperator => [
			input = join4
			condition = filterCondition
		]

		val join5 = createJoinOperator => [
			leftInput = filter
			rightInput = expand6
		]

		val antiJoin = createAntiJoinOperator => [
			leftInput = join5
			rightInput = expand7
		]

		val projection = createProjectionOperator => [
			input = antiJoin
			variables.addAll(Arrays.asList(semaphore, route1, route2, sensor1, sensor2, te1, te2))
		]
		val production = createProductionOperator => [
			input = projection
		]
		semaphoreNeighbor.rootExpression = production
		return semaphoreNeighbor
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////////
	def semaphoreNeighborC() {
		// (sensor1:Sensor)<-[:MONITORED_BY]-(te1:TrackElement)-[:CONNECTS_TO]->(te2:TrackElement)-[:MONITORED_BY]->(sensor2:Sensors)
		val expand1 = createExpandOperator => [
			input = te1s
			direction = Direction.OUT
			sourceVertexVariable = te1
			targetVertexVariable = te2
			edgeVariable = connectsTo
		]
		val expand2 = createExpandOperator => [
			input = expand1
			direction = Direction.OUT
			sourceVertexVariable = te1
			targetVertexVariable = sensor1
			edgeVariable = monitoredBy1
		]
		val expand3 = createExpandOperator => [
			input = expand2
			direction = Direction.OUT
			sourceVertexVariable = te2
			targetVertexVariable = sensor2
			edgeVariable = monitoredBy2
		]

		// (semaphore:Semaphore)<-[:EXIT]-(route1:Route)-[:REQUIRES]->(sensor1:Sensor)
		val expand4 = createExpandOperator => [
			input = route1s
			direction = Direction.OUT
			sourceVertexVariable = route1
			targetVertexVariable = sensor1
			edgeVariable = requires1
		]
		val expand5 = createExpandOperator => [
			input = expand4
			direction = Direction.OUT
			sourceVertexVariable = route1
			targetVertexVariable = semaphore
			edgeVariable = exit
		]

		val join1 = createJoinOperator => [
			leftInput = expand3
			rightInput = expand5
		]

		// (route2:Route)-[:REQUIRES]->(sensor2:Sensor)
		val expand6 = createExpandOperator => [
			input = route2sA
			direction = Direction.OUT
			sourceVertexVariable = route2
			targetVertexVariable = sensor2
			edgeVariable = requires2
		]

		val join2 = createJoinOperator => [
			leftInput = join1
			rightInput = expand6
		]

		// (route2:Route)-[:EXIT]->(semaphore:Semaphore)
		val expand7 = createExpandOperator => [
			input = route2sB
			direction = Direction.OUT
			sourceVertexVariable = route2
			targetVertexVariable = semaphore
			edgeVariable = entry
		]

		val filter = createSelectionOperator => [
			input = join2
			condition = filterCondition
		]

		val antiJoin = createAntiJoinOperator => [
			leftInput = filter
			rightInput = expand7
		]

		val projection = createProjectionOperator => [
			input = antiJoin
			variables.addAll(Arrays.asList(semaphore, route1, route2, sensor1, sensor2, te1, te2))
		]
		val production = createProductionOperator => [
			input = projection
		]
		semaphoreNeighbor.rootExpression = production
		return semaphoreNeighbor
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////////
	def semaphoreNeighborD() {
		// (te1:TrackElement)-[:CONNECTS_TO]->(te2:TrackElement)
		val expand1 = createExpandOperator => [
			input = te1s
			direction = Direction.OUT
			sourceVertexVariable = te1
			targetVertexVariable = te2
			edgeVariable = connectsTo
		]

		// (semaphore:Semaphore)<-[:EXIT]-(route1:Route)-[:REQUIRES]->(sensor1:Sensor)<-[:MONITORED_BY]-(te1:TrackElement)
		val expand2 = createExpandOperator => [
			input = route1s
			direction = Direction.OUT
			sourceVertexVariable = route1
			targetVertexVariable = semaphore
			edgeVariable = exit
		]
		val expand3 = createExpandOperator => [
			input = expand2
			direction = Direction.OUT
			sourceVertexVariable = route1
			targetVertexVariable = sensor1
			edgeVariable = requires1
		]
		val expand4 = createExpandOperator => [
			input = expand3
			direction = Direction.IN
			sourceVertexVariable = sensor1
			targetVertexVariable = te1
			edgeVariable = monitoredBy1
		]

		// (semaphore:Semaphore)<-[:EXIT]-(route1:Route)-[:REQUIRES]->(sensor1:Sensor)
		val expand5 = createExpandOperator => [
			input = te2s
			direction = Direction.OUT
			sourceVertexVariable = te2
			targetVertexVariable = sensor2
			edgeVariable = monitoredBy2
		]

		// (route2:Route)-[:REQUIRES]->(sensor2:Sensor)
		val expand6 = createExpandOperator => [
			input = route2sA
			direction = Direction.OUT
			sourceVertexVariable = route2
			targetVertexVariable = sensor2
			edgeVariable = requires2
		]

		val join1 = createJoinOperator => [
			leftInput = expand1
			rightInput = expand4
		]

		val join2 = createJoinOperator => [
			leftInput = join1
			rightInput = expand5
		]

		val join3 = createJoinOperator => [
			leftInput = join2
			rightInput = expand6
		]

		val filter = createSelectionOperator => [
			input = join3
			condition = filterCondition
		]

		// (route2:Route)-[:EXIT]->(semaphore:Semaphore)
		val expand7 = createExpandOperator => [
			input = route2sB
			direction = Direction.OUT
			sourceVertexVariable = route2
			targetVertexVariable = semaphore
			edgeVariable = entry
		]

		val antiJoin = createAntiJoinOperator => [
			leftInput = filter
			rightInput = expand7
		]

		val projection = createProjectionOperator => [
			input = antiJoin
			variables.addAll(Arrays.asList(semaphore, route1, route2, sensor1, sensor2, te1, te2))
		]
		val production = createProductionOperator => [
			input = projection
		]
		semaphoreNeighbor.rootExpression = production
		return semaphoreNeighbor
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////////
	def semaphoreNeighborE() {
		// (route1:Route)-[:EXIT]->(semaphore:Semaphore)
		val expand1 = createExpandOperator => [
			input = route1sA
			direction = Direction.OUT
			sourceVertexVariable = route1
			targetVertexVariable = semaphore
			edgeVariable = exit
		]

		// (route1:Route)-[:REQUIRES]->(sensor1:Sensor)
		val expand2 = createExpandOperator => [
			input = route2sA
			direction = Direction.OUT
			sourceVertexVariable = route2
			targetVertexVariable = sensor2
			edgeVariable = requires2
		]

		val join1 = createJoinOperator => [
			leftInput = expand1
			rightInput = expand2
		]

		val filter = createSelectionOperator => [
			input = join1
			condition = filterCondition
		]

		// (route2:Route)-[:ENTRY]->(semaphore:Semaphore)
		val expand3 = createExpandOperator => [
			input = route2sB
			direction = Direction.OUT
			sourceVertexVariable = route2
			targetVertexVariable = semaphore
			edgeVariable = entry
		]

		val antiJoin = createAntiJoinOperator => [
			leftInput = filter
			rightInput = expand3
		]

		val expand4 = createExpandOperator => [
			input = route1sB
			direction = Direction.OUT
			sourceVertexVariable = route1
			targetVertexVariable = sensor1
			edgeVariable = requires1
		]

		val join2 = createJoinOperator => [
			leftInput = antiJoin
			rightInput = expand4
		]

		// (te1:TrackElement)-[:MONITORED_BY]->(sensor1:Sensor)
		val expand5 = createExpandOperator => [
			input = te1sA
			direction = Direction.OUT
			sourceVertexVariable = te1
			targetVertexVariable = sensor1
			edgeVariable = monitoredBy1
		]

		val join3 = createJoinOperator => [
			leftInput = join2
			rightInput = expand5
		]

		// (te1:TrackElement)-[:CONNECTS_TO]->(te2:TrackElement)
		val expand6 = createExpandOperator => [
			input = te1sB
			direction = Direction.OUT
			sourceVertexVariable = te1
			targetVertexVariable = te2
			edgeVariable = connectsTo
		]

		val join4 = createJoinOperator => [
			leftInput = join3
			rightInput = expand6
		]

		// (te2:TrackElement)-[:MONITORED_BY]->(sensor2:Sensor)
		val expand7 = createExpandOperator => [
			input = route2sC
			direction = Direction.OUT
			sourceVertexVariable = te2
			targetVertexVariable = sensor2
			edgeVariable = monitoredBy2
		]

		val join5 = createJoinOperator => [
			leftInput = join4
			rightInput = expand7
		]

		val projection = createProjectionOperator => [
			input = join5
			variables.addAll(Arrays.asList(semaphore, route1, route2, sensor1, sensor2, te1, te2))
		]
		val production = createProductionOperator => [
			input = projection
		]
		semaphoreNeighbor.rootExpression = production
		return semaphoreNeighbor
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////////
	def semaphoreNeighborF() {
		val expand1 = createExpandOperator => [
			input = te1sA
			direction = Direction.OUT
			sourceVertexVariable = te1
			targetVertexVariable = sensor1
			edgeVariable = monitoredBy1
		]
		val expand2 = createExpandOperator => [
			input = te2s
			direction = Direction.OUT
			sourceVertexVariable = te2
			targetVertexVariable = sensor2
			edgeVariable = monitoredBy2
		]

		val join1 = createJoinOperator => [
			leftInput = expand1
			rightInput = expand2
		]

		val expand3 = createExpandOperator => [
			input = join1
			direction = Direction.IN
			sourceVertexVariable = sensor1
			targetVertexVariable = route1
			edgeVariable = requires1
		]
		val expand4 = createExpandOperator => [
			input = expand3
			direction = Direction.IN
			sourceVertexVariable = sensor2
			targetVertexVariable = route2
			edgeVariable = requires2
		]

		val filter = createSelectionOperator => [
			input = expand4
			condition = filterCondition
		]

		val expand5 = createExpandOperator => [
			input = filter
			direction = Direction.OUT
			sourceVertexVariable = route1
			targetVertexVariable = semaphore
			edgeVariable = exit
		]

		val expand6 = createExpandOperator => [
			input = te1sB
			direction = Direction.OUT
			sourceVertexVariable = te1
			targetVertexVariable = te2
			edgeVariable = connectsTo
		]

		val join2 = createJoinOperator => [
			leftInput = expand5
			rightInput = expand6
		]

		val expand7 = createExpandOperator => [
			input = route2sA
			direction = Direction.OUT
			sourceVertexVariable = route2
			targetVertexVariable = semaphore
			edgeVariable = entry
		]

		val antiJoin = createAntiJoinOperator => [
			leftInput = join2
			rightInput = expand7
		]

		val projection = createProjectionOperator => [
			input = antiJoin
			variables.addAll(Arrays.asList(semaphore, route1, route2, sensor1, sensor2, te1, te2))
		]
		val production = createProductionOperator => [
			input = projection
		]
		semaphoreNeighbor.rootExpression = production
		return semaphoreNeighbor
	}

}
