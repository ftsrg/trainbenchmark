-- the "Sensor.route" attribute is the inverse of the "Route.gathers" edge
UPDATE Sensor
SET route = NULL
WHERE id = (SELECT Value FROM Variables WHERE Name = 'sensor')
	AND route = (SELECT Value FROM Variables WHERE Name = 'route');
