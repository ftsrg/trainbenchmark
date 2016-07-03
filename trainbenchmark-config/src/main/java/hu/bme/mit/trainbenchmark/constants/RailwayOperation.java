package hu.bme.mit.trainbenchmark.constants;

public enum RailwayOperation {
	ACTIVEROUTE("ActiveRoute"), //
	CONNECTEDSEGMENTS("ConnectedSegments"), //
	CONNECTEDSEGMENTS_INJECT("ConnectedSegmentsInject"), //
	CONNECTEDSEGMENTS_REPAIR("ConnectedSegmentsRepair"), //
	POSLENGTH("PosLength"), //
	POSLENGTH_INJECT("PosLengthInject"), //
	POSLENGTH_REPAIR("PosLengthRepair"), //
	ROUTESENSOR("RouteSensor"), //
	ROUTESENSOR_INJECT("RouteSensorInject"), //
	ROUTESENSOR_REPAIR("RouteSensorRepair"), //
	SEMAPHORENEIGHBOR("SemaphoreNeighbor"), //
	SEMAPHORENEIGHBOR_INJECT("SemaphoreNeighborInject"), //
	SEMAPHORENEIGHBOR_REPAIR("SemaphoreNeighborRepair"), //
	SWITCHMONITORED("SwitchMonitored"), //
	SWITCHMONITORED_INJECT("SwitchMonitoredInject"), //
	SWITCHMONITORED_REPAIR("SwitchMonitoredRepair"), //
	SWITCHSET("SwitchSet"), //
	SWITCHSET_INJECT("SwitchSetInject"), //
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
