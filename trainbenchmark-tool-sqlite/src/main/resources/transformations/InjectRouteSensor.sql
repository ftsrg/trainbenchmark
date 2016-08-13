-- the "Sensor.route" attribute is the inverse of the "Route.gathers" edge
UPDATE Sensor
SET route = NULL
WHERE 1 = 1
	AND id = (SELECT Value FROM Variables WHERE Name = 'sensor')
	AND route = (SELECT Value FROM Variables WHERE Name = 'route');
