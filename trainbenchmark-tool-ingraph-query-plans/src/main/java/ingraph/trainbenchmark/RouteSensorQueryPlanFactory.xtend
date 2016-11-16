package ingraph.trainbenchmark

import relalg.Direction

class RouteSensorQueryPlanFactory extends QueryPlanFactory {

	// container
	val routeSensor = createRelalgContainer

	// vertex labels
	val routeLabel = createVertexLabel => [name = "Route"]
	val sensorLabel = createVertexLabel => [name = "Sensor"]
	val switchLabel = createVertexLabel => [name = "Switch"]
	val switchPositionLabel = createVertexLabel => [name = "SwitchPosition"]

	// edge labels
	val followsLabel = createEdgeLabel => [name = "follows"]
	val requiresLabel = createEdgeLabel => [name = "requires"]
	val monitoredByLabel = createEdgeLabel => [name = "monitoredBy"]
	val targetLabel = createEdgeLabel => [name = "target"]

	// vertex variables
	val route = createVertexVariable => [name = "route"; vertexLabels.add(routeLabel)]
	val sw = createVertexVariable => [name = "sw"; vertexLabels.add(switchLabel)]
	val swP = createVertexVariable => [name = "swP"; vertexLabels.add(switchPositionLabel)]
	val sensor = createVertexVariable => [name = "sensor"; vertexLabels.add(sensorLabel)]

	// edge variables
	val target = createEdgeVariable => [name = "_e1"; edgeLabels.add(targetLabel)]
	val monitoredBy = createEdgeVariable => [name = "_e2"; edgeLabels.add(monitoredByLabel)]
	val follows = createEdgeVariable => [name = "_e3"; edgeLabels.add(followsLabel)]
	val requires = createEdgeVariable => [name = "_e4"; edgeLabels.add(requiresLabel)]

	// inputs
	val getRoutes = createGetVerticesOperator => [vertexVariable = route]
	val getSws = createGetVerticesOperator => [vertexVariable = sw]
	val getSwPs = createGetVerticesOperator => [vertexVariable = swP]

	def routeSensorA() {
		val expand1 = createExpandOperator => [
			input = getRoutes
			direction = Direction.OUT
			sourceVertexVariable = route
			targetVertexVariable = swP
			edgeVariable = follows
		]
		val expand2 = createExpandOperator => [
			input = expand1
			direction = Direction.OUT
			sourceVertexVariable = swP
			targetVertexVariable = sw
			edgeVariable = target
		]
		val expand3 = createExpandOperator => [
			input = expand2
			direction = Direction.OUT
			sourceVertexVariable = sw
			targetVertexVariable = sensor
			edgeVariable = monitoredBy
		]
		val expand4 = createExpandOperator => [
			input = getRoutes
			direction = Direction.OUT
			sourceVertexVariable = route
			targetVertexVariable = sensor
			edgeVariable = requires
		]

		val antiJoin = createAntiJoinOperator => [
			leftInput = expand3
			rightInput = expand4
		]
		val production = createProductionOperator => [
			input = antiJoin
		]

		routeSensor.rootExpression = production
		return routeSensor
	}

	def routeSensorB() {
		val expand1 = createExpandOperator => [
			input = getSwPs
			direction = Direction.OUT
			sourceVertexVariable = swP
			targetVertexVariable = sw
			edgeVariable = target
		]
		val expand2 = createExpandOperator => [
			input = expand1
			direction = Direction.OUT
			sourceVertexVariable = sw
			targetVertexVariable = sensor
			edgeVariable = monitoredBy
		]
		val expand3 = createExpandOperator => [
			input = expand2
			direction = Direction.IN
			sourceVertexVariable = swP
			targetVertexVariable = route
			edgeVariable = follows
		]
		val expand4 = createExpandOperator => [
			input = getRoutes
			direction = Direction.OUT
			sourceVertexVariable = route
			targetVertexVariable = sensor
			edgeVariable = requires
		]

		val antiJoin = createAntiJoinOperator => [
			leftInput = expand3
			rightInput = expand4
		]
		val production = createProductionOperator => [
			input = antiJoin
		]

		routeSensor.rootExpression = production
		return routeSensor
	}

	def routeSensorC() {
		val expand1 = createExpandOperator => [
			input = getRoutes
			direction = Direction.OUT
			sourceVertexVariable = route
			targetVertexVariable = swP
			edgeVariable = follows
		]
		val expand2 = createExpandOperator => [
			input = getSws
			direction = Direction.OUT
			sourceVertexVariable = sw
			targetVertexVariable = sensor
			edgeVariable = monitoredBy
		]
		val expand3 = createExpandOperator => [
			input = getRoutes
			direction = Direction.OUT
			sourceVertexVariable = route
			targetVertexVariable = sensor
			edgeVariable = requires
		]
		val expand4 = createExpandOperator => [
			input = getSwPs
			direction = Direction.OUT
			sourceVertexVariable = swP
			targetVertexVariable = sw
			edgeVariable = target
		]

		val join1 = createJoinOperator => [
			leftInput = expand1
			rightInput = expand2
		]
		val antiJoin = createAntiJoinOperator => [
			leftInput = join1
			rightInput = expand3
		]
		val join2 = createJoinOperator => [
			leftInput = antiJoin
			rightInput = expand4
		]
		val production = createProductionOperator => [
			input = join2
		]

		routeSensor.rootExpression = production
		return routeSensor
	}

}
