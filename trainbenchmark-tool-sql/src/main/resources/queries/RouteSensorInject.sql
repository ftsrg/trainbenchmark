SELECT DISTINCT
	Route.id AS route,
	Sensor.id AS sensor
FROM Sensor
INNER JOIN Route          ON Sensor.route = Route.id; -- the "Sensor.route" attribute is the inverse of the "Route.gathers" edge
