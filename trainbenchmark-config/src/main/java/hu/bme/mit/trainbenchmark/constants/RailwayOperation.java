package hu.bme.mit.trainbenchmark.constants;

public enum RailwayOperation {
	// well-formedness constraint operations (query only)
	ACTIVEROUTE("ActiveRoute"), //
	CONNECTEDSEGMENTS("ConnectedSegments"), //
	POSLENGTH("PosLength"), //
	ROUTELENGTH("RouteLength"), //
	ROUTEREACHABILITY("RouteReachability"), //
	ROUTESENSOR("RouteSensor"), //
	SEMAPHORENEIGHBOR("SemaphoreNeighbor"), //
	SWITCHMONITORED("SwitchMonitored"), //
	SWITCHSET("SwitchSet"), //

	// fault injection operations
	CONNECTEDSEGMENTS_INJECT("ConnectedSegmentsInject"), //
	POSLENGTH_INJECT("PosLengthInject"), //
	ROUTESENSOR_INJECT("RouteSensorInject"), //
	SEMAPHORENEIGHBOR_INJECT("SemaphoreNeighborInject"), //
	SWITCHMONITORED_INJECT("SwitchMonitoredInject"), //
	SWITCHSET_INJECT("SwitchSetInject"), //

	// repair operations
	CONNECTEDSEGMENTS_REPAIR("ConnectedSegmentsRepair"), //
	POSLENGTH_REPAIR("PosLengthRepair"), //
	ROUTESENSOR_REPAIR("RouteSensorRepair"), //
	SEMAPHORENEIGHBOR_REPAIR("SemaphoreNeighborRepair"), //
	SWITCHMONITORED_REPAIR("SwitchMonitoredRepair"), //
	SWITCHSET_REPAIR("SwitchSetRepair"), //
	;

	private String name;

	RailwayOperation(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}
