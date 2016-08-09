SELECT
	Switch.id AS sw,
	monitoredBy.Sensor_id AS sensor
FROM Switch
LEFT JOIN monitoredBy ON monitoredBy.TrackElement_id = Switch.id;
