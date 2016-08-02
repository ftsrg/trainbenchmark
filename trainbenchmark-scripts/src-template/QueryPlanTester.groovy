for (scenario in scenarios) {
	def scenarioString = scenario.toString().toLowerCase()
	def messageSize = 2048

	def operations1 = [ACTIVEROUTE]
	for (variant in 'A'..'E') {
		for (size = minSize; size <= maxSize; size *= 2) {
			def modelPath = "railway-${scenarioString}-${size}"
			def bcRouteSensor = new BenchmarkConfigCore(xms, xmx, timeout, runs, queryTransformationCount, modelPath, operations1, "ActiveRoute")
			if (BenchmarkRunner.run(new IQDBenchmarkConfigWrapper(bcRouteSensor, messageSize, variant)) == 143) {
				break
			}
		}
	}

	def operations2 = [ROUTESENSOR_REPAIR]
	for (variant in 'A'..'C') {
		for (size = minSize; size <= maxSize; size *= 2) {
			def modelPath = "railway-${scenarioString}-${size}"
			def bcRouteSensor = new BenchmarkConfigCore(xms, xmx, timeout, runs, queryTransformationCount, modelPath, operations2, "RouteSensor")
			if (BenchmarkRunner.run(new IQDBenchmarkConfigWrapper(bcRouteSensor, messageSize, variant)) == 143) {
				break
			}
		}
	}

	def operations3 = [CONNECTEDSEGMENTS_REPAIR]
	for (variant in 'A'..'D') {
		for (size = minSize; size <= maxSize; size *= 2) {
			def modelPath = "railway-${scenarioString}-${size}"
			def bcConnectedSegments = new BenchmarkConfigCore(xms, xmx, timeout, runs, queryTransformationCount, modelPath, operations3, "ConnectedSegments")
			if (BenchmarkRunner.run(new IQDBenchmarkConfigWrapper(bcConnectedSegments, messageSize, variant)) == 143) {
				break
			}
		}
	}

	def operations4 = [SEMAPHORENEIGHBOR_REPAIR]
	for (variant in 'A'..'F') {
		for (size = minSize; size <= maxSize; size *= 2) {
			def modelPath = "railway-${scenarioString}-${size}"
			def bcRouteSensor = new BenchmarkConfigCore(xms, xmx, timeout, runs, queryTransformationCount, modelPath, operations4, "SemaphoreNeighbor")
			if (BenchmarkRunner.run(new IQDBenchmarkConfigWrapper(bcRouteSensor, messageSize, variant)) == 143) {
				break
			}
		}
	}

	def operations5 = [SWITCHSET_REPAIR]
	for (variant in 'A'..'B') {
		for (size = minSize; size <= maxSize; size *= 2) {
			def modelPath = "railway-${scenarioString}-${size}"
			def bcRouteSensor = new BenchmarkConfigCore(xms, xmx, timeout, runs, queryTransformationCount, modelPath, operations5, "SwitchSet")
			if (BenchmarkRunner.run(new IQDBenchmarkConfigWrapper(bcRouteSensor, messageSize, variant)) == 143) {
				break
			}
		}
	}
}
