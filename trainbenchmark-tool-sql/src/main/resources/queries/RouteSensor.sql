SELECT DISTINCT
	Route.id AS route,
	Sensor.id AS sensor,
	SwitchPosition.id AS swP,
	Switch.id as sw
FROM Sensor
INNER JOIN monitoredBy    ON monitoredBy.Sensor_id = Sensor.id
INNER JOIN Switch         ON Switch.id = monitoredBy.TrackElement_id
INNER JOIN SwitchPosition ON SwitchPosition.target = Switch.id
INNER JOIN Route          ON Route.id = SwitchPosition.route -- the "SwitchPosition.route" attribute is the inverse of the "Route.follows" edge
WHERE NOT EXISTS (
  SELECT 1
  FROM requires
  WHERE Route_id  = Route.id
    AND Sensor_id = Sensor.id
)
