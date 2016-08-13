SET @route := ?;
SET @sensor := ?;

-- the "Sensor.route" attribute is the inverse of the "Route.gathers" edge
UPDATE Sensor
SET route = NULL
WHERE 1 = 1
	AND route = @route
	AND id = @sensor;
